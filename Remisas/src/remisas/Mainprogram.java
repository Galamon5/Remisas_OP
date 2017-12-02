/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.Timer;
import modelo.Clientes;

/**
 *
 * @author juanmanuel
 */
public class Mainprogram extends Application {
    
    private Stage window;
    private Clientes cliente;
    
    //Inicio de Sesion
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Mainprogram.class.getResource("loginStage.fxml"));
        Parent root = (Parent) loader.load();
        window = stage;
        Scene scene = new Scene(root);
        window.setTitle("Inicio de sesion");
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
        controller.setPrincipal(this);
    }
    public void stageInicio() throws Exception {
        FXMLLoader loader = new FXMLLoader(Mainprogram.class.getResource("loginStage.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        window.setTitle("Inicio de sesion");
        window.setScene(scene);
        LoginController controller = loader.getController();
        controller.setPrincipal(this);
    }
    public void stageMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(Mainprogram.class.getResource("menuStage.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        window.setTitle("Menu");
        window.setScene(scene);
        MenuStageController controller = loader.getController();
        controller.setPrincipal(this);
    }
    
    public void stageModifyClient() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifyClientStage.fxml"));
        loader.load();
        modifyClientController controller = loader.getController();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Modificar Cliente");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller.setStagePrincipal(stage);
        controller.setPrincipal(this);
        stage.showAndWait();
        System.out.println("hola");
    }
    
    //Registro de nuevo Usuario
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
