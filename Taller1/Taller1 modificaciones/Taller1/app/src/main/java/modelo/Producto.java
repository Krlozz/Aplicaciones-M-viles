package modelo;

import android.widget.Toast;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Producto implements Serializable {
    private String descripcion,qr;
    private double precio;

    public Producto() {
    }

    public Producto(String descripcion, String qr, double precio) {
        this.descripcion = descripcion;
        this.qr = qr;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    @Override
    public String toString() {
        return "Descripcion: " + this.descripcion + "   \nCódigo QR: " + this.qr + "    \nPrecio: " + this.precio + "$";
    }

    /*Producto productos[];

    public Producto [] cargarProductos(){
        productos = new Producto[] {new Producto("camiseta","001",2.45),
                new Producto("camisa","002",50.26),
                new Producto("chompa","003",91.58),
                new Producto("zapatos","004",109.56),
                new Producto("zapatillas","005",7.09),
                new Producto("pantalon","006",8.79),
                new Producto("terno","007",245.28),
                new Producto("abrigo","008",54.41),
                new Producto("gorro","009",5.24)
        };
        return productos;
    }*/

    /*public String[] descripcion() {
        String nombres[] = new String[cargarProductos().length];
        for(int i =0; i<nombres.length; i++){
            nombres [i] = cargarProductos()[i].getDescripcion();
        }
        return nombres;
    }*/


}
