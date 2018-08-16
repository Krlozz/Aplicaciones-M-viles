package modelo;

import java.io.Serializable;

public class Evento implements Serializable {

    private String nombreEvento, lugar, qr;
    private int anio, mes, dia;
    private String hora, minuto, segundo;

    public Evento() {    }

    public Evento(String nombreEvento, String lugar, String qr, int anio, int mes, int dia, String hora, String minuto, String segundo) {
        this.nombreEvento = nombreEvento;
        this.lugar = lugar;
        this.qr = qr;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    @Override
    public String toString() {
        return "Nombre del evento='" + nombreEvento +
                ", lugar='" + lugar +
                ", Fecha=" + anio +
                "/" + mes +
                "/" + dia +
                "   " + hora +
                ":" + minuto +
                ":" + segundo;
    }





}
