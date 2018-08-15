package com.carlosayalat.taller1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.zxing.Result;

import java.util.ArrayList;

import controlador.InicialesProductos;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import modelo.Producto;

public class EscanerQR extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUESTCAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camID = Camera.CameraInfo.CAMERA_FACING_BACK;

    String usuario,contraseña;

    //private Producto[] producto;
    private ArrayList<Producto> producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner_qr);

        usuario = getIntent().getExtras().getString("idUsuario");
        contraseña = getIntent().getExtras().getString("idContraseña");

        // comprobar/verificar permisos de acceso a la cámara
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);   // al momento de abrirla va aparecer la cámara directamente
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // M es desde 23 para adelante
            // controlar los permisos
            if(verificarPermisos()) {
                // mensaje
                Toast.makeText(getApplicationContext(),"Permiso otorgado",Toast.LENGTH_LONG).show();
            } else {
                // solicito permisos
                solicitarPermisos();
            }
        }
    }

    public boolean verificarPermisos() {
        return ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED;          // algo específico que se quiere, contexto de la aplicación
    }

    public void solicitarPermisos() {
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA},REQUESTCAMERA);
    }

    @Override
    protected void onResume() {      // para reanudar los frames
        super.onResume();
        int apiVersion = Build.VERSION.SDK_INT;
        if(apiVersion >= Build.VERSION_CODES.M) {
            if(verificarPermisos()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                // obtenga los resultados y arranque la cámara
                scannerView.setResultHandler(this);
                scannerView.startCamera();   // el scanner arranca la cámara
            } else {
                solicitarPermisos();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();   // paramos la cámara
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUESTCAMERA:
                if(grantResults.length > 0) {
                    boolean aceptaPermiso = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(aceptaPermiso) {
                        // mensaje
                    } else {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                                requestPermissions(new String[] {Manifest.permission.CAMERA},REQUESTCAMERA);
                            }
                        }
                    }
                }
        }
    }

    @Override
    public void handleResult(Result result) { // con este método se escanea el código
        /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        //alertDialog.setMessage("mensaje cualquiera");
        alertDialog.setMessage(result.getText());
        alertDialog.show();*/
        //producto = new Producto().cargarProductos();
        producto = new InicialesProductos().listaProductosIniciales();
        for (int i = 0; i < producto.size(); i++) {
            //if(result.getText().equals(producto[i].getQr())){
            if(result.getText().equals(producto.get(i).getQr())){
                Intent intent = new Intent(getApplicationContext(),Detalle.class);
                //intent.putExtra("id", producto[i]);
                intent.putExtra("id", producto.get(i));
                intent.putExtra("idUsuario", usuario);
                intent.putExtra("idContraseña", contraseña);
                startActivity(intent);

                break;
            }
        }

        Log.e("Resultado",result.getText());
        Log.e("ResultadoBar",result.getBarcodeFormat().toString());
        onResume();
        //alertDialog.setMessage(result.getBarcodeFormat().toString());
    }



}