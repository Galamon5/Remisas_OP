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
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.ClienteFisico;
import modelo.ClienteMoral;
import modelo.Conector;
import modelo.Telefonos;

/**
 *
 * @author bigbo
 */
public class modifyClientController implements Initializable {
    
    //Variables FXML
    @FXML TextField nombre,correo,direccionLocal,direccionPersonal,rfc,noHacienda;
    @FXML TableView<Telefonos> tableTelefonos;
    @FXML TableColumn<Telefonos,String> tipo,telefono;
    @FXML RadioButton RadioMoral,RadioFisica;
    @FXML GridPane personaFisica,personaMoral;
    
    //variables locales
    private ObservableList<Telefonos> telefonosList;
    private Stage mainStage;
    private Mainprogram mainProgram;
    private Conector conexion;
    
    //Metodos FXML
    @FXML
    private void enableGridFisico(){
        personaFisica.setDisable(false);
        personaMoral.setDisable(true);
    }
    @FXML
    private void enableGridMoral(){
        personaMoral.setDisable(false);
        personaFisica.setDisable(true);
    }
    @FXML
    private void agregarTelefono() throws IOException{
        System.out.println("RFC: "+rfc.getText()+"  noHacienda: "+noHacienda.getText());
        if(rfc.getText().equals("") && noHacienda.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al agregar telefono");
            a.setHeaderText(null);
            a.setContentText("Antes de agregar telefonos, llena el campo de RFC o noHacienda segun corresponda");
            a.showAndWait();
        }else{
            int noCliente = 0;
            try{
                if(RadioFisica.isSelected())
                    noCliente = Integer.valueOf(rfc.getText());
                else if(RadioMoral.isSelected())
                    noCliente = Integer.valueOf(noHacienda.getText());
            }catch(NumberFormatException e){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error en id");
                a.setHeaderText(null);
                a.setContentText("Los campos de rfc y noHacienda solo aceptan valores numericos");
                a.showAndWait();
            }
            System.out.println(noCliente);
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
    }
    @FXML
    private void borrarTelefono(){
        Alert a;
        Telefonos x = tableTelefonos.getSelectionModel().getSelectedItem();
        conexion.startConnection();
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
    private void agregarCliente(){
        int res = 0;
        conexion.startConnection();
        try{
            if(RadioFisica.isSelected()){
                ClienteFisico x = new ClienteFisico(Integer.valueOf(rfc.getText()),nombre.getText(),
                                    direccionLocal.getText(),correo.getText(),direccionPersonal.getText(),"Fisico");
                res = x.agregarCliente(conexion.getConnection());
            }else{
                ClienteMoral x = new ClienteMoral(Integer.valueOf(noHacienda.getText()),nombre.getText(),direccionLocal.getText(),
                                    correo.getText(),"Moral");
                res = x.agregarCliente(conexion.getConnection());
            }
            if(res == 1){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Agregar Cliente");
                a.setHeaderText(null);
                a.setContentText("Se a agregado el cliente satisfactoriamente");
                a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error al agregar cliente");
                a.setHeaderText(null);
                a.setContentText("No se pudo agregar el cliente");
                a.showAndWait();
            }
        } catch(NumberFormatException ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error en id");
            a.setHeaderText(null);
            a.setContentText("Los campos de rfc y noHacienda solo aceptan valores numericos");
            a.showAndWait();
        }
    }
    @FXML
    private void cancelar(){
        this.mainStage.close();
    }
    
    //Metodos locales
    public void setStagePrincipal(Stage mainStage){
        this.mainStage = mainStage;
    }
    
    public void setPrincipal(Mainprogram mainProgram){
        this.mainProgram = mainProgram;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new Conector();
        telefonosList = FXCollections.observableArrayList();
        tableTelefonos.setItems(telefonosList);
        tipo.setCellValueFactory(new PropertyValueFactory<Telefonos,String>("tipo"));
        telefono.setCellValueFactory(new PropertyValueFactory<Telefonos,String>("telefono"));
    }
    
}
