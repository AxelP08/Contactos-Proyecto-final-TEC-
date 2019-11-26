package com.fes.aragon.contactos.BD;

public class Contacto {

    private String id;
    private String nombres;
    private String apellidos;
    private String celular;
    private String casa;
    private String email;

    public Contacto() {
    }

    public Contacto(String nombres, String apellidos, String celular, String casa, String email) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.casa = casa;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
