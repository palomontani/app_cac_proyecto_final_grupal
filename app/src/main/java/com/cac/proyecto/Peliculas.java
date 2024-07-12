package com.cac.proyecto;

public class Peliculas {

    //Atributos
    private int idPelicula;
    private String titulo;
    private String duracion;
    private String genero;
    private String imagen;

    //Getters and Setters
    
    public int getIdPelicula(){
        return idPelicula;
    }
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDuracion(){
        return duracion;
    }
    public void setDuracion(String duracion){
        this.duracion = duracion;
    }

    public String getGenero(){
        return genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }

    public String getImagen(){
        return imagen;
    }
    public void setImagen(String imagen){
        this.imagen = imagen;
    }


    //Constructores
    public Peliculas(){
    
    }

    public Peliculas(int idPelicula, String titulo, String duracion, String genero, String imagen){
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.imagen = imagen;
    }
}
