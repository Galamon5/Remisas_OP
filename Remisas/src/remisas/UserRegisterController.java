/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanmanuel
 */
public class UserRegisterController implements Initializable {
    
    //local variables
    Mainprogram mainProgram;
    Stage mainStage;
    
    //FXML variables
    @FXML
    private TextField textRegistNombre, textRegistUsuario, textConfirmCode;
    @FXML
    private PasswordField textRegistPass, textConfirmPass;
    
    //Metodos FXML
    @FXML
    private void createNewUser() throws Exception{
        System.out.println("Generando Usuario \n"+"Nombre: "+textRegistNombre.getText()
        +" Usuario: "+textRegistUsuario.getText()+" Password: "+textConfirmPass.getText()
        +" Code: "+textConfirmCode.getText());
        System.out.println("regresando a inicio");
        mainProgram.stageInicio();
    }
    @FXML
    private void cancelNewUser() throws Exception{
        System.out.println("Cancelando la operacion");
        mainProgram.stageInicio();
    }
    
    
    //Metodos locales
    public void setPrincipal(Mainprogram stage){
        this.mainProgram = stage;
    }
    
    public void setStagePrincipal(Stage stage){
        this.mainStage = stage;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
