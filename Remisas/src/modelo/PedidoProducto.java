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
public class PedidoProducto {
    
    private IntegerProperty idPedido,cantidad,idProducto;
    
     private PedidoProducto(int idPedido,int cantidad, int idProducto){
        this.idPedido = new SimpleIntegerProperty(idPedido);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.idProducto = new SimpleIntegerProperty(idProducto);
        
    }
    private PedidoProducto(PedidoProducto pedidoProducto){
        this.idPedido = new SimpleIntegerProperty(pedidoProducto.getIdPedido());
        this.cantidad = new SimpleIntegerProperty(pedidoProducto.getCantidad());
        this.idProducto=new SimpleIntegerProperty(pedidoProducto.getIdProducto());
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
    
    public int getCantidad(){
        return cantidad.get();
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = new SimpleIntegerProperty(cantidad); 
    }
    public Property cantidadProperty(){
        return cantidad;
    }
    
     public int getIdProducto(){
        return idProducto.get();
    }
    
    public void setIdProducto(int idProducto){
        this.idProducto = new SimpleIntegerProperty(idProducto); 
    }
    public Property idProductoProperty(){
        return idProducto;
    }
    

    
}
