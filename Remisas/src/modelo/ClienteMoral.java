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
 * @author bigbo
 */
public class ClienteMoral {
    private IntegerProperty noCliente;
    private StringProperty nombre,direccionLocal,correo,tipoCliente;
    private int pedidos=0,remisas=0,rPagadas=0;
    
    public ClienteMoral(int noCliente,String nombre,String direccionLocal,String correo,String tipoCliente){
        this.noCliente = new SimpleIntegerProperty(noCliente);
        this.nombre = new SimpleStringProperty(nombre);
        this.direccionLocal = new SimpleStringProperty(direccionLocal);
        this.correo = new SimpleStringProperty(correo);
        this.tipoCliente = new SimpleStringProperty(tipoCliente);
    }
    
    public ClienteMoral(ClienteMoral cliente,int pedidos,int remisas,int rPagadas){
        this.noCliente = new SimpleIntegerProperty(cliente.getNoCliente());
        this.nombre = new SimpleStringProperty(cliente.getNombre());
        this.direccionLocal = new SimpleStringProperty(cliente.getDireccionLocal());
        this.correo = new SimpleStringProperty(cliente.getCorreo());   
        this.tipoCliente = new SimpleStringProperty(cliente.getTipoCliente());
        this.pedidos = pedidos;
        this.remisas = remisas;
        this.rPagadas = rPagadas;
    }

    public int getRPagadas() {
        return rPagadas;
    }

    public void setRPagadas(int rPagadas) {
        this.rPagadas = rPagadas;
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
        int pedido=0,remisa=0,pagado=0;
        ArrayList<ClienteMoral> clientes = new ArrayList();
        ArrayList idClientes = new ArrayList();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "select noCliente,nombre,direccionLocal,correo,"
                            + "tipoCliente from clientemoral");
            while(result.next()){
                idClientes.add(result.getInt("noCliente"));
                clientes.add(new ClienteMoral(result.getInt("noCliente"),result.getString("nombre"),
                                result.getString("direccionLocal"),result.getString("correo"),
                                result.getString("tipoCliente"))); 
            }
            for(int i=0;i<idClientes.size();i++){
                result = statement.executeQuery("select count(idPedido) as numPedidos from pedido where fk_cliente="+idClientes.get(i));
                while(result.next())
                    pedido = result.getInt("numPedidos");
                result = statement.executeQuery("select count(idPedido) as numRemisas from remisa where fk_cliente="+idClientes.get(i));
                while(result.next())
                    remisa = result.getInt("numRemisas");
                result = statement.executeQuery("select count(idPedido) as numPagadas from remisapagada where fk_cliente="+idClientes.get(i));
                while(result.next())
                    pagado = result.getInt("numPagadas");
                for(int j=0;j<clientes.size();j++){
                    if(clientes.get(i).getNoCliente()==(int)idClientes.get(j)){
                        list.add(new ClienteMoral(clientes.get(j),pedido,remisa,pagado));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteMoral.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al generar el Statement");
            a.setHeaderText("Error al generar el Statement");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
    
    public int actualizarCliente(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareStatement("update clientemoral set nombre=?, "
                    + "direccionLocal=?, correo=?, tipoCliente=? where noCliente=?");
            instruction.setInt(1, noCliente.get());
            instruction.setString(1, nombre.get());
            instruction.setString(2, direccionLocal.get());
            instruction.setString(3, correo.get());
            instruction.setString(4, tipoCliente.get());
            instruction.setInt(5, noCliente.get());
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteMoral.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al actualizar el cliente");
            a.setHeaderText("Error al intentar actualizar el cliente");
            a.setContentText(ex.getMessage());
            a.showAndWait();
            return 0;
        }
        
    }
    
    public int borrarCliente(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareStatement("delete from ClienteMoral where noCliente = ?");
            instruction.setInt(1, noCliente.get());
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Telefonos.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al borrar cliente");
            a.setHeaderText("Error al intentar borrar cliente");
            a.setContentText(ex.getMessage());
            a.showAndWait();
            return 0;
        }
    }
    
    public int agregarCliente(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareStatement("insert into clientemoral values (?,?,?,?,?)");
            instruction.setInt(1, noCliente.get());
            instruction.setString(2, nombre.get());
            instruction.setString(3, direccionLocal.get());
            instruction.setString(4, correo.get());
            instruction.setString(5, tipoCliente.get());
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteMoral.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al agregar al cliente");
            a.setHeaderText("Error al intentar agregar al cliente");
            a.setContentText(ex.getMessage());
            a.showAndWait();
            return 0;
        }
    }
}
