package com.alura.jdbc.controller;
import com.alura.jdbc.entities.Categoria;
import com.alura.jdbc.factory.ConexionFactory;
import com.alura.jdbc.persistencia.CategoriaDAO;
import java.util.*;
public class CategoriaController {
    private CategoriaDAO categoriaDAO;
    
    public CategoriaController(){
        this.categoriaDAO = new CategoriaDAO(new ConexionFactory().conexion());
    }
    public List<Categoria> listar(){
	return categoriaDAO.listarCategoria();
    }
    public List<Categoria> cargaReporte(){
        return this.categoriaDAO.listarConProductos();
    }
}