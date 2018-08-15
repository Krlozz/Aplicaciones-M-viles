package control;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelo.Persona;

public class leerEscribirArchivos {

    private File file = Environment.getExternalStorageDirectory(); // obtengo un archivo de la memoria externa
    private String ruta = file.getAbsolutePath() + File.separator;  // devuelve todas las rutas

    public void escribirArchivo(Persona p, String nombre) {
        try {
            FileOutputStream fos = new FileOutputStream(ruta+nombre);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(p);
            out.close();


        } catch (FileNotFoundException e) {
            Log.e( "error archivo: ",e.toString());
        } catch (IOException e) {
            Log.e( "error io: ",e.toString());
        }
    }

    public Persona leerArchivo(String nombre) {
        Persona p = null;
        try {
            FileInputStream fis = new FileInputStream(ruta+nombre);
            ObjectInputStream in = new ObjectInputStream(fis);
            p = (Persona) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e( "error archivo: ",e.toString());
        } catch (IOException e) {
            Log.e( "error io: ",e.toString());
        } catch (ClassNotFoundException e) {
            Log.e( "error persona: ",e.toString());
        }


        return p;
    }

}
