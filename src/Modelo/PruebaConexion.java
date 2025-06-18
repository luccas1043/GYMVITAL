/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;

/**
 *
 * @author TOSHIBA
 */
public class PruebaConexion {

    public static void main(String[] args) {
        Conexion cn = new Conexion();
        if (cn.getConnection() == null) {
            System.out.println("No hay conexión");
        } else {
            System.out.println("Sí hay conexión");
        }
    }

}
