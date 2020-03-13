/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import javafx.pkg4labs.main.JavaFX4Labs;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Muhammad Fahru Rozi
 */
public class LoginController implements Initializable {
   
    @FXML
    private TextField username;
    

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void register(javafx.scene.input.MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/Register.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.setScene(new Scene(root));
    }
    
}
