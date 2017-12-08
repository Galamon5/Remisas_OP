/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML private TextField noHacienda;
    @FXML private TableView<Telefonos> tableTelefonos;
    @FXML private TableColumn<Telefonos,String> columnTipo,columnNumero;
    @FXML private GridPane personaFisica,personaMoral;
    @FXML private Button btnGuardar,btnQuitar,btnAgregar;
    
    //Variables locales
    private ObservableList<Telefonos> telefonosList;
    private Conector conexion;
    private Stage mainStage;
    private Mainprogram mainProgram;
    private int noCliente;
    private String tipo;
    private ObservableList<ClienteMoral> moralList;
    private ObservableList<ClienteFisico> fisicoList;
    private TableView<ClienteMoral> tableMoral;
    private TableView<ClienteFisico> tableFisico;
    
    //Metodos locales
    
    public void setPrincipalStage(Stage stage){
        mainStage = stage;
    }
    
    public void setPrincipal(Mainprogram mainProgram){
        this.mainProgram = mainProgram; 
    }
    
    public void setClienteMoralList(ObservableList<ClienteMoral> moralList,TableView<ClienteMoral> tableMoral){
        this.moralList = moralList;
        this.tableMoral = tableMoral;
    }
    
    public void setClienteFisicoList(ObservableList<ClienteFisico> fisicoList,TableView<ClienteFisico> tableFisico){
        this.fisicoList = fisicoList;
        this.tableFisico = tableFisico;
    }
    
    public void setCliente(ClienteFisico cliente){
        llenarTabla(cliente.getNoCliente());
        nombre.setText(cliente.getNombre());
        correo.setText(cliente.getCorreo());
        direccionLocal.setText(cliente.getDireccionLocal());
        personaFisica.setDisable(false);
        direccionPersonal.setText(cliente.getDireccionPersonal());
        rfc.setText(""+cliente.getNoCliente());
        noCliente = cliente.getNoCliente();
        tipo = cliente.getTipoCliente();
    }
    public void setCliente(ClienteMoral cliente){
        llenarTabla(cliente.getNoCliente());
        nombre.setText(cliente.getNombre());
        correo.setText(cliente.getCorreo());
        direccionLocal.setText(cliente.getDireccionLocal());
        personaMoral.setDisable(false);
        noHacienda.setText(""+cliente.getNoCliente());
        noCliente = cliente.getNoCliente();
        tipo = cliente.getTipoCliente();
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
        nombre.setEditable(true); correo.setEditable(true);
        direccionLocal.setEditable(true); direccionPersonal.setEditable(true);
        btnGuardar.setDisable(false); btnQuitar.setDisable(false); btnAgregar.setDisable(false);
    }
    @FXML
    public void agregarTelefono() throws IOException{
        System.out.println("noCliente:"+noCliente);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addTel.fxml"));
        loader.load();
        AddTelController controller = loader.getController();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Agregar Telefono");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller.setNoCliente(noCliente);
        controller.setPrincipal(mainProgram);
        controller.setList(telefonosList);
        controller.setStage(stage);
        stage.showAndWait();
    }
    @FXML
    public void quitarTelefono(){
        Alert a;
        Telefonos x = tableTelefonos.getSelectionModel().getSelectedItem();
        conexion.startConnection();
        System.out.println("idTelefono: "+x.getIdTelefonos()+"  fk_cliente:"+x.getFk_cliente());
        int res = x.borrarTelefono(conexion.getConnection());
        conexion.closeConnection();
        if(res == 1){
            telefonosList.remove(tableTelefonos.getSelectionModel().getSelectedIndex());
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Se borro el telefono");
            a.setHeaderText(null);
            a.setContentText("El telefono fue borrado satisfactoriamente");
            a.showAndWait();
            }
            else {
                a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error al borrar el telefono");
                a.setHeaderText(null);
                a.setContentText("Ocurrio un error al borrar el telefono");
                a.showAndWait();
            }
    }
    @FXML
    public void guardarCambios(){
        Alert a;
        int res = 0;
        if(tipo.equals("Moral")){
            ClienteMoral x = new ClienteMoral(noCliente,nombre.getText(),direccionLocal.getText(),correo.getText(),tipo);
            conexion.startConnection();
            res = x.actualizarCliente(conexion.getConnection());
            conexion.closeConnection();
            if(res == 1){
                moralList.set(tableMoral.getSelectionModel().getSelectedIndex(), x);
            }
        }
        else{
            ClienteFisico x = new ClienteFisico(noCliente,nombre.getText(),direccionLocal.getText(),correo.getText(),
                    direccionPersonal.getText(),tipo);
            conexion.startConnection();
            res = x.actualizarCliente(conexion.getConnection());
            conexion.closeConnection();
            if(res == 1){
                fisicoList.set(tableFisico.getSelectionModel().getSelectedIndex(), x);
            }
        }
        
        if(res == 1){
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Se actualizo el cliente");
            a.setHeaderText(null);
            a.setContentText("El cliente fue actualizado satisfactoriamente");
            a.showAndWait();
            }
            else {
                a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error al actualizar el cliente");
                a.setHeaderText(null);
                a.setContentText("Ocurrio un error al actualizar el cliente");
                a.showAndWait();
            }
        
        nombre.setEditable(false); correo.setEditable(false);
        direccionLocal.setEditable(false); direccionPersonal.setEditable(false);
        btnGuardar.setDisable(true); btnQuitar.setDisable(true); btnAgregar.setDisable(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
