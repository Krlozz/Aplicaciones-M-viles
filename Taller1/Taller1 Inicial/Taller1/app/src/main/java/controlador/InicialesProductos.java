package controlador;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.Producto;

public class InicialesProductos implements Serializable {

    private ArrayList<Producto> productos;

    public ArrayList listaProductosIniciales() {
        productos = new ArrayList<>();
        productos.add(new Producto("camiseta","001",2.45));
        productos.add(new Producto("camisa","002",50.26));
        productos.add(new Producto("chompa","003",91.58));
        productos.add(new Producto("zapatos","004",109.56));
        productos.add(new Producto("zapatillas","005",7.09));
        productos.add(new Producto("pantalon","006",8.79));
        productos.add(new Producto("terno","007",245.28));
        productos.add(new Producto("abrigo","008",54.41));
        productos.add(new Producto("gorro","009",5.24));
        return productos;
    }

    public void ponerProd(){
        listaProductosIniciales();
    }

}
