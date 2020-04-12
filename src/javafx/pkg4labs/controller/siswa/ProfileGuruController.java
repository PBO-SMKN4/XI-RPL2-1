/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import javafx.pkg4labs.model.GuruBK;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class ProfileGuruController implements Initializable {
<<<<<<< HEAD
    
    @FXML
    private TextField inp_nip;
    
    @FXML
    private DatePicker contoh;
    
    @FXML
    private TextField inp_jenisKelamin;
    
    @FXML
    private TextField inp_email;
    
    @FXML
    private TextField inp_tanggalLahir;
    
    @FXML
    private TextField inp_nama;
    
    @FXML
    private TextField inp_username;
    
    @FXML
    private TextField inp_wa;
=======

>>>>>>> ba14b58e70b208c81233fc6074bba8501eb73c16

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inp_nama.setText(GuruBK.getNama());
        inp_username.setText(GuruBK.getUsername());
        inp_jenisKelamin.setText(GuruBK.getJenisKelamin());
        inp_email.setText(GuruBK.getEmail());
        inp_nip.setText(GuruBK.getNip());
        inp_wa.setText(GuruBK.getNoWa());
        inp_tanggalLahir.setText(GuruBK.getTanggalLahir());
    }


    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));    
    }
    
}
