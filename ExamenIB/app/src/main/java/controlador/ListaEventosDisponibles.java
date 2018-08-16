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
import java.util.ArrayList;

import modelo.Evento;

public class ListaEventosDisponibles {

    private File file = Environment.getExternalStorageDirectory(); // obtengo un archivo de la memoria externa
    private String ruta = file.getAbsolutePath() + File.separator;  // devuelve todas las rutas
    private File aux;

    public ArrayList<Evento> listadoEventos(Evento producto){
        ArrayList<Evento> listaEventos=new ArrayList<>();
        listaEventos.add(producto);
        return listaEventos;
    }

    public void crearArchivo(Evento eventos, String nombre){
        aux = new File(ruta + nombre);
        if (aux.exists()) {
            ArrayList<Evento> listaEventos = new ArrayList<>();
            listaEventos = leerArchivo(nombre);
            listaEventos.add(eventos);
            escribirArchivo(listaEventos,nombre);
        } else {
            try {
                if (aux.createNewFile()){
                    escribirArchivo(listadoEventos(eventos),nombre);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Evento> leerArchivo(String nombre) {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        ArrayList<Evento> productos = new ArrayList<>();
        try {
            fis = new FileInputStream(ruta + nombre);
            in = new ObjectInputStream(fis);
            productos = (ArrayList<Evento>) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("error de lista",e.toString());
        }
        return productos;
    }

    public void escribirArchivo(ArrayList<Evento> productos, String nombre){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(ruta + nombre);
            out = new ObjectOutputStream(fos);
            out.writeObject(productos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        }
    }




}
