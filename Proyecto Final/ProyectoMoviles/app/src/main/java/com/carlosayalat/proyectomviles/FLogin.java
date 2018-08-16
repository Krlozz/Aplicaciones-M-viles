package com.carlosayalat.proyectomviles;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FLogin.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FLogin extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //GMAIL
    private GoogleApiClient googleApiClient;
    private final int CODERC = 9001;


    //FB
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;



    private OnFragmentInteractionListener mListener;

    public FLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FLogin newInstance(String param1, String param2) {
        FLogin fragment = new FLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flogin, container, false);

        SignInButton botongoogle = (SignInButton) view.findViewById(R.id.loginGmail);
        botongoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGmail();
            }
        });

        FacebookSdk.sdkInitialize(getContext());
        AppEventsLogger.activateApp(getActivity());

        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        /*loginButton = (LoginButton) view.findViewById(R.id.loginFacebook);
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);*/



        return view;
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return (accessToken != null) && (!accessToken.isExpired());
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(getContext());
    }

    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent intent = new Intent(getContext(),Usuario.class);
            intent.putExtra("idUsuario", profile.getFirstName() + " " + profile.getLastName());
            intent.putExtra("idCorreo", profile.getLinkUri().toString());
            System.out.println(profile.getLinkUri().toString());
            intent.putExtra("idFoto", profile.getProfilePictureUri(200,200).toString());
            intent.putExtra("id", "2");
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODERC) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()) {
                GoogleSignInAccount acc = result.getSignInAccount();

                String token = acc.getIdToken();

                if(token!=null){
                    Toast.makeText(getContext(), token, Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getContext(),Usuario.class);
                intent.putExtra("idUsuario", acc.getDisplayName());
                intent.putExtra("idCorreo", acc.getEmail());
                intent.putExtra("idFoto", acc.getPhotoUrl().toString());
                intent.putExtra("id", "1");
                startActivity(intent);
            }
        }
    }

    public void loginGmail() {
        if(googleApiClient!=null){
            //desconectando
            googleApiClient.disconnect();

        }
        //solicitar correo para iniciar sesión
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        //igual el cliente con el logeo
        googleApiClient = new GoogleApiClient.Builder(getContext()).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();
        //abrir ventana de google
        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signIntent,CODERC);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
