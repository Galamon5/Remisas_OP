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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.Conector;
import modelo.Usuario;
import static remisas.alertClass.*;

/**
 * FXML Controller class
 *
 * @author juanmanuel
 */
public class UserRegisterController implements Initializable {
    
    //local variables
    private Mainprogram mainProgram;
    private Stage mainStage;
    private Conector conexion;
    
    //FXML variables
    @FXML
    private TextField textRegistNombre, textRegistUsuario, textConfirmCode;
    @FXML
    private PasswordField textRegistPass, textConfirmPass;
    
    //Metodos FXML
    @FXML
    private void createNewUser() throws Exception{
        
        textRegistUsuario.setStyle("-fx-border-color: none");
        textRegistNombre.setStyle("-fx-border-color: none");
        textConfirmCode.setStyle("-fx-border-color: none");
        textRegistPass.setStyle("-fx-border-color: none");
        textConfirmPass.setStyle("-fx-border-color: none");
        
        if(textRegistUsuario.getText().equals("")){
            newError("Error","Introduce tu nombre de usuario");
            textRegistUsuario.setStyle("-fx-border-color: #d32f2f");
        }
        else if(textRegistNombre.getText().equals("")){
            newError("Error","El campo Nombre es obligatorio");
            textRegistNombre.setStyle("-fx-border-color: #d32f2f");
        }
        else if(textRegistPass.getText().equals("")){
            newError("Error", "Introduce una contraseña");
            textRegistPass.setStyle("-fx-border-color: #d32f2f");
        }
        else if(textConfirmPass.getText().equals("")){
            newError("Error","Confirma tu contraseña ingresandola en el campo -Confirmar contraseña-");
            textConfirmPass.setStyle("-fx-border-color: #d32f2f");
        }
        else if(!"1234".equals(textConfirmCode.getText())){
            newError("Codigo no valido","El codigo no es el mismo proporcionado por el gerente");
            textConfirmCode.setStyle("-fx-border-color: #b32f2f");
        }
        else if(!textConfirmPass.getText().equals(textRegistPass.getText())){
            newError("Error","Las contraseñas no coinciden");
            textConfirmPass.setStyle("-fx-border-color: #b32f2f");
        }
        else{
            Alert a;
            Usuario x = new Usuario(textRegistNombre.getText(),textRegistPass.getText(),textRegistUsuario.getText());
            conexion.startConnection();
            int res = x.addUsuario(conexion.getConnection());
            conexion.closeConnection();
            if(res == 1){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Usuario registrado");
                a.setHeaderText(null);
                a.setContentText("El cliente fue registrado satisfactoriamente");
                a.showAndWait();
                mainProgram.stageInicio();
            }
            else {
                a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error al generar cliente");
                a.setHeaderText(null);
                a.setContentText("Ocurrio un error al generar el cliente");
                a.showAndWait();
            }
            
        }
    }
    @FXML
    private void cancelNewUser() throws Exception{
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("Abortar operacion");
        a.setContentText("¿Seguro que quieres cancelar la operacion?");
        a.setHeaderText(null);
        
        Optional<ButtonType> result = a.showAndWait();
        if(result.get()==ButtonType.OK){
            System.out.println("Cancelando operacion");
            mainProgram.stageInicio();
        }
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
        conexion = new Conector();
    }    
    
}
