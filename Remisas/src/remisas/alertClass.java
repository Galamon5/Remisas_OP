/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author juanmanuel
 */
public class alertClass {
    
    public static void newError(String title,String content){
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle(title);
        a1.setContentText(content);
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    
    public static void newWarning(String title,String content){
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle(title);
        a1.setContentText(content);
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    
}
