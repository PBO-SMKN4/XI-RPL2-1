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
import javafx.pkg4labs.model.OrtuSiswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fajar
 */
public class WaliSiswaController implements Initializable {
    
    @FXML
    private TextField text_ttl;
    
    @FXML
    private TextField text_jk;
    
    @FXML
    private TextField text_hubungan;
    
    @FXML
    private TextField text_noHp;
    
    @FXML
    private TextField text_alamat;
    
    @FXML
    private TextField text_agama;
    
    @FXML
    private TextField text_goldar;
    
    @FXML
    private TextField text_nama;
    
    @FXML
    private TextField text_nik;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
    }    

    public void showData(){
        try{
            
            OrtuSiswa ortuSiswa = new OrtuSiswa(SessionId.getIdToWali());
            text_nama.setText(ortuSiswa.getNamaWali());
            text_ttl.setText(ortuSiswa.getTglLahir());
            text_jk.setText(ortuSiswa.getJenKel());
            text_hubungan.setText(ortuSiswa.getHubungan());
            text_agama.setText(ortuSiswa.getAgama());
            text_goldar.setText(ortuSiswa.getGolDar());
            text_nik.setText(ortuSiswa.getIdWali());
            text_noHp.setText(ortuSiswa.getNoHP());
            text_alamat.setText(ortuSiswa.getAlamat());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/ProfileSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}