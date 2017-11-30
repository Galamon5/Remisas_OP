/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

/**
 *
 * @author bigbo
 */
public class ClienteMoral {
    private IntegerProperty noCliente;
    private StringProperty nombre,direccionLocal,correo,nombreComprador,puesto,tipoCliente;
    private int pedidos=0,remisas=0;
    
    public ClienteMoral(int noCliente,String nombre,String direccionLocal,String correo,
                        String nombreComprador,String puesto,String tipoCliente){
        this.noCliente = new SimpleIntegerProperty(noCliente);
        this.nombre = new SimpleStringProperty(nombre);
        this.direccionLocal = new SimpleStringProperty(direccionLocal);
        this.correo = new SimpleStringProperty(correo);
        this.nombreComprador = new SimpleStringProperty(nombreComprador);
        this.puesto = new SimpleStringProperty(puesto);
        this.tipoCliente = new SimpleStringProperty(tipoCliente);
    }
    
    public ClienteMoral(ClienteMoral cliente,int pedidos,int remisas){
        this.noCliente = new SimpleIntegerProperty(cliente.getNoCliente());
        this.nombre = new SimpleStringProperty(cliente.getNombre());
        this.direccionLocal = new SimpleStringProperty(cliente.getDireccionLocal());
        this.correo = new SimpleStringProperty(cliente.getCorreo());
        this.nombreComprador = new SimpleStringProperty(cliente.getNombreComprador());
        this.puesto = new SimpleStringProperty(cliente.getPuesto());
        this.tipoCliente = new SimpleStringProperty(cliente.getTipoCliente());
        this.pedidos = pedidos;
        this.remisas = remisas;
    }
    
    public String getNombreComprador(){
        return nombre.get();
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
    
    public int getPedidos() {
        return pedidos;
    }

    public void setPedidos(int pedidos) {
        this.pedidos = pedidos;
    }

    public int getRemisas() {
        return remisas;
    }

    public void setRemisas(int remisas) {
        this.remisas = remisas;
    }
    
    public int getNoCliente(){
        return noCliente.get();
    }
    
    public void setNoCliente(int noCliente){
        this.noCliente = new SimpleIntegerProperty(noCliente); 
    }
    
    public Property noClienteProperty(){
        return noCliente;
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
    
    public String getDireccionLocal(){
        return direccionLocal.get();
    }
    
    public void setDireccionLocal(String direccionLocal){
        this.direccionLocal = new SimpleStringProperty(direccionLocal);
    }
    
    public Property direccionLocalProperty(){
        return direccionLocal;
    }
    
    public String getCorreo(){
        return correo.get();
    }
    
    public void setCorreo(String correo){
        this.correo = new SimpleStringProperty(correo);
    }
    
    public Property correoProperty(){
        return correo;
    }
    
    public String getTipoCliente(){
        return tipoCliente.get();
    }
    
    public void setTipoCliente(String tipoCliente){
        this.tipoCliente = new SimpleStringProperty(tipoCliente);
    }
    
    public Property tipoClienteProperty(){
        return tipoCliente;
    }
    
    public static void llenarTabla(Connection connection,ObservableList<ClienteMoral> list){
        int pedido=0,remisa=0;
        
    }
}
