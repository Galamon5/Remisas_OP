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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Clientes;

/**
 * FXML Controller class
 *
 * @author bigbo
 */
public class DetallesClienteController implements Initializable {

    //Variables GUI
    @FXML TextField nombre,direccionLocal,correo,direccionPersonal,rfc;
    @FXML TextField nombreComprador,puesto,noHacienda;
    @FXML TableView tableTelefonos;
    @FXML TableColumn columnTipo,columnNumero;
    @FXML GridPane personaFisica,personaMoral;
    
    //Variables locales
    
    //Metodos locales
    public void setCliente(Clientes cliente){
        nombre.setText(cliente.getNombre());
        correo.setText(cliente.getCorreo());
        if(cliente.getTipo()==1)
            personaFisica.setDisable(false);
        else
            personaMoral.setDisable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
