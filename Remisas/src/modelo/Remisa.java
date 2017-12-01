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
public class Remisa {
    
    private IntegerProperty idPedido,fk_cliente;
    private  Date fechaCreacion, fechaRegistro,fechaLimite;
    private DoubleProperty montoTotal,monto;
    
     private Remisa(int idPedido,int fk_cliente, Date fechaCreacion,Date fechaRegistro, Date fechaLimite, Double montoTotal, Double monto){
        this.idPedido = new SimpleIntegerProperty(idPedido);
        this.fk_cliente = new SimpleIntegerProperty(fk_cliente);
        this.fechaCreacion = fechaCreacion;
        this.fechaRegistro = fechaRegistro;
        this.fechaLimite= fechaLimite;
        this.monto=new SimpleDoubleProperty(monto);
        this.montoTotal=new SimpleDoubleProperty(montoTotal);
        
        
    }
    private Remisa(Remisa remisa){
        this.idPedido = new SimpleIntegerProperty(remisa.getIdPedido());
        this.fk_cliente = new SimpleIntegerProperty(remisa.getFk_cliente());
        this.monto=new SimpleDoubleProperty(remisa.getMonto());
        this.montoTotal=new SimpleDoubleProperty(remisa.getMontoTotal());
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
    
    public Double getMonto(){
        return monto.get();
    }
    
    public void setMonto(Double monto){
        this.monto = new SimpleDoubleProperty(monto); 
    }
    public Property montoProperty(){
        return monto;
    }
    
    public Double getMontoTotal(){
        return montoTotal.get();
    }
    
    public void setMontoTotal(Double montoTotal){
        this.montoTotal = new SimpleDoubleProperty(montoTotal); 
    }
    public Property montoTotalProperty(){
        return montoTotal;
    }
}