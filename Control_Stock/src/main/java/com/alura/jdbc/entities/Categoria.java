package com.alura.jdbc.entities;
import java.util.ArrayList;
import java.util.List;
public class Categoria {
    private Integer categoriaId;
    private String nombre;
    private List<Producto> productos=new ArrayList<>();
    
    public Categoria() {}
    public Categoria(Integer categoriaId, String nombre) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;
    }
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
    public Integer getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(Integer id) {
        this.categoriaId = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "Tipo: "+  nombre;
    }
    public void agregar(Producto producto) {
        this.productos.add(producto);
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}