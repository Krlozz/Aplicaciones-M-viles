package com.carlosayalat.exameniib;

public class Usuario {

    private String numMatr;
    private String pass;
    private String status;

    public String getNumMatr() {
        return numMatr;
    }

    public void setNumMatr(String numMatr) {
        this.numMatr = numMatr;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "numMatr='" + numMatr + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
