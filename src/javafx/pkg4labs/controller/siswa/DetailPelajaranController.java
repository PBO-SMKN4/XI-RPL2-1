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
import javafx.pkg4labs.model.JadwalPelajaran;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DetailPelajaranController implements Initializable {

    @FXML
    private TextField tf_namaMatpel;
    
    @FXML
    private TextField tf_jadwal;
    
    @FXML
    private TextField tf_jam;
    
    @FXML
    private TextField tf_nip;
    
    @FXML
    private TextField tf_namaGuru;
    
    @FXML
    private TextField tf_email;
    
    @FXML
    private TextField tf_noWa;
    
    @FXML
    private Circle img_guru;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
    }    
    
    public void showData(){
        JadwalPelajaran JP = new JadwalPelajaran(SessionId.getIdToDetailJadwal());
        tf_namaMatpel.setText(JP.getNamaMapel());
        tf_jam.setText(JP.getJam());
        tf_jadwal.setText(JP.getHari());
        tf_nip.setText(JP.getNip());
        tf_namaGuru.setText(JP.getGuruMapel());
        tf_email.setText(JP.getEmail());
        tf_noWa.setText(JP.getNoWa());
        img_guru.setFill(new ImagePattern(JP.getFoto()));
    }
    
    @FXML
    private void backHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/JadwalPelajaran.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
}