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
    private ObservableList<Clientes> clientesList;
    private Conector conexion;
    
    //variables FXML control de clientes
    @FXML TableView<ClienteFisico> tableClienteF;
    @FXML TableColumn<ClienteFisico,Integer> columnIdClienteF;
    @FXML TableColumn<ClienteFisico,String> columnNombreF;
    @FXML TableColumn<ClienteFisico,Integer> columnPedidosF;
    @FXML TableColumn<ClienteFisico,String> columnCorreoF;
    @FXML TableView<ClienteMoral> tableClienteM;
    @FXML TableColumn<ClienteMoral,Integer> columnIdClienteM;
    @FXML TableColumn<ClienteMoral,String> columnNombreM;
    @FXML TableColumn<ClienteMoral,Integer> columnPedidosM;
    @FXML TableColumn<ClienteMoral,String> columnCorreoM;
    
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
    private void showDetallesCliente() throws Exception{
        /*Clientes x = tableClienteF.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("detallesClienteStage.fxml"));
        loader.load();
        DetallesClienteController controller = loader.getController();
        controller.setCliente(x);
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Informacion Cliente");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();*/
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
        /*conexion.startConnection();
        clientesList =  FXCollections.observableArrayList();
        Clientes.llenarTabla(conexion.getConnection(), clientesList);
        tableCliente.setItems(clientesList);
        columnIdCliente.setCellValueFactory(new PropertyValueFactory<Clientes,Integer>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<Clientes,String>("nombre"));
        columnTipoCliente.setCellValueFactory(new PropertyValueFactory<Clientes,Integer>("tipo"));
        columnPedidos.setCellValueFactory(new PropertyValueFactory<Clientes,Integer>("pedidos"));
        columnCorreo.setCellValueFactory(new PropertyValueFactory<Clientes,String>("correo"));*/
    }    
    
}
