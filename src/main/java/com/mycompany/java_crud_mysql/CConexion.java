/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandro
 */
public class CConexion {

    Connection conectar = null;

    String usuario = "root";
    String password = "039812";
    String bd = "bdescuela";
    String servidor = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + servidor + ":" + puerto + "/" + bd;

    //Crear metodo que me de informacion de la coneccion
    public Connection estableceConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, password);
            //JOptionPane.showMessageDialog(null, "Conexión exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos!, " + e.toString());
        }

        return conectar;
    }

}
