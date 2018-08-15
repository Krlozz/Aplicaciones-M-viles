package controlador;

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

/**
 * Created by USRGAM on 11/05/2018.
 */

public class ListaUsers {

    private File file = Environment.getExternalStorageDirectory(); // obtengo un archivo de la memoria externa
    private String ruta = file.getAbsolutePath() + File.separator;  // devuelve todas las rutas


    public void escribirArchivo(Persona users[], String nombre){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(ruta + nombre);
            out = new ObjectOutputStream(fos);
            out.writeObject(users);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        }
    }

    public Persona[] leerArchivo(String nombre){
        Persona[] listaUsers = new Persona[]{};
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(ruta + nombre);
            in = new ObjectInputStream(fis);
            listaUsers = (Persona[]) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("error de lista",e.toString());
        }
        return listaUsers;
    }
}
