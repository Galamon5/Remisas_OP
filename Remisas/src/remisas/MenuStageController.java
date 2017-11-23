/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author juanmanuel
 */
public class MenuStageController implements Initializable {
    
    //Variables locales
    private Mainprogram mainProgram;
    
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

    //Metodos locales
    public void setPrincipal(Mainprogram stage){
        this.mainProgram = stage;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
