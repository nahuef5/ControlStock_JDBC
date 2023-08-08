package com.alura.jdbc.persistencia;

import com.alura.jdbc.entities.Categoria;
import com.alura.jdbc.entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CategoriaDAO {
    private Connection conexion;

    public CategoriaDAO(Connection conexion){
        this.conexion = conexion;
    }
    public List<Categoria> listarCategoria(){
        List<Categoria> categorias= new ArrayList<>();
        try{
            final PreparedStatement preparedStatement=conexion.prepareStatement("SELECT categoriaId, nombre FROM categoria");
            try(preparedStatement){ 
                final ResultSet resultset=preparedStatement.executeQuery();
                try(resultset){
                    while(resultset.next()){
                        var cat = new Categoria(resultset.getInt("categoriaId"),
                        resultset.getString("nombre"));
                        categorias.add(cat);
                    }
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return categorias;
    }

    /*public List<Categoria> listarConProductos() {
    List<Categoria> categorias= new ArrayList<>();
    try{
    final PreparedStatement preparedStatement=conexion.prepareStatement(
    "SELECT c.categoriaId, c.nombre, "
    +"p.id, p.nombre, p.cantidad "
    + "FROM categoria c "
    + "INNER JOIN producto p ON c.categoriaId = p.categoria_id"
    );
    try(preparedStatement){
    final ResultSet resultset=preparedStatement.executeQuery();
    try(resultset){
    while(resultset.next()){
    var cat = new Categoria(resultset.getInt("categoriaId"),
    resultset.getString("nombre"));
    categorias.add(cat);
    }
    }
    }
    }catch(SQLException e){
    throw new RuntimeException(e.getMessage());
    }
    return categorias;
    }*/
    public List<Categoria> listarConProductos() {
        List<Categoria> categorias= new ArrayList<>();
        try{
            final PreparedStatement preparedStatement=conexion.prepareStatement(
                    "SELECT c.categoriaId, c.nombre, "
                    +"p.id, p.nombre, p.cantidad "
                    + "FROM categoria c "
                    + "INNER JOIN producto p ON c.categoriaId = p.categoria_id"
                            );
            try(preparedStatement){ 
                final ResultSet resultset=preparedStatement.executeQuery();
                try(resultset){
                    while(resultset.next()){
                        Integer categoriaID = resultset.getInt("c.categoriaId");
                        String categoriaNombre=resultset.getString("c.nombre");
                        
                        var cat = categorias.stream()
                                .filter(c -> c.getCategoriaId().equals(categoriaID))
                                .findAny().orElseGet(
                                        ()->{
                                            Categoria Categoria = new Categoria(categoriaID,categoriaNombre);
                                            
                                            categorias.add(Categoria);
                                            return Categoria;//es un objeto
                                        }
                                        );
                        Producto producto = new Producto(resultset.getInt("p.id"),
                            resultset.getString("p.nombre"),
                            resultset.getInt("p.cantidad"));
                        cat.agregar(producto);
                    }
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return categorias;
    }
}