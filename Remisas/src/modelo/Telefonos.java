/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


/**
 *
 * @author Abril
 */
public class Telefonos {
    
    private IntegerProperty idTelefonos,fk_cliente;
    private  StringProperty tipo,telefono;
    
    public Telefonos(int idTelefonos,int fk_cliente, String tipo,String telefono){
        this.idTelefonos = new SimpleIntegerProperty(idTelefonos);
        this.fk_cliente = new SimpleIntegerProperty(fk_cliente);
        this.tipo = new SimpleStringProperty (tipo);
        this.telefono = new SimpleStringProperty(telefono); 
    }
    public Telefonos(int fk_cliente, String tipo,String telefono){
        this.fk_cliente = new SimpleIntegerProperty(fk_cliente);
        this.tipo = new SimpleStringProperty (tipo);
        this.telefono = new SimpleStringProperty(telefono); 
    }
    
     public int getIdTelefonos(){
        return idTelefonos.get();
    }
    
    public void setIdTelefonos(int idTelefonos){
        this.idTelefonos = new SimpleIntegerProperty(idTelefonos); 
    }
    public Property idTelefonosProperty(){
        return idTelefonos;
    }
    
    public int getFk_cliente(){
        return fk_cliente.get();
    }
    
    public void setFk_cliente(int fk_cliente){
        this.fk_cliente = new SimpleIntegerProperty(fk_cliente); 
    }
    public Property fk_clienteProperty(){
        return fk_cliente;
    }
    
     public String getTipo(){
        return tipo.get();
    }
    
    public void setTipo(String tipo){
        this.tipo = new SimpleStringProperty(tipo);
    }
    
    public Property tipoProperty(){
        return tipo;
    }
     public String getTelefono(){
        return telefono.get();
    }
    
    public void setTelefono(String telefono){
        this.telefono = new SimpleStringProperty(telefono);
    }
    
    public Property telefonoProperty(){
        return telefono;
    }
    
    public static void llenarTabla(Connection connection, ObservableList<Telefonos> list,int idCliente){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select idTelefonos,tipo,telefono,fk_cliente from telefonos where fk_cliente="+idCliente);
            while(result.next())
                list.add(new Telefonos(result.getInt("idTelefonos"),result.getInt("fk_cliente"),result.getString("tipo"),result.getString("telefono")));
        } catch (SQLException ex) {
            Logger.getLogger(Telefonos.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el Statement");
            a.setHeaderText("Error al generar el Statement");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
    
    public int addTelefono(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareStatement(
                    "insert into telefonos (tipo,telefono,fk_cliente) values (?,?,?)");
            instruction.setString(1, tipo.get());
            instruction.setString(2, telefono.get());
            instruction.setInt(3, fk_cliente.get());
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Telefonos.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el usuario");
            a.setHeaderText("Error al intentar generar un nuevo telefono");
            a.setContentText(ex.getMessage());
            return 0;
        }
    }
    
    public int borrarTelefono(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareStatement("delete from telefonos where idTelefonos = ?");
            instruction.setInt(1, idTelefonos.get());
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Telefonos.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al borrar el telefono");
            a.setHeaderText("Error al intentar borrar el telefono");
            a.setContentText(ex.getMessage());
            return 0;
        }
    }
    
    public int getIdTelefonos(Connection connection,String telefono){
        int res=0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select idTelefonos from telefonos where telefono="+telefono);
            while(result.next())
                res = result.getInt("idTelefonos");
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(Telefonos.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el Statement");
            a.setHeaderText("Error al generar el Statement");
            a.setContentText(ex.getMessage());
            a.showAndWait();
            return 0;
        }
        
    }
    
}