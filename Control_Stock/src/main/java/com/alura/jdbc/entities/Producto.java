package com.alura.jdbc.entities;

public class Producto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Integer Categoria_Id;
    public Producto(){}
    public Producto(Integer id, String nombre, String descripcion, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }
    public Producto(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }
    public Producto(Integer id, String nombre, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public Integer getCategoria_Id() {
        return Categoria_Id;
    }
    public void setCategoria_Id(Integer Categoria_Id) {
        this.Categoria_Id = Categoria_Id;
    }
}