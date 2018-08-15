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
import java.io.Serializable;
import java.util.HashMap;

import modelo.Usuario;

public class ListaUsuarios implements Serializable {

    private File file = Environment.getExternalStorageDirectory(); // obtengo un archivo de la memoria externa
    private String ruta = file.getAbsolutePath() + File.separator;  // devuelve todas las rutas
    private File aux;


    public void creacionArchivo(Usuario user, String nombre){
        aux = new File(ruta + nombre);
        if (aux.exists()){
            HashMap<String,Object> listaUsuarios = new HashMap<String, Object>();
            listaUsuarios = leerArchivo(nombre);
            listaUsuarios.put(user.getCorreo(),user);
            escribirArchivo(listaUsuarios,nombre);
        }else{
            try {
                if (aux.createNewFile()){
                    escribirArchivo(usuarios(user),nombre);
                }
            } catch (IOException e) {
                Log.e("error creación archivo:",e.toString());
            }
        }
    }

    public HashMap<String,Object> usuarios(Usuario usuario){
        HashMap<String,Object> listaUsuarios = new HashMap();
        listaUsuarios.put(usuario.getCorreo(),usuario);
        return listaUsuarios;
    }

    public void escribirArchivo(HashMap<String,Object> usuariosWri, String nombre){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(ruta + nombre);
            out = new ObjectOutputStream(fos);
            out.writeObject(usuariosWri);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        }
    }

    public HashMap<String,Object> leerArchivo(String nombre) {
        HashMap<String, Object> usuarios = new HashMap<String, Object>();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(ruta + nombre);
            in = new ObjectInputStream(fis);
            usuarios = (HashMap<String, Object>) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e("error archivo", e.toString());
        } catch (IOException e) {
            Log.e("error io", e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("error lectura archivo:", e.toString());
        }
        return usuarios;
    }
}
