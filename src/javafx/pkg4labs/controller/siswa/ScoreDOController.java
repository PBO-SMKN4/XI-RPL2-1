/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class ScoreDOController implements Initializable {

    @FXML
    private Label lbl_scoreDO;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Siswa.getScoreDO()<100&&Siswa.getScoreDO()>9) {
            lbl_scoreDO.setLayoutX(70);
        }else if(Siswa.getScoreDO()<10){
            lbl_scoreDO.setLayoutX(95);
        }else{
            lbl_scoreDO.setLayoutX(47);
        }
        lbl_scoreDO.setText(String.valueOf(Siswa.getScoreDO()));
    }    

    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root)); 
    }
    
}
