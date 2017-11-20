/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static remisas.Popupalert.display;

/**
 *
 * @author juanmanuel
 */
public class LoginController implements Initializable {
    
    //variables del archivo FXML
    @FXML
    private Label label;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField textUsuario;
    @FXML
    private PasswordField textContra;
    
    
    //variables locales
    private Stage mainStage;
    private Mainprogram mainProgram;
    
    //Metodos FXML
    @FXML
    private void logIn(){
        if("".equals(textUsuario.getText()))
            display("Error","Introduce tu usuario");
        else if("".equals(textContra.getText()))
            display("Error","Inserte contraseña");
        else
        System.out.println("Peticion de inicio de sesion:"+"\n"+"Usuario: "
                +textUsuario.getText()+"  Password: "+textContra.getText());
        textUsuario.setText("");
        textContra.setText("");
    }
    @FXML
    private void signIn() throws Exception{
        System.out.println("Generando registro de usuario");
        mainProgram.stageRegistroUsuario();
        System.out.println("Generado registro de usuario");
    }
    @FXML
    private void passwordRecovery(){
        System.out.println("Intentando recuperar la contrasenia");
        textUsuario.setText("");
        textContra.setText("");
    }
    
    
    //Metodos locales
    public void setPrincipal(Mainprogram stage){
        this.mainProgram = stage;
    }
    
    public void setStagePrincipal(Stage stage){
        this.mainStage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
