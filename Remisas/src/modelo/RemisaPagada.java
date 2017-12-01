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
public class RemisaPagada {
    
    private IntegerProperty idPedido,fk_cliente;
    private  Date fechaCreacion, fechaRemisaPagada;
    private DoubleProperty montoPagado;
    
     private RemisaPagada(int idPedido,int fk_cliente, Date fechaCreacion,Date fechaRemisaPagada,Double montoPagado){
        this.idPedido = new SimpleIntegerProperty(idPedido);
        this.fk_cliente = new SimpleIntegerProperty(fk_cliente);
        this.fechaCreacion = fechaCreacion;
        this.fechaRemisaPagada = fechaRemisaPagada;
        this.montoPagado=new SimpleDoubleProperty(montoPagado); 
    }
    private RemisaPagada(RemisaPagada remisaPagada){
        this.idPedido = new SimpleIntegerProperty(remisaPagada.getIdPedido());
        this.fk_cliente = new SimpleIntegerProperty(remisaPagada.getFk_cliente());
        this.montoPagado=new SimpleDoubleProperty(remisaPagada.getMontoPagado());
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
     public Date getFechaRemisaPagada(){
        return fechaRemisaPagada;
    }
    
    public void setFechaRemisaPagada(Date fechaRemisaPagada){
        this.fechaRemisaPagada = fechaRemisaPagada; 
    }
   
    
    public Double getMontoPagado(){
        return montoPagado.get();
    }
    
    public void setMontoPagado(Double montoPagado){
        this.montoPagado = new SimpleDoubleProperty(montoPagado); 
    }
    public Property montoPagadoProperty(){
        return montoPagado;
    }
    
    
}