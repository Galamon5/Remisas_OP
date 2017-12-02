/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.*;

/**
 * FXML Controller class
 *
 * @author bigbo
 */
public class DetallesClienteController implements Initializable {

    //Variables GUI
    @FXML private TextField nombre,direccionLocal,correo,direccionPersonal,rfc;
    @FXML private TextField nombreComprador,puesto,noHacienda;
    @FXML private TableView<Telefonos> tableTelefonos;
    @FXML private TableColumn<Telefonos,String> columnTipo,columnNumero;
    @FXML private GridPane personaFisica,personaMoral;
    
    //Variables locales
    private ObservableList<Telefonos> telefonosList;
    private Conector conexion;
    private Stage mainStage;
    private Mainprogram mainProgram;
    
    //Metodos locales
    public void setCliente(ClienteFisico cliente){
        llenarTabla(cliente.getNoCliente());
        nombre.setText(cliente.getNombre());
        correo.setText(cliente.getCorreo());
        direccionLocal.setText(cliente.getDireccionLocal());
        personaFisica.setDisable(false);
        direccionPersonal.setText(cliente.getDireccionPersonal());
        rfc.setText(""+cliente.getNoCliente()); 
    }
    public void setCliente(ClienteMoral cliente){
        llenarTabla(cliente.getNoCliente());
        nombre.setText(cliente.getNombre());
        correo.setText(cliente.getCorreo());
        direccionLocal.setText(cliente.getDireccionLocal());
        personaMoral.setDisable(false);
        nombreComprador.setText(cliente.getNombreComprador());
        puesto.setText(cliente.getPuesto());
        noHacienda.setText(""+cliente.getNoCliente());
    }
    private void llenarTabla(int noCliente){
        conexion = new Conector();
        conexion.startConnection();
        telefonosList = FXCollections.observableArrayList();
        Telefonos.llenarTabla(conexion.getConnection(), telefonosList,noCliente);
        tableTelefonos.setItems(telefonosList);
        columnTipo.setCellValueFactory(new PropertyValueFactory<Telefonos,String>("tipo"));
        columnNumero.setCellValueFactory(new PropertyValueFactory<Telefonos,String>("telefono"));
        conexion.closeConnection();
    }
    
    //Metodos FXML
    @FXML
    public void modifyButton() throws IOException{
        //mainProgram.stageModifyClient();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifyClientStage.fxml"));
        loader.load();
        modifyClientController controller = loader.getController();
        Parent root = loader.getRoot();
        //Stage stage = new Stage();
        mainStage.setTitle("Modificar Cliente");
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        controller.setStagePrincipal(mainStage);
        controller.setPrincipal(mainProgram);
        //stage.showAndWait();
        System.out.println("hola");
    }
    
    public void setPrincipalStage(Stage stage){
        mainStage = stage;
    }
    
    public void setPrincipal(Mainprogram mainProgram){
        this.mainProgram = mainProgram; 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
