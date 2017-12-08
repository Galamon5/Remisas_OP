/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remisas;

import java.net.URL;
import java.util.Optional;
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
public class ModifyInventoryController implements Initializable {
       
    //Variables locales
    private Mainprogram mainProgram;
    private Stage mainStage;
    private Conector conexion;
    private Producto producto;
    private ObservableList<Producto> list;
    private TableView<Producto> table;
    private int idProducto;
    
    //Variables FXML
    @FXML private TextField nombre,marca,precio;
    @FXML private Spinner stock,cantidadCaja;
    @FXML private Button btnModificar,btnCancelar,btnGuardar;
    
    //metodos FXML
    @FXML
    public void modificar(){
        nombre.setEditable(true);
        marca.setEditable(true);
        precio.setEditable(true);
        stock.setEditable(true);
        cantidadCaja.setEditable(true);
        btnGuardar.setDisable(false);
        btnModificar.setDisable(true);
    }
    @FXML
    public void cancelar(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Cancelando operación");
        a.setHeaderText("Estas apunto de cancelar la operacion");
        a.setContentText("¿Estas seguro?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK)
            mainStage.close();
    }
    @FXML
    public void guardar(){
        System.out.println("Guardar: "+producto);
        Alert a;
        try{
            Producto x = new Producto(idProducto,Integer.valueOf(stock.getEditor().getText()),Integer.valueOf(cantidadCaja.getEditor().getText()),
            nombre.getText(),marca.getText(),Double.valueOf(precio.getText()));
            conexion.startConnection();
            int res = x.actualizarProducto(conexion.getConnection());
            conexion.closeConnection();
            if(res==1){
                list.set(table.getSelectionModel().getSelectedIndex(), x);
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Producto actualizado");
                a.setHeaderText(null);
                a.setContentText("El producto se actualizo satisfactoriamente");
                a.showAndWait();
                mainStage.close();
            } else {
                a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error al actualizar el producto");
                a.setHeaderText(null);
                a.setContentText("Error al intentar actualizar el producto");
                a.showAndWait();
            }
        } catch(NumberFormatException ex){
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error al actualizar el producto");
            a.setHeaderText("Ocurrio un error al intentar actualizar el producto");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
    
    //metodos locales
    public void setProducto(Producto producto){
        nombre.setText(producto.getNombre());
        marca.setText(producto.getMarca());
        precio.setText(""+producto.getPrecio());
        stock.setValueFactory(new IntegerSpinnerValueFactory(1,500,producto.getStock(),1));
        cantidadCaja.setValueFactory(new IntegerSpinnerValueFactory(1,1000,producto.getCantidadPorCaja(),1));
        idProducto = producto.getIdProducto();
    }
    
    public void setPrincipal(Mainprogram mainProgram){
        this.mainProgram = mainProgram;
    }
    
    public void setStagePrincipal(Stage stage){
        mainStage = stage;
    }
    
    public void setObservableList(ObservableList<Producto> list){
        this.list = list;
    }
    
    public void setTableView(TableView<Producto> table){
        this.table = table;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialize: "+producto);
        conexion = new Conector();
        nombre.setEditable(false);
        marca.setEditable(false);
        precio.setEditable(false);
        stock.setEditable(false);
        cantidadCaja.setEditable(false);
        btnGuardar.setDisable(true);
    }
    
}
