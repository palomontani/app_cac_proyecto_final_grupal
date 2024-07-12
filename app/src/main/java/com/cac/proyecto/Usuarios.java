package com.cac.proyecto;

import java.sql.Date;

public class Usuarios {

    private int idUsuario;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private Date fechaNac;
    private String imgPerfil;
    private String rol;
    private boolean estaEliminado;

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaNac() {
        return fechaNac;
    }
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getImgPerfil() {
        return imgPerfil;
    }
    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isEstaEliminado() {
        return estaEliminado;
    }
    public void setEstaEliminado(boolean estaEliminado) {
        this.estaEliminado = estaEliminado;
    }

    
    public Usuarios() {
    }

    public Usuarios(int idUsuario, String nombreUsuario, String nombre, String apellido, String email, String contrasena, Date fechaNac, String imgPerfil, String rol, boolean estaEliminado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.fechaNac = fechaNac;
        this.imgPerfil = imgPerfil;
        this.rol = rol;
        this.estaEliminado = estaEliminado;
    }
}
