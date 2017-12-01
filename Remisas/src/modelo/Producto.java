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
public class Producto {
    
    private IntegerProperty idProducto,stock,cantidadPorCaja;
    private  StringProperty nombre,marca;
    private DoubleProperty precio;
    
     private Producto(int idProducto,int stock, int cantidadPorCaja, String nombre, String marca, Double precio){
        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.stock = new SimpleIntegerProperty(stock);
        this.cantidadPorCaja = new SimpleIntegerProperty(cantidadPorCaja);
        this.nombre = new SimpleStringProperty(nombre);
        this.marca = new SimpleStringProperty(marca);
        this.precio = new SimpleDoubleProperty(precio);
        
    }
    private Producto(Producto producto){
        this.idProducto = new SimpleIntegerProperty(producto.getIdProducto());
        this.stock = new SimpleIntegerProperty(producto.getStock());
        this.cantidadPorCaja = new SimpleIntegerProperty(producto.getCantidadPorCaja());
        this.nombre = new SimpleStringProperty(producto.getNombre());
        this.marca = new SimpleStringProperty(producto.getMarca());
        this.precio = new SimpleDoubleProperty(producto.getPrecio());
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
    
    public int getStock(){
        return stock.get();
    }
    
    public void setStock(int stock){
        this.stock = new SimpleIntegerProperty(stock); 
    }
    public Property stockProperty(){
        return stock;
    }
    
    public int getCantidadPorCaja(){
        return cantidadPorCaja.get();
    }
    
    public void setCantidadPorCaja(int cantidadPorCaja){
        this.cantidadPorCaja = new SimpleIntegerProperty(cantidadPorCaja); 
    }
    public Property cantidadPorCajaProperty(){
        return cantidadPorCaja;
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
    
    public String getMarca(){
        return marca.get();
    }
    
    public void setMarca(String marca){
        this.marca = new SimpleStringProperty(marca); 
    }
    public Property marcaProperty(){
        return marca;
    }
    
    public Double getPrecio(){
        return precio.get();
    }
    
    public void setPrecio(Double precio){
        this.precio = new SimpleDoubleProperty(precio); 
    }
    public Property precioProperty(){
        return precio;
    }
    

    
}

