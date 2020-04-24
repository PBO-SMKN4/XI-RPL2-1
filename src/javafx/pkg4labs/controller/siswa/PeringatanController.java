/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.Peringatan;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class PeringatanController implements Initializable {
    
    Peringatan peringatan = new Peringatan(Siswa.getNis());

    @FXML
    private Label label_isi;
    
    @FXML
    private Label label_judul;
    
    @FXML
    private Label label_tanggal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       showPeringatan();
       
        System.out.println(peringatan.getJudul());
         
        System.out.println(peringatan.getIsiPeringatan());
        
        System.out.println(peringatan.getTanggalPeringatan());
        
    }    
    
    public void showPeringatan(){
      
        label_judul.setText(peringatan.getJudul());
        label_isi.setText(peringatan.getIsiPeringatan());
        label_tanggal.setText(peringatan.getTanggalPeringatan());
        
    }
    
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}