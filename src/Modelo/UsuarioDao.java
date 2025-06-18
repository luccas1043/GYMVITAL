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
public class UsuarioDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarUsuario(Usuario usu) {
        
        String sql = "INSERT INTO usuarios (dni, nombre, telefono, direccion, correo, pass, rol) VALUES (?,?,?,?,?,?,?)";
            try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getDni());
            ps.setString(2, usu.getNombre());
            ps.setString(3, usu.getTelefono()); 
            ps.setString(4, usu.getDireccion());
            ps.setString(5, usu.getCorreo());
            ps.setString(6, usu.getPass());
            ps.setString(7, usu.getRol());
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
    public List ListarUsuario(){
        List<Usuario>  ListaUsu = new ArrayList();
        String sql = "SELECT * FROM Usuarios";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id") );
                usu.setDni(rs.getString("dni"));
                usu.setNombre(rs.getString("nombre"));
                usu.setTelefono(rs.getString("telefono"));
                usu.setDireccion(rs.getString("direccion"));
                usu.setCorreo(rs.getString("correo"));
                usu.setPass(rs.getNString("pass"));
                usu.setRol(rs.getString("rol"));
                ListaUsu.add(usu);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaUsu;
    }
    public boolean EliminarUsuario(int id){
        String sql = "DELETE FROM usuarios WHERE id =?";
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
    public boolean ModificarUsuario (Usuario usu){
        String sql = "UPDATE  usuarios SET dni=?, nombre=?, telefono=?, direccion=?, correo=?, pass=?, rol=? WHERE id=?";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, usu.getDni());
            ps.setString(2, usu.getNombre());
            ps.setString(3, usu.getTelefono());
            ps.setString(4, usu.getDireccion());
            ps.setString(5, usu.getCorreo());
            ps.setString(6, usu.getPass());
            ps.setString(7, usu.getRol());
            ps.setInt(8, usu.getId());
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
    public Usuario BuscarUsuario(String dni){
        Usuario usu =new Usuario();
        String sql = "SELECT * FROM usuarios WHERE dni = ?";
        try {
            con = cn.getConnection();
            ps =con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                usu.setNombre(rs.getString("nombre"));
                usu.setTelefono(rs.getString("telefono"));
                usu.setDireccion(rs.getString("direccion"));
                usu.setCorreo(rs.getString("correo"));    
                usu.setPass(rs.getString("pass"));
                usu.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return usu;    
    }
}
