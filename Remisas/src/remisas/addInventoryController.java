/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;
import modelo.*;

/**
 *
 * @author bigbo
 */
public class addInventoryController implements Initializable {
    
    //Variables FXML
    @FXML TextField nombre,marca,precio;
    @FXML Spinner stock,cantidadCaja;
    
    //Metodos FXML
    @FXML
    public void agregar(){
        int id = 0;
        try{
            Producto x = new Producto(Integer.valueOf(stock.getEditor().getText()),Integer.valueOf(cantidadCaja.getEditor().getText()),
                                        nombre.getText(),marca.getText(),Double.valueOf(precio.getText()));
            conexion.startConnection();
            int res = x.addProducto(conexion.getConnection());
            id = Producto.getIdPedido(conexion.getConnection());
            conexion.closeConnection();
            x.setIdProducto(id);
            if(res==1){
                list.add(x);
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Producto agregado");
                a.setHeaderText(null);
                a.setContentText("Producto agregado satisfactoriamente");
                a.showAndWait();
            } else {
                a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error al generar el producto");
                a.setHeaderText("Error en la base de datos");
                a.setContentText("Ocurrio un error al intentar generar el nuevo producto");
                a.showAndWait();
            }
        } catch(NumberFormatException ex){
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error en el formato");
            a.setHeaderText("Los campos STOCK, CANTIDAD POR CAJA y PRECIO deben ser llenados unicamente con numeros");
            a.setContentText("Introduce los valores correctamente");
            a.showAndWait();
        }
        mainStage.close();
    }
    @FXML
    public void cancelar(){
        
    }
    
    //Variables locales
    private Mainprogram mainProgram;
    private Stage mainStage;
    private Alert a;
    private Conector conexion;
    private ObservableList<Producto> list;
    
    //Metodos locales
    public void setPrincipal(Mainprogram mainProgram){
        this.mainProgram = mainProgram;
    }
    public void setStagePrincipal(Stage stage){
        mainStage = stage;
    }
    public void setObservableList(ObservableList<Producto> list){
        this.list = list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new Conector();
        stock.setValueFactory(new IntegerSpinnerValueFactory(1,500,1,1));
        cantidadCaja.setValueFactory(new IntegerSpinnerValueFactory(1,1000,1,1));
    }
    
}
