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
public class Remisa extends Pedido {
    
    private  Date fechaRegistro,fechaLimite;
    private DoubleProperty montoDeuda;
    private StringProperty nombre;
    
     private Remisa(int idPedido,String nombre,int fk_cliente, Date fechaCreacion,Date fechaRegistro, Date fechaLimite, Double monto, Double montoDeuda,String nombreComprador,String puesto){
        super(idPedido,fk_cliente,fechaCreacion,nombreComprador,puesto,monto);
        this.fechaRegistro = fechaRegistro;
        this.fechaLimite= fechaLimite;
        this.montoDeuda=new SimpleDoubleProperty(montoDeuda);
        this.nombre = new SimpleStringProperty(nombre);
        super.setTipo("Remisa");
        
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
    
     public Date getFechaRegistro(){
        return fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro){
        this.fechaRegistro = fechaRegistro; 
    }
     public Date getFechaLimite(){
        return fechaLimite;
    }
    
    public void setFechaLimite(Date fechaLimite){
        this.fechaLimite = fechaLimite; 
    }
    
    public Double getMontoDeuda(){
        return montoDeuda.get();
    }
    
    public void setMontoDeuda(Double montoDeuda){
        this.montoDeuda = new SimpleDoubleProperty(montoDeuda); 
    }
    public Property montoDeudaProperty(){
        return montoDeuda;
    }
    
    public static void llenarTabla(Connection connection,ObservableList<Pedido> list){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select idPedido,nombre,fk_cliente,"
                    + "fechaCreacion,fechaRegistro,fechaLimite,sum(subtotal) as montoTotal,monto,nombreComprador,puesto "
                    + "from listaremisas group by idPedido");
            while(result.next())
                list.add(new Remisa(result.getInt("idPedido"),result.getString("nombre"),result.getInt("fk_cliente"),
                            result.getDate("fechaCreacion"),result.getDate("fechaRegistro"),result.getDate("fechaLimite"),
                            result.getDouble("montoTotal"),result.getDouble("monto"),result.getString("nombreComprador"),
                            result.getString("puesto")));
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Error al generar el statement");
            a.setHeaderText("Ocurrio un error al intentar generar el statement de la base de datos");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
}
