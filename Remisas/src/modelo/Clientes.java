/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author bigbo
 */
public class Clientes {
    
    private IntegerProperty id,id_telefono,tipo,pedidos;
    private StringProperty nombre,direccion,correo;
    
    public Clientes(int id, String nombre, String direccion, String correo,int tipo,int pedidos, int id_telefono){
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.direccion = new SimpleStringProperty(direccion);
        this.correo = new SimpleStringProperty(correo);
        this.tipo = new SimpleIntegerProperty(tipo);
        this.pedidos = new SimpleIntegerProperty(pedidos);
        this.id_telefono = new SimpleIntegerProperty(id_telefono);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public int getId_telefono() {
        return id_telefono.get();
    }

    public void setId_telefono(int id_telefono) {
        this.id_telefono = new SimpleIntegerProperty(id_telefono);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion = new SimpleStringProperty(direccion);
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String correo) {
        this.correo = new SimpleStringProperty(correo);
    }
    
    public int getTipo(){
        return tipo.get();
    }
    
    public void setTipo(int tipo){
        this.tipo = new SimpleIntegerProperty(tipo);
    }
    
    public int getPedidos(){
        return pedidos.get();
    }
    
    public void setPedidos(int pedidos){
        this.pedidos = new SimpleIntegerProperty(pedidos);
    }
    
    public IntegerProperty idProperty(){
        return id;
    }
    
    public IntegerProperty id_telefonoProperty(){
        return id_telefono;
    }
    
    public StringProperty nombreProperty(){
        return nombre;
    }
    
    public StringProperty direccionProperty(){
        return direccion;
    }
    
    public StringProperty correoProperty(){
        return correo;
    }
    
    public IntegerProperty tipoProperty(){
        return tipo;
    } 
    
    public IntegerProperty pedidosProperty(){
        return pedidos;
    }
    
    public static void llenarTabla(Connection connection,ObservableList<Clientes> list){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select id,nombre,direccion,correo,tipo,pedidos,id_telefono from clientes");
            while(result.next()){
                list.add(new Clientes(result.getInt("id"),
                                    result.getString("nombre"),
                                    result.getString("direccion"),
                                    result.getString("correo"),
                                    result.getInt("tipo"),
                                    result.getInt("pedidos"),
                                    result.getInt("id_telefono")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el Statement");
            a.setHeaderText("Error al generar el Statement");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
}
