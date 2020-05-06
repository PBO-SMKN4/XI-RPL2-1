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
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.InfoTugasBerita;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class DetailInformasiController implements Initializable {

    @FXML
    private TextArea tarea_isi;
    @FXML
    private TextField tf_judul;
    @FXML
    private TextField tf_pengirim;
    @FXML
    private TextField tf_perihal;
    @FXML
    private TextField tf_tengat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InfoTugasBerita info = new InfoTugasBerita(SessionId.getIdToDetailInfo());
        tarea_isi.setText(info.getIsiBerita());
        tf_judul.setText(info.getJudul());
        tf_perihal.setText(info.getPerihal());
        tf_pengirim.setText(GuruBK.getNama());
        tf_tengat.setText(info.getTglTengat());
    }    
    
    @FXML
    private void back(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/Informasi.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}