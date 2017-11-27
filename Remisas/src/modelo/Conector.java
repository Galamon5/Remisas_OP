/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author bigbo
 */
public class Conector {
    private Connection connection;
    private String url = "jdbc:mysql://localhost/pruebajava";
    private String User = "root";
    private String password = "";

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void startConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, User, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error en la base de datos");
            a.setHeaderText("Error al intentar importar el controlador de la base de datos");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error en la base de datos");
            a.setHeaderText("Error al intentar conectar con la base de datos");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
    
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error en la base de datos");
            a.setHeaderText("Error al intentar cerrar conexion con la base de datos");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
    
}


