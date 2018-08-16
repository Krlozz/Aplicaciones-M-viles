package com.carlosayalat.maps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnPolygonClickListener, GoogleMap.OnPolylineClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GoogleMap mMap;

    public MapsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getMapAsync(this); // línea super importante
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // programar permisos control de maps
        //this solo funciona en activities
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 177);
            }

            return;
        } else {
            mMap.setMyLocationEnabled(true);
            dibujar();
            agregarMarcacion(-0.206361,-78.491335);
            moverCamara(-0.206361,-78.491335);
            mMap.setOnPolygonClickListener(this);    // ejecuta método abstracto de polygon

            mMap.getUiSettings().setAllGesturesEnabled(false);    // gestos
            mMap.getUiSettings().setZoomControlsEnabled(true);
            // mMap.getUiSettings       para habilitar gestos
        }


    }


    public void dibujar() {
        Polygon polyline = mMap.addPolygon(new PolygonOptions().
                clickable(true).
                add(new LatLng(-0.206643, -78.488023),
                        new LatLng(-0.208491, -78.485814),
                        new LatLng(-0.212343, -78.488668),
                        new LatLng(-0.212740, -78.489322),
                        new LatLng(-0.212364, -78.491854),
                        new LatLng(-0.210379, -78.493624),
                        new LatLng(-0.206643, -78.488023))
                .fillColor(Color.RED).
                strokeColor(Color.BLUE)
                );
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-0.206643, -78.488023),16));

        polyline.setTag("ID:001_EPN - Aplicaciones Móviles");


    }

    public void agregarMarcacion(double lat, double lng) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng))
                .title("Mi marcación")
                .snippet("Contenido....")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                //.draggable(true)   // para poder arrastrarle donde se quiera
                );
        /*   // para al darle click que abra otro activity
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.getId();
                // intent

            }
        });
        */
    }

    //public void moverCamara(LatLng latLng) {
    public void moverCamara(double lat, double lng) {
        CameraPosition cameraPosition = CameraPosition
                .builder()
                //.target(latLng)
                .target(new LatLng(lat,lng))
                .zoom(16)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    @Override
    public void onPolygonClick(Polygon polygon) {
        Toast.makeText(getContext(), polygon.getId() + polygon.getTag(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}
