package com.alura.jdbc.pruebas;
import com.alura.jdbc.factory.ConexionFactory;
import java.sql.Connection;
public class PruebasPoolConexiones {
    public static void main (String... args){
        ConexionFactory conexionFactory=new ConexionFactory();
        int i=0;
        while(i<20){
            Connection conexion=conexionFactory.conexion();
            System.out.println("Abriendo conexion numero: \n"+ (i+1) +"\n---------------------");
            i++;
        }
    }
}