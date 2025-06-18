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
 * @author HP
 */
public class ProveedorDao {
     Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarProveedor(Proveedor prov) {
        
        String sql = "INSERT INTO proveedor (ruc, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
            try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getRuc());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getTelefono()); 
            ps.setString(4, prov.getDireccion());
            ps.setString(5, prov.getRazon());
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
    public boolean EliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id =?";
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
    public boolean ModificarProveedor (Proveedor prov){
        String sql = "UPDATE  proveedor SET ruc=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, prov.getRuc());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getTelefono());
            ps.setString(4, prov.getDireccion());
            ps.setString(5, prov.getRazon());
            ps.setInt(6, prov.getId());
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
    public Proveedor BuscarProveedor(String ruc){
        Proveedor prov =new Proveedor();
        String sql = "SELECT * FROM proveedor WHERE ruc = ?";
        try {
            con = cn.getConnection();
            ps =con.prepareStatement(sql);
            ps.setString(1, ruc);
            rs = ps.executeQuery();
            if (rs.next()) {
                prov.setNombre(rs.getString("nombre"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setDireccion(rs.getString("direccion"));
                prov.setRazon(rs.getString("razon"));    
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return prov;    
    }
    public List ListarProveedor(){
        List<Proveedor>  ListaProv = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Proveedor prov = new Proveedor();
                prov.setId(rs.getInt("id") );
                prov.setRuc(rs.getString("ruc"));
                prov.setNombre(rs.getString("nombre"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setDireccion(rs.getString("direccion"));
                prov.setRazon(rs.getString("razon"));
                ListaProv.add(prov);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaProv;
    }
   
}
