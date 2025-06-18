/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    public Connection getConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3307/sistemaventasGYM?serverTimezone=UTC";
        con = DriverManager.getConnection(url, "root", "");
        return con;
    } catch (ClassNotFoundException e) {
        System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Error al conectar: " + e.getMessage());
    }
    return null;
}
    }
    