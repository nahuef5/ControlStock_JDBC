package com.alura.jdbc.persistencia;
import com.alura.jdbc.entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class ProductoDAO {
    private Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }
    public void guardarProducto(Producto producto) {
        try {
            PreparedStatement prepareStatement;
                prepareStatement = conexion.prepareStatement(
                        "INSERT INTO PRODUCTO "
                        + "(nombre, descripcion, cantidad, categoria_id)"
                        + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try (prepareStatement) {
                prepareStatement.setString(1, producto.getNombre());
                prepareStatement.setString(2, producto.getDescripcion());
                prepareStatement.setInt(3, producto.getCantidad());
                prepareStatement.setInt(4, producto.getCategoria_Id());
    
                prepareStatement.execute();
    
                final ResultSet resultSet = prepareStatement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        producto.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el producto: %s", producto));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Producto> listarProducto() {
        List<Producto> resultado = new ArrayList<>();
        try {
            final PreparedStatement prepareStatement = conexion
                    .prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto");
    
            try (prepareStatement) {
                prepareStatement.execute();
    
                final ResultSet resultSet = prepareStatement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("descripcion"),
                                resultSet.getInt("cantidad")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
    public int eliminarProducto(Integer id) {
        try {
            final PreparedStatement statement = conexion.prepareStatement("DELETE FROM producto WHERE id = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int modificarProducto(String nombre, String descripcion, Integer cantidad, Integer id) {
        try {
            final PreparedStatement statement = conexion.prepareStatement(
                    "UPDATE `producto` SET "
                    + "`nombre` = ?"
                    + ", `descripcion` = ?"
                    + ", `cantidad`= ?"
                    + " WHERE `producto`.`id` = ?;");
            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Producto> listar(Integer categoriaId) {
        List<Producto> resultado = new ArrayList<>();
        try {
            final PreparedStatement prepareStatement = conexion
                    .prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto WHERE categoria_id=?");
                try (prepareStatement) {
                prepareStatement.setInt(1, categoriaId);
                prepareStatement.execute();
    
                final ResultSet resultSet = prepareStatement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("descripcion"),
                                resultSet.getInt("cantidad")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}