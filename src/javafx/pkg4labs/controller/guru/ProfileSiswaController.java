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
import javafx.pkg4labs.model.Students;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */


public class ProfileSiswaController implements Initializable {

    @FXML
    private TextField text_nis;
    
    @FXML
    private TextField text_nama;
    
    @FXML
    private TextField text_jk;
    
    @FXML
    private TextField text_kelas;
    
    @FXML
    private TextField text_ttl;
    
    @FXML
    private TextField text_agama;
    
    @FXML
    private TextField text_alamat;
    
    @FXML
    private TextField text_noHp;
    
    @FXML
    private TextField text_email;
    
    @FXML
    private Button butt_wali1;
    
    
    @FXML
    private Button butt_wali2;
    
    @FXML
    private ImageView foto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            showdata();
    }    
    
    public void showdata(){
        Students siswa = new Students(SessionId.getId()); 
        System.out.println(siswa.getNis());
        text_nis.setText(siswa.getNis());
        text_nama.setText(siswa.getNama());
        text_jk.setText(siswa.getJenisKelamin());
        text_kelas.setText(siswa.getNamaKelas());
        text_ttl.setText(siswa.getTanggalLahir());
        text_agama.setText(siswa.getAgama());
        text_alamat.setText(siswa.getAlamat());
        text_noHp.setText(siswa.getNoHP());
        text_email.setText(siswa.getEmail());
        butt_wali1.setText(siswa.getNamaOrtu1());
        butt_wali2.setText(siswa.getNamaOrtu2());
        foto.setImage(siswa.getFoto());
    }
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/DataSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void ToWali1(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/WaliSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void ToWali2(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/WaliSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}
