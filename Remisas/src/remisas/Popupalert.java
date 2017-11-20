/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author bigbo
 */
public class Popupalert {
    public static void display(String title, String message){
        Stage ventana = new Stage();
        ventana.initModality(Modality.WINDOW_MODAL);
        ventana.setTitle(title);
        Label label = new Label(message);
        Button closebutton = new Button("Cerrar");
        closebutton.setOnAction(e -> ventana.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,closebutton);
        layout.setAlignment(Pos.CENTER);
        layout.setMinWidth(250);
        layout.setMinHeight(100);
        
        Scene scene = new Scene(layout);
        ventana.setScene(scene);
        ventana.showAndWait();
    }
}
