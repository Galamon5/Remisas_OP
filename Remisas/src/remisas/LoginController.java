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

/**
 *
 * @author juanmanuel
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane anchorPane;
    
    private Stage mainStage;
    private Mainprogram mainProgram;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Me tocaste! D:");
        label.setText("Width: "+anchorPane.getWidth()+" Height: "+anchorPane.getHeight());
    }
    
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
