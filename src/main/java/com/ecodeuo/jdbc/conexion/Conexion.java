package com.ecodeuo.jdbc.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection (){
        Connection conexion = null;
        var url = "jdbc:mysql://localhost:3306/zona_fit";
        var usuario = "root";
        var password = "klaPAUsius9425";

        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e){
            System.out.println("error de tipo: "+ e.getMessage());
        }
        return conexion;

    }

    public static void main(String[] args) {
        var conexion = Conexion.getConnection();
        if (conexion != null)
            System.out.println("nos conectamos papa");
        else System.out.println("nos lleva");
    }


}
