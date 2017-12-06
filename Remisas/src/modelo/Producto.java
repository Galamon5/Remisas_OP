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
    
     public Producto(int idProducto,int stock, int cantidadPorCaja, String nombre, String marca, Double precio){
        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.stock = new SimpleIntegerProperty(stock);
        this.cantidadPorCaja = new SimpleIntegerProperty(cantidadPorCaja);
        this.nombre = new SimpleStringProperty(nombre);
        this.marca = new SimpleStringProperty(marca);
        this.precio = new SimpleDoubleProperty(precio);
        
    }
    public Producto(int stock, int cantidadPorCaja, String nombre, String marca, Double precio){
        
        this.idProducto = new SimpleIntegerProperty(0);
        this.stock = new SimpleIntegerProperty(stock);
        this.cantidadPorCaja = new SimpleIntegerProperty(cantidadPorCaja);
        this.nombre = new SimpleStringProperty(nombre);
        this.marca = new SimpleStringProperty(marca);
        this.precio = new SimpleDoubleProperty(precio);
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
    
    public static void llenarTabla(Connection connection,ObservableList<Producto> list){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select idProducto,nombre,marca,stock,cantidadPorCaja,precio from producto");
            while(result.next())
                list.add(new Producto(result.getInt("idProducto"),result.getInt("stock"),result.getInt("cantidadPorCaja"),
                                        result.getString("nombre"),result.getString("marca"),result.getDouble("precio")));
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el statement");
            a.setHeaderText(null);
            a.setContentText("Ocurrio un error al intentar generar el statement");
            a.showAndWait();
        }
    }
    
    public int addProducto(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareStatement("insert into producto "
                    + "(nombre,marca,stock,cantidadPorCaja,precio) values (?,?,?,?,?)");
            instruction.setString(1, nombre.get());
            instruction.setString(2, marca.get());
            instruction.setInt(3, stock.get());
            instruction.setInt(4, cantidadPorCaja.get());
            instruction.setDouble(5, precio.get());
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el Statement");
            a.setHeaderText(null);
            a.setContentText("Se genero un error al intentar generar el statement");
            a.showAndWait();
            return 0;
        }
    }
    
    public static int getIdPedido(Connection connection){
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select max(idProducto) as max from producto");
            while(result.next()){
                id = result.getInt("max");
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el Statement");
            a.setHeaderText("Se genero un error al intentar generar el statement");
            a.setContentText(ex.getMessage());
            a.showAndWait();
            return 0;
        }
    }
}

