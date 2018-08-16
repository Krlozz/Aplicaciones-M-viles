package com.carlosayalat.proyectomviles;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
/*import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;*/
import android.content.Context;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;

import org.opencv.android.*;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class testOpenCV extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener{

    private static final String TAG = "OpenCV3::HaarTest";
    private CameraBridgeViewBase openCvCameraView;
    private CascadeClassifier cascadeClassifier;
    private Mat grayScaleImg;
    private int absoluteFaceSize;



    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) throws IOException {
            switch (status){
                case BaseLoaderCallback.SUCCESS:
                    initializeOpenCVDependencies();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    private void initializeOpenCVDependencies() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.haarcascade_frontalface_alt2);
        //InputStream is = getResources().openRawResource(R.raw.lbpcascade_frontalface);
        File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
        File mCascadeFile = new File(cascadeDir,"haarcascade_frontalface_alt2.xml");
        //File mCascadeFile = new File(cascadeDir, "lbpcascade_frontalface.xml");
        FileOutputStream os = new FileOutputStream(mCascadeFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        for (byte b:buffer){
            Log.e("--====>Byte", Byte.toString(b));
        }
        while ((bytesRead=is.read(buffer))!=-1){
            Log.e("=================D",Integer.toString(bytesRead));
            os.write(buffer,0,bytesRead);
        }
        is.close();
        os.close();

        cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
        cascadeClassifier.load(mCascadeFile.getAbsolutePath());
        if (cascadeClassifier.empty()){
            Log.e(TAG, "Failed to load cascade classifier");
            cascadeClassifier = null;
        }else
            Log.i(TAG, "Loaded cascade classifier from " + mCascadeFile.getAbsolutePath());

        openCvCameraView.enableView();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        openCvCameraView = new JavaCameraView(this,-1);
        setContentView(openCvCameraView);
        openCvCameraView.setCvCameraViewListener(this);

    }




    @Override
    public void onCameraViewStarted(int width, int height) {
        grayScaleImg = new Mat(height,width, CvType.CV_8UC4);
        Log.e("========>H",Integer.toString(height));
        Log.e("========>W",Integer.toString(width));
        Log.e("========>",Integer.toString(CvType.CV_8UC4));
        Log.e("----------->>",Integer.toString(absoluteFaceSize));
        absoluteFaceSize = (int) (height*0.1);
    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(Mat inputFrame) {

        Imgproc.cvtColor(inputFrame,grayScaleImg,Imgproc.COLOR_RGBA2RGB);
        MatOfRect faces = new MatOfRect();
        if (cascadeClassifier!=null){
            cascadeClassifier.detectMultiScale(grayScaleImg,faces,1.1,2,2,
                    new Size(absoluteFaceSize,absoluteFaceSize),new Size());

        }
        Rect[] facesArray = faces.toArray();
        for (Rect var : facesArray)
        {
            Log.e("valor RECT ===>",var.toString());
        }
        for (int i=0;i<facesArray.length;i++){
            //Log.e("valor",facesArray[i].tl().toString());
            // color del cuadro que reconoce los rostros
            Imgproc.rectangle(inputFrame,facesArray[i].tl(),facesArray[i].br(),new Scalar(255, 0, 0),2);

            Log.e("Prueba", facesArray[i].tl().toString()+ facesArray[i].br().toString());
            Hablar();
        }


        Log.e("TAG",inputFrame.toString());
        return inputFrame;
    }

    private TextToSpeech hablar;

    @Override
    protected void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_13,this,mLoaderCallback);
    }

    public void Hablar() {
        hablar = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // idioma
                    int result = hablar.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "No se puede hablar en ese lenguaje");
                    } else {
                        hablar.speak("Reconocido", TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                    Log.e("TTS", "No se inicializó correctamente");
                }
            }
        });
    }
}