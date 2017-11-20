/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.Timer;

/**
 *
 * @author juanmanuel
 */
public class Mainprogram extends Application {
    
    private Stage window;
    
    //Inicio de Sesion
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Mainprogram.class.getResource("loginStage.fxml"));
        Parent root = (Parent) loader.load();
        window = stage;
        Scene scene = new Scene(root);
        window.setTitle("Inicio");
        window.setScene(scene);
        window.setMinWidth(800);
        window.setMinHeight(500);
        LoginController controller = loader.getController();
        controller.setPrincipal(this);
        stage.show();
    }
    public void stageRegistroUsuario() throws Exception {
        FXMLLoader loader = new FXMLLoader(Mainprogram.class.getResource("userRegisterStage.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        window.setTitle("Registro de usuario");
        window.setScene(scene);
        UserRegisterController controller = loader.getController();
        controller.setStagePrincipal(window);
    }
    
    //Registro de nuevo Usuario
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
