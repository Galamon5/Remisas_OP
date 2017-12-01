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
public class Registro {
    
    private IntegerProperty idRegistro,fk_Pedido;
    private  StringProperty motivo;
    private DoubleProperty monto;
    private Date fecha;
    
     private Registro(int idRegistro,int fk_Pedido, String motivo,Double monto, Date fecha){
        this.idRegistro = new SimpleIntegerProperty(idRegistro);
        this.fk_Pedido = new SimpleIntegerProperty(fk_Pedido);
        this.motivo = new SimpleStringProperty(motivo);
        this.monto = new SimpleDoubleProperty(monto);
        this.fecha= fecha;
        
    }
    private Registro(Registro registro){
        this.idRegistro = new SimpleIntegerProperty(registro.getIdRegistro());
        this.fk_Pedido = new SimpleIntegerProperty(registro.getFk_Pedido());
        this.motivo = new SimpleStringProperty(registro.getMotivo());
        this.monto = new SimpleDoubleProperty(registro.getMonto());
    }
    
     public int getIdRegistro(){
        return idRegistro.get();
    }
    
    public void setIdRegistro(int idRegistro){
        this.idRegistro = new SimpleIntegerProperty(idRegistro); 
    }
    public Property idRegistroProperty(){
        return idRegistro;
    }
    
    public int getFk_Pedido(){
        return fk_Pedido.get();
    }
    
    public void setFk_Pedido(int fk_Pedido){
        this.fk_Pedido = new SimpleIntegerProperty(fk_Pedido); 
    }
    public Property fk_PedidoProperty(){
        return fk_Pedido;
    }
    
    public String getMotivo(){
        return motivo.get();
    }
    
    public void setMotivo(String motivo){
        this.motivo = new SimpleStringProperty(motivo); 
    }
    public Property motivoProperty(){
        return motivo;
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
    
    public Date getFecha(){
        return fecha;
    }
    
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    
    

    
}