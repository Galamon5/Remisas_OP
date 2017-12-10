/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.sql.*;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


/**
 *
 * @author Abril
 */
public class Pedido {
    
    private IntegerProperty idPedido,fk_cliente;
    private DoubleProperty monto;
    private  Date fechaCreacion;
    private StringProperty nombreComprador,puesto,tipo;
    
    public Pedido(int idPedido, int fk_cliente, Date fechaCreacion, String nombreComprador, String puesto,double monto){
        this.idPedido = new SimpleIntegerProperty(idPedido);
        this.fk_cliente = new SimpleIntegerProperty(fk_cliente);
        this.fechaCreacion = fechaCreacion;
        this.nombreComprador=new SimpleStringProperty(nombreComprador);
        this.puesto=new SimpleStringProperty(puesto);
        tipo = new SimpleStringProperty("Pedido");
        this.monto = new SimpleDoubleProperty(monto);
        
    }
    private Pedido(Pedido pedido){
        this.idPedido = new SimpleIntegerProperty(pedido.getIdPedido());
        this.fk_cliente = new SimpleIntegerProperty(pedido.getFk_cliente());
        this.nombreComprador=new SimpleStringProperty(pedido.getNombreComprador());
        this.puesto=new SimpleStringProperty(pedido.getPuesto());
        this.monto = new SimpleDoubleProperty(pedido.getMonto());
        tipo = new SimpleStringProperty("Pedido");
    }
    
    public double getMonto(){
        return monto.get();
    }
    
    public void setMonto(double monto){
        this.monto = new SimpleDoubleProperty(monto);
    }
    
    public Property montoProperty(){
        return monto;
    }
    
    public void setTipo(String tipo){
        this.tipo = new SimpleStringProperty(tipo);
    }
    
    public String getTipo(){
        return tipo.get();
    }
    
    public Property tipoProperty(){
        return tipo;
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
    
    public static void llenarTabla(Connection connection,ObservableList<Pedido> list){
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select idPedido,fk_cliente,fechaCreacion,nombre,puesto,sum(subtotal)"
                    + " as total from listapedidos group by idPedido");
            while(result.next())
                list.add(new Pedido(result.getInt("idPedido"),result.getInt("fk_cliente"),result.getDate("fechaCreacion")
                        ,result.getString("nombre"),result.getString("puesto"),result.getDouble("total")));
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Error al generar el statement");
            a.setHeaderText("Ocurrio un error al intentar generar el statement de la base de datos");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
}
