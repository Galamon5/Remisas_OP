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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Conector;
import modelo.Telefonos;

/**
 *
 * @author bigbo
 */
public class AddTelController implements Initializable {
    
    //Variables FXML
    @FXML TextField telefono;
    @FXML ComboBox<String> tipo;
    
    //Variables locales
    private int noCliente;
    private Mainprogram mainProgram;
    private Stage mainStage;
    private Conector conexion;
    private ObservableList<Telefonos> list;
    
    //Metodos FXML
    @FXML
    public void aceptar(){
        int tel;
        System.out.println("noCliente al aceptar: "+noCliente);
        Alert a;
        Telefonos x = new Telefonos(noCliente,tipo.getSelectionModel().getSelectedItem(),telefono.getText());
        conexion.startConnection();
        int res = x.addTelefono(conexion.getConnection());
        tel = x.getIdTelefonos(conexion.getConnection(), x.getTelefono());
        conexion.closeConnection();
        x.setIdTelefonos(tel);
        if(res == 1){
            list.add(x);
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Nuevo telefono registrado");
            a.setHeaderText(null);
            a.setContentText("El telefono fue registrado satisfactoriamente");
            a.showAndWait();
            mainStage.close();
        } else {
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al guardar el telefono");
            a.setHeaderText(null);
            a.setContentText("Ocurrio un error al guardar el telefono");
            a.showAndWait();
        }
    }
    @FXML
    public void cancelar(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Cancelando operacion");
        a.setHeaderText(null);
        a.setContentText("¿Estas seguro de que quieres cancelar la operacion?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK){
            mainStage.close();
        }
        
    }
    
    //Metodos locales
    public void setNoCliente(int noCliente){
        this.noCliente = noCliente;
    }
    public void setPrincipal(Mainprogram mainProgram){
        this.mainProgram = mainProgram;
    }
    public void setStage(Stage mainStage){
        this.mainStage = mainStage;
    }
    
    public void setList(ObservableList<Telefonos> list){
        this.list = list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new Conector();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Local","Movil","Otros");
        tipo.setItems(list);
        System.out.println("noCliente: "+noCliente);
    }
    
}
