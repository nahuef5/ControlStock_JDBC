package com.alura.jdbc.pruebas;
import com.alura.jdbc.factory.ConexionFactory;
import java.sql.Connection;
import java.sql.SQLException;
public class PruebaConexion {
    public static void main(String[] args) throws SQLException {
        Connection conexion=new ConexionFactory().conexion();
        System.out.println("Cerrando la conexion");
        conexion.close();
    }
}