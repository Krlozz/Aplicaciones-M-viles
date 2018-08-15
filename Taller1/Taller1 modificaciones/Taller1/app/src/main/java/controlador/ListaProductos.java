package controlador;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.Producto;

public class ListaProductos implements Serializable {

    private File file = Environment.getExternalStorageDirectory(); // obtengo un archivo de la memoria externa
    private String ruta = file.getAbsolutePath() + File.separator;  // devuelve todas las rutas
    private File aux;

    public ArrayList<Producto> listadoProductos(Producto producto){
        ArrayList<Producto> listaProductos=new ArrayList<>();
        listaProductos.add(producto);
        return listaProductos;
    }

    public void crearArchivo(Producto productos, String nombre){
        aux = new File(ruta + nombre);
        if (aux.exists()) {
            ArrayList<Producto> listaProductos = new ArrayList<>();
            listaProductos = leerArchivo(nombre);
            listaProductos.add(productos);
            escribirArchivo(listaProductos,nombre);
        } else {
            try {
                if (aux.createNewFile()){
                    escribirArchivo(listadoProductos(productos),nombre);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Producto> leerArchivo(String nombre) {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            fis = new FileInputStream(ruta + nombre);
            in = new ObjectInputStream(fis);
            productos = (ArrayList<Producto>) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("error de lista",e.toString());
        }
        return productos;
    }

    public void escribirArchivo(ArrayList<Producto> productos, String nombre){
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

    public void modificar(int position,Producto producto, String nombre){
        file = new File(ruta + nombre);
        if (file.exists()){
            ArrayList<Producto> listaProductos = new ArrayList<>();
            listaProductos = leerArchivo(nombre);
            listaProductos.set(position,producto);
            escribirArchivo(listaProductos,nombre);
        }
    }

    public void eliminar(int position, String nombre){
        file = new File(ruta + nombre);
        if (file.exists()){
            ArrayList<Producto> listaProductos = new ArrayList<>();
            listaProductos = leerArchivo(nombre);
            listaProductos.remove(position);
            escribirArchivo(listaProductos,nombre);
        }
    }



    /*public void escribirArchivo(Producto productos[], String nombre){
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

    public Producto[] leerArchivo(String nombre){
        Producto[] listaPoductos = new Producto[]{};
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(ruta + nombre);
            in = new ObjectInputStream(fis);
            listaPoductos = (Producto[]) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("error de lista",e.toString());
        }
        return listaPoductos;
    }*/


}
