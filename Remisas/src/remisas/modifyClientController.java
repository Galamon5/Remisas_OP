/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author bigbo
 */
public class modifyClientController implements Initializable {
    
    //variables locales
    private Stage mainStage;
    private Mainprogram mainProgram;
    
    public void setStagePrincipal(Stage mainStage){
        this.mainStage = mainStage;
    }
    
    public void setPrincipal(Mainprogram mainProgram){
        this.mainProgram = mainProgram;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
