package com.carlosayalat.codigoqrbarras;

//import android.graphics.Camera;
// se trae con el id del hardware
import android.app.AlertDialog;
import android.app.Dialog;
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

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.Manifest;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUESTCAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camID = Camera.CameraInfo.CAMERA_FACING_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        //alertDialog.setMessage("mensaje cualquiera");
        alertDialog.setMessage(result.getText());
        alertDialog.show();
        Log.e("Resultado",result.getText());
        Log.e("ResultadoBar",result.getBarcodeFormat().toString());
        onResume();
        //alertDialog.setMessage(result.getBarcodeFormat().toString());
    }
}
