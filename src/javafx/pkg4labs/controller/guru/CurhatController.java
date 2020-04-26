/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class CurhatController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane scrlPane_chat;
    @FXML
    private ImageView imgv_fotoGuru;
    @FXML
    private Label lbl_namaGuru;
    @FXML
    private TextArea tarea_message;
    @FXML
    private ImageView imgv_send;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/ListCurhat.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void sendMessage(MouseEvent event) {
    }

    @FXML
    private void showChat(MouseEvent event) {
    }
    
}
