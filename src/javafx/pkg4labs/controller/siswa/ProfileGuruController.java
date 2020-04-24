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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class ProfileGuruController implements Initializable {
    
    @FXML
    private Label lbl_nip;
    
    @FXML
    private Label lbl_jenkel;
    
    @FXML
    private Label lbl_email;
    
    @FXML
    private Label lbl_tanggalLahir;
    
    @FXML
    private Label lbl_nama;
    
    @FXML
    private Label lbl_wa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_nama.setText(GuruBK.getNama());
        lbl_jenkel.setText(GuruBK.getJenisKelamin());
        lbl_email.setText(GuruBK.getEmail());
        lbl_nip.setText(GuruBK.getNip());
        lbl_wa.setText(GuruBK.getNoWa());
        lbl_tanggalLahir.setText(GuruBK.getTanggalLahir());
        
        
        System.out.println(lbl_nip.getParent());
    }
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));    
    }
    
}
