/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.Conector;
import modelo.Usuario;
import static remisas.alertClass.*;

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
    private Conector conexion;
    
    //Metodos FXML
    @FXML
    private void logIn() throws Exception{
        textContra.setStyle("-fx-border-color: none");
        textUsuario.setStyle("-fx-border-color: none");
        if("".equals(textUsuario.getText())){
            newError("Error al leer el usuario","Por favor introdusca su usuario");
            textUsuario.setStyle("-fx-border-color: #d32f2f");
        }
        else if("".equals(textContra.getText())){
            newError("Error al leer la contraseña","Inserte su contraseña");
            textContra.setStyle("-fx-border-color: #d32f2f");
        }
        else{
            boolean res = false;
            conexion.startConnection();
            ArrayList<Usuario> usuarios = Usuario.getUsuarios(conexion.getConnection());
            for(int i=0;i<usuarios.size();i++){
                if(textUsuario.getText().equals(usuarios.get(i).getUsuario())
                        && textContra.getText().equals(usuarios.get(i).getPassword())){
                    res = true;
                    break;
                }
            }
            if(res)
                mainProgram.stageMenu();
            else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Usuario o contraseña incorrectos");
                a.setHeaderText(null);
                a.setContentText("Usuario o contraseña incorrectos, porfavor introducelos nuevamente");
                a.showAndWait();
            }
        }
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
    
    @FXML
    private void exit(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Salir");
        a.setHeaderText("Estas apunto de salir de la aplicación");
        a.setContentText("Estas seguro?");
        
        Optional<ButtonType> result = a.showAndWait();
        if(result.get()==ButtonType.OK){
            System.out.println("Saliendo de la aplicacion");
            System.exit(0);
        }
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
        conexion = new Conector();
        
    }    
    
}
