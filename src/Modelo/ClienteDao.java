/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author TOSHIBA
 */
public class ClienteDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarCliente(Cliente cl) {
        
        String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion, suscripcion, meses) VALUES (?,?,?,?,?,?)";
            try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono()); 
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getSuscripcion());
            ps.setString(6, cl.getMeses());
            ps.execute();
            return true;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
                return false;
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
     }
    public List ListarCliente(){
        List<Cliente>  ListaCl = new ArrayList();
        String sql = "SELECT * FROM clientes";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id") );
                cl.setDni(rs.getString("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setSuscripcion(rs.getString("suscripcion"));
                cl.setMeses(rs.getNString("meses"));
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }
    public boolean EliminarCliente(int id){
        String sql = "DELETE FROM clientes WHERE id =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }         
        }
    }
    public boolean ModificarCliente (Cliente cl){
        String sql = "UPDATE  clientes SET dni=?, nombre=?, telefono=?, direccion=?, suscripcion=?, meses=? WHERE id=?";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getSuscripcion());
            ps.setString(6, cl.getMeses());
            ps.setInt(7, cl.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
            System.out.println(e.toString());
            }
        }
        
    }
    public Cliente BuscarCliente(String dni){
        Cliente cl =new Cliente();
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try {
            con = cn.getConnection();
            ps =con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setSuscripcion(rs.getString("suscripcion"));    
                cl.setMeses(rs.getString("meses"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;    
    }
}
