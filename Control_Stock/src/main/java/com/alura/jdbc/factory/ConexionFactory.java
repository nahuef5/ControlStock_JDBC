package com.alura.jdbc.factory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
public class ConexionFactory {
    private final String url="jdbc:mysql://localhost:3306/control_de_stock?useTimeZone=true&serverTimeZone=UTC";
    private final String root="root";
    private final String password="";
    private DataSource dataSource;
    
    public ConexionFactory() {
        var pooledDataSource=new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl(url);
        pooledDataSource.setUser(root);
        pooledDataSource.setPassword(password);
        
        pooledDataSource.setMaxPoolSize(10);
        
        this.dataSource=pooledDataSource;
    }
    public Connection conexion(){
        try{
            return this.dataSource.getConnection();
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage()+" -- "+ex.getLocalizedMessage());
        }
    }
}