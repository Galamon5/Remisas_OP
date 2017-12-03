/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.*;

/**
 * FXML Controller class
 *
 * @author juanmanuel
 */
public class MenuStageController implements Initializable {
    
    //Variables locales
    private Mainprogram mainProgram;
    private ObservableList<ClienteFisico> clientesFisicoList;
    private ObservableList<ClienteMoral> clientesMoralList;
    private Conector conexion;
    
    //variables FXML control de clientes
    @FXML TableView<ClienteFisico> tableClienteF;
    @FXML TableColumn<ClienteFisico,Integer> columnIdClienteF;
    @FXML TableColumn<ClienteFisico,String> columnNombreF;
    @FXML TableColumn<ClienteFisico,Integer> columnPedidosF;
    @FXML TableColumn<ClienteFisico,String> columnCorreoF;
    @FXML TableColumn<ClienteFisico,Integer> columnRemisasF;
    @FXML TableColumn<ClienteFisico,Integer> columnPagadasF;
    @FXML TableView<ClienteMoral> tableClienteM;
    @FXML TableColumn<ClienteMoral,Integer> columnIdClienteM;
    @FXML TableColumn<ClienteMoral,String> columnNombreM;
    @FXML TableColumn<ClienteMoral,Integer> columnPedidosM;
    @FXML TableColumn<ClienteMoral,String> columnCorreoM;
    @FXML TableColumn<ClienteMoral,Integer> columnRemisasM;
    @FXML TableColumn<ClienteMoral,Integer> columnPagadasM;
    
    //varibale FXML control de inventario;
    @FXML TableView tableInventario;
    @FXML TableColumn columnIdInventario;
    @FXML TableColumn columnProducto;
    @FXML TableColumn columnMarca;
    @FXML TableColumn columnPrecio;
    @FXML TableColumn columnCantidad;
    @FXML TableColumn columnModificar;
    @FXML TableColumn columnBorrarInventario;
    
    //variable FXML control de pedidos
    @FXML TableView tablePedido;
    @FXML TableColumn columnIdPedido;
    @FXML TableColumn columnTipoPedido;
    @FXML TableColumn columnCliente;
    @FXML TableColumn columnFecha;
    @FXML TableColumn columnMonto;
    @FXML TableColumn columnDetallesPedido;
    @FXML TableColumn columnBorrarPedido;
    
    //Metodos FXML
    @FXML
    private void logOut() throws Exception{
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("Cerrando sesion");
        a.setHeaderText("Estas apunto de cerrar sesion");
        a.setContentText("¿Estas seguro?");
        
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK){
            System.out.println("Cerrando sesion");
            mainProgram.stageInicio();
        }
    }
    
    @FXML
    private void unSelectMoral() {
        tableClienteM.setFocusTraversable(false);
        tableClienteF.setFocusTraversable(true);
    }
    @FXML
    private void unSelectFisico(){
        tableClienteF.setFocusTraversable(false);
        tableClienteM.setFocusTraversable(true);
    }
    
    @FXML
    private void showDetallesCliente() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("detallesClienteStage.fxml"));
        loader.load();
        DetallesClienteController controller = loader.getController();
        if(tableClienteF.isFocusTraversable()){
            System.out.println("Es Fisico");
            ClienteFisico x = tableClienteF.getSelectionModel().getSelectedItem();
            controller.setCliente(x);
            controller.setClienteFisicoList(clientesFisicoList,tableClienteF);
        }
        else if(tableClienteM.isFocusTraversable()){
            System.out.println("Es moral");
            ClienteMoral x = tableClienteM.getSelectionModel().getSelectedItem();
            controller.setCliente(x);
            controller.setClienteMoralList(clientesMoralList,tableClienteM);
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("Selecciona un cliente");
        }
        
        
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Informacion Cliente");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller.setPrincipalStage(stage);
        controller.setPrincipal(mainProgram);
        stage.showAndWait();
    }
    @FXML
    private void borrarCliente(){
        int res = 0;
        System.out.println("Entro");
        conexion.startConnection();
        if(tableClienteF.isFocusTraversable()){
            System.out.println("Es Fisico");
            ClienteFisico x = tableClienteF.getSelectionModel().getSelectedItem();
            res = x.borrarCliente(conexion.getConnection());
            if(res == 1){
                this.clientesFisicoList.remove(tableClienteF.getSelectionModel().getSelectedIndex());
            }
        }
        else if(tableClienteM.isFocusTraversable()){
            System.out.println("Es moral");
            ClienteMoral x = tableClienteM.getSelectionModel().getSelectedItem();
            res = x.borrarCliente(conexion.getConnection());
            if(res == 1){
                this.clientesMoralList.remove(tableClienteM.getSelectionModel().getSelectedIndex());
            }
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("Selecciona un cliente");
        }
        if(res == 1){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Se borro cliente");
            a.setHeaderText(null);
            a.setContentText("El cliente se borro satisfactoriamente");
            a.showAndWait();
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Ocurrio un error al borrar el cliente");
            a.setHeaderText(null);
            a.setContentText("Ocurrio un error al borrar el cliente");
            a.showAndWait();
        }
        conexion.closeConnection();
    }

    //Metodos locales
    public void setPrincipal(Mainprogram stage){
        this.mainProgram = stage;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conector();
        //TableView cliente fisico
        conexion.startConnection();
        clientesFisicoList =  FXCollections.observableArrayList();
        ClienteFisico.llenarTabla(conexion.getConnection(), clientesFisicoList);
        tableClienteF.setItems(clientesFisicoList);
        columnIdClienteF.setCellValueFactory(new PropertyValueFactory<ClienteFisico,Integer>("noCliente"));
        columnNombreF.setCellValueFactory(new PropertyValueFactory<ClienteFisico,String>("nombre"));
        columnRemisasF.setCellValueFactory(new PropertyValueFactory<ClienteFisico,Integer>("remisas"));
        columnPedidosF.setCellValueFactory(new PropertyValueFactory<ClienteFisico,Integer>("pedidos"));
        columnCorreoF.setCellValueFactory(new PropertyValueFactory<ClienteFisico,String>("correo"));
        columnPagadasF.setCellValueFactory(new PropertyValueFactory<ClienteFisico,Integer>("rPagadas"));
        conexion.closeConnection();
        
        //TableView Cliente moral
        conexion.startConnection();
        clientesMoralList = FXCollections.observableArrayList();
        ClienteMoral.llenarTabla(conexion.getConnection(), clientesMoralList);
        tableClienteM.setItems(clientesMoralList);
        columnIdClienteM.setCellValueFactory(new PropertyValueFactory<ClienteMoral,Integer>("noCliente"));
        columnNombreM.setCellValueFactory(new PropertyValueFactory<ClienteMoral,String>("nombre"));
        columnRemisasM.setCellValueFactory(new PropertyValueFactory<ClienteMoral,Integer>("remisas"));
        columnPedidosM.setCellValueFactory(new PropertyValueFactory<ClienteMoral,Integer>("pedidos"));
        columnCorreoM.setCellValueFactory(new PropertyValueFactory<ClienteMoral,String>("correo"));
        columnPagadasM.setCellValueFactory(new PropertyValueFactory<ClienteMoral,Integer>("rPagadas"));
        
        conexion.closeConnection();
    }    
    
}
