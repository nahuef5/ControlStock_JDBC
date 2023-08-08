package com.alura.jdbc.controller;
import com.alura.jdbc.entities.Categoria;
import com.alura.jdbc.entities.Producto;
import com.alura.jdbc.factory.ConexionFactory;
import com.alura.jdbc.persistencia.ProductoDAO;
import java.util.*;
public class ProductoController{
    private ProductoDAO productoDAO;
    
    public ProductoController() {
        this.productoDAO = new ProductoDAO(new ConexionFactory().conexion());
    }
    public void guardarProducto(Producto producto, Integer categoriaId){
        producto.setCategoria_Id(categoriaId);
        productoDAO.guardarProducto(producto);
    }
    public int modificarProducto(String nombre, String descripcion, Integer cantidad, Integer id) {
        return productoDAO.modificarProducto(nombre, descripcion, cantidad, id);
    }
    public int eliminarProducto(Integer id) {
        return productoDAO.eliminarProducto(id);
    }
    public List<Producto> listarProducto(){
        return productoDAO.listarProducto();
    }
    public List<Producto> listar(Categoria categoria){
        return productoDAO.listar(categoria.getCategoriaId());
    }
}