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
public class Pedido {
    
    private IntegerProperty idPedido,fk_cliente;
    private  Date fechaCreacion;
    private StringProperty nombreComprador,puesto;
    
     private Pedido(int idPedido,int fk_cliente, Date fechaCreacion,String nombreComprador,String puesto){
        this.idPedido = new SimpleIntegerProperty(idPedido);
        this.fk_cliente = new SimpleIntegerProperty(fk_cliente);
        this.fechaCreacion = fechaCreacion;
         this.nombreComprador=new SimpleStringProperty(nombreComprador);
        this.puesto=new SimpleStringProperty(puesto);
         
        
    }
    private Pedido(Pedido pedido){
        this.idPedido = new SimpleIntegerProperty(pedido.getIdPedido());
        this.fk_cliente = new SimpleIntegerProperty(pedido.getFk_cliente());
        this.nombreComprador=new SimpleStringProperty(pedido.getNombreComprador());
        this.puesto=new SimpleStringProperty(pedido.getPuesto());
    }
    
     public String getNombreComprador(){
        return nombreComprador.get();
    }
    
    public void setNombreComprador(String nombreComprador){
        this.nombreComprador = new SimpleStringProperty(nombreComprador); 
    }
    public Property nombreCompradorProperty(){
        return nombreComprador;
    }
     public String getPuesto(){
        return puesto.get();
    }
    
    public void setPuesto(String puesto){
        this.puesto = new SimpleStringProperty(puesto); 
    }
    public Property puestoProperty(){
        return puesto;
    }
    
     public int getIdPedido(){
        return idPedido.get();
    }
    
    public void setIdPedido(int idPedido){
        this.idPedido = new SimpleIntegerProperty(idPedido); 
    }
    public Property idPedidoProperty(){
        return idPedido;
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
    
    public Date getFechaCreacion(){
        return fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion; 
    }
    
}
