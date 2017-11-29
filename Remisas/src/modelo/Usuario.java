/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.*;
import javafx.scene.control.Alert;

/**
 *
 * @author bigbo
 */
public class Usuario {
    
    private StringProperty nombre,password,usuario;
    
    public Usuario(String nombre,String password,String usuario){
        this.nombre = new SimpleStringProperty(nombre);
        this.password = new SimpleStringProperty(password);
        this.usuario = new SimpleStringProperty(usuario);
    }
    
    public String getNombre(){
        return nombre.get();
    }
    
    public void setNombre(String nombre){
        this.nombre = new SimpleStringProperty(nombre);
    }
    
    public Property nombreProperty(){
        return nombre;
    }
    
    public String getPassword(){
        return password.get();
    }
    
    public void setPassword(String password){
        this.password = new SimpleStringProperty(password);
    }
    
    public Property passwordProperty(){
        return password;
    }
    
    public String getUsuario(){
        return usuario.get();
    }
    
    public void setUsuario(String usuario){
        this.usuario = new SimpleStringProperty(usuario);
    }
    
    public Property usuarioProperty(){
        return usuario;
    }
    
    public int addUsuario(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareStatement("insert into usuario (nombre,password,usuario) values (?,?,?)");
            instruction.setString(1, nombre.get());
            instruction.setString(2, password.get());
            instruction.setString(3, usuario.get());
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el usuario");
            a.setHeaderText("Error al intentar generar el nuevo usuario");
            a.setContentText(ex.getMessage());
            return 0;
        }
    }
    
    public static ArrayList<Usuario> getUsuarios(Connection connection){
        ArrayList<Usuario> usuarios = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select nombre,password,usuario from usuario");
            while(result.next()){
                usuarios.add(new Usuario(result.getString("nombre"),result.getString("password"),result.getString("usuario")));
            }
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el Statement");
            a.setHeaderText("Error al generar Statement");
            a.setContentText(ex.getMessage());
            a.showAndWait();
            return null;
        }
        
    }
    
}
