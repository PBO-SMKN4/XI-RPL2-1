/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

import javafx.pkg4labs.model.GuruBK;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class ProfileBKController implements Initializable{
    boolean edit = false;
    
    @FXML
    private TextField inp_nip;
    
    @FXML
    private TextField inp_nama;
    
    @FXML
    public ComboBox<String> com_jk; 
    
    @FXML
    private DatePicker dat_lahir;
    
    @FXML
    private TextField inp_username;
    
    @FXML
    private PasswordField inp_password;
    
    @FXML
    private TextField inp_email;
    
    @FXML
    private TextField inp_wa;
    
    @FXML
    private Button but_edit;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> Jenis_Kelamin = FXCollections.observableArrayList("Laki-laki","Perempuan");
        com_jk.setItems(Jenis_Kelamin);
        inp_nip.setText(GuruBK.getNip());
        inp_nama.setText(GuruBK.getNama());
        com_jk.setValue(GuruBK.getJenisKelamin());
        dat_lahir.setValue(LocalDate.parse(GuruBK.getTanggalLahir()));
        inp_username.setText(GuruBK.getUsername());
        inp_email.setText(GuruBK.getEmail());
        inp_wa.setText(GuruBK.getNoWa());
    }
    

    public void edit(){       
    if(!edit){
            but_edit.setText("Save");
            seteditButton(true);
            edit = true;
        }
        else{
            saveEdit();
            but_edit.setText("Edit");
            edit = false;
            seteditButton(false);
        }
    }
    
    public void seteditButton(Boolean control){
        inp_nip.setEditable(control);
        inp_nama.setEditable(control);
        com_jk.setEditable(control);
        dat_lahir.setEditable(control);
        inp_username.setEditable(control);
        inp_password.setEditable(control);
        inp_email.setEditable(control);
        inp_wa.setEditable(control);
    }
    
    public void saveEdit(){
        String nip = inp_nip.getText();
        String nama = inp_nama.getText();
        String jk = com_jk.getValue();
        LocalDate tgl_lahir = dat_lahir.getValue();
        String username = inp_username.getText();
        String email = inp_email.getText();
        String wa = inp_wa.getText();
        
        try {
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE teachers SET nama = '"+nama+"',"
                      + "jk          = '"+jk+"',"
                      + "tgl_lahir   = '"+tgl_lahir+"',"
                      + "username    = '"+username+"',"
                      + "email       = '"+email+"' ,"
                      + "no_whatsapp = '"+wa+"' WHERE nip = '"+nip+"'";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                 System.out.println("Berhasil");
            }
            else{
                JOptionPane.showMessageDialog(null, "Data gagal diubah");
                System.out.println("Gagal");
            }
            
            } catch(Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada query");
            }
        }
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void gotoHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/guru/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoEditProfile(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/EditProfile.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoInfoDev(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/guru/InfoDeveloper.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoLogout(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/LoginSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
}