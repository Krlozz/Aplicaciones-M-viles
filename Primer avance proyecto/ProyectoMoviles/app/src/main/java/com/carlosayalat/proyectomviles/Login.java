package com.carlosayalat.proyectomviles;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    //GMAIL
    private GoogleApiClient googleApiClient;
    private final int CODERC = 9001;

    //FB
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SignInButton botongoogle = (SignInButton) findViewById(R.id.loginGmail);
        botongoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGmail();
            }
        });

        /* Para el hash de facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        printKeyHash();
        */
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

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

        loginButton = (LoginButton) findViewById(R.id.loginFacebook);
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.registerCallback(callbackManager, callback);
        /*loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(getApplicationContext(),General.class);
                        intent.putExtra("idUsuario", "Krlozz");
                        intent.putExtra("idCorreo", "Krlozz");
                        intent.putExtra("idFoto", "Krlozz");
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        String cancelMessage = "Login Cancelado.";
                        Toast.makeText(getApplicationContext(), cancelMessage, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        String errorMessage = "Login error.";
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
        );*/


    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return (accessToken != null) && (!accessToken.isExpired());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //los logs de 'instalar' y 'aplicación activa' App Eventos.
        //AppEventsLogger.activateApp(this);
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs de'app desactivada' App Eventos.
        AppEventsLogger.deactivateApp(this);
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent intent = new Intent(getApplicationContext(),General.class);
            intent.putExtra("idUsuario", profile.getFirstName() + " " + profile.getLastName());
            intent.putExtra("idCorreo", profile.getLinkUri().toString());
            intent.putExtra("idFoto", profile.getProfilePictureUri(200,200).toString());
            startActivity(intent);
        }
    }


    /* Para el hash de facebook
    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo( getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("jk", "Exception(NameNotFoundException) : " + e);
        } catch (NoSuchAlgorithmException e) {
            Log.e("mkm", "Exception(NoSuchAlgorithmException) : " + e);
        }
    }
    */

    public void loginGmail() {
        if(googleApiClient!=null){
            //desconectando
            googleApiClient.disconnect();

        }
        //solicitar correo para iniciar sesión
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        //igual el cliente con el logeo
        googleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();
        //abrir ventana de google
        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signIntent,CODERC);

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }*/

    //ver el resultado de otro activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODERC) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()) {
                GoogleSignInAccount acc = result.getSignInAccount();

                String token = acc.getIdToken();

                Log.e("correo: " ,acc.getEmail());
                Log.e("nombre: " ,acc.getDisplayName());
                Log.e("imagen: " ,acc.getPhotoUrl().toString());
                if(token!=null){
                    Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),General.class);
                intent.putExtra("idUsuario", acc.getDisplayName());
                intent.putExtra("idCorreo", acc.getEmail());
                intent.putExtra("idFoto", acc.getPhotoUrl().toString());
                startActivity(intent);
            }
        }
    }

}


