/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class EditProfileController implements Initializable {
    
    Connection koneksi;
    String nis;
    boolean edit = false;
    int i;
    
    @FXML
    public ComboBox<String> comb_kelas;    
    ObservableList<String> kelas = FXCollections.observableArrayList();
    
    @FXML
    public ComboBox<String> comb_kelamin;    
    ObservableList<String> kelamin = FXCollections.observableArrayList();
    
    @FXML
    private TextField inp_nis;
    
    @FXML
    private TextField inp_nama;
    
    @FXML
    private TextField inp_email;
    
    @FXML
    private TextField inp_username;
    
    @FXML
    private DatePicker inp_tanggal;
    
    @FXML
    private PasswordField inp_password;
    
    @FXML
    private Button butt_edit;
    
  public void initialize(URL url, ResourceBundle rb) {
                
        try{
            setEditButton(false);
        
            koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            Statement stmt = koneksi.createStatement();
            
            nis = SessionSiswa.getSession();
            int length = 0;
            
            ObservableList<String> data = FXCollections.observableArrayList("Laki-laki","Perempuan");
            comb_kelamin.setItems(data);
            
             String query = "SELECT count(classes.nama_kelas) AS length FROM classes";     
             ResultSet rsItung = stmt.executeQuery(query);
           
             if(rsItung.first()){
                   length = rsItung.getInt("length");
              }   
        
             String[] listKelas = new String[length];
        
             String queryKelas = "SELECT * FROM classes";
             ResultSet rsKelas = stmt.executeQuery(queryKelas);
             
             while(rsKelas.next()){
                listKelas[i] = rsKelas.getString("nama_kelas");
                i++;
             }
        
             ObservableList<String> kelas = FXCollections.observableArrayList(listKelas);
             comb_kelas.setItems(kelas);
        
          }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                 e.printStackTrace();
        }
       showData();
    }
        
    
  
     
    public void edit(){
        if(!edit){
            setEditButton(true);
            butt_edit.setText("Simpan");
            edit = true;
        }
        else{
            if(!validation()){
                simpanEdit();
                butt_edit.setText("Edit");
                edit = false;
                setEditButton(false);
            }
        }
    }
    
     public void setEditButton(Boolean control){
        inp_nis.setEditable(control);
        inp_nama.setEditable(control);
        inp_email.setEditable(control);
        inp_tanggal.setDisable(!control);
        inp_username.setEditable(control);
        comb_kelamin.setDisable(!control);
        comb_kelas.setDisable(!control);
        
    }
    
     void showData() {
        try{
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM students WHERE nis = '"+nis+"'";
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){
                inp_nis.setText(rs.getString("nis"));
                inp_nama.setText(rs.getString("nama"));
                comb_kelas.setValue(rs.getString("nama_kelas"));
                comb_kelamin.setValue(rs.getString("jk"));
                inp_tanggal.setValue(LocalDate.parse(rs.getString("tgl_lahir")));
                inp_username.setText(rs.getString("username"));
                inp_email.setText(rs.getString("email"));
            }
            
        } catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan query");
        }
    }
     
    public boolean validation(){
        boolean showMessage = false;
        String validation[] = {inp_nama.getText().equals("")?"Nama":null,inp_email.getText().equals("")?"Email":null,inp_username.getText().equals("")?"Username":null};
        String message = "Masukan ";
        for(String i : validation){
            if (i!=null) {
                showMessage = true;
                break;
            }
        }
        
        if (showMessage) {
            for (int i = 0; i < validation.length; i++) {
                if (validation[i]!=null) {
                    message+=validation[i];
                }
                if (i!=validation.length-1) {
                    message+=validation[i+1]!=null?", ":"";  
                }
            }
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
        }
        return showMessage;
    }
    
    
    
    public void simpanEdit(){
        
        String nis          = inp_nis.getText();
        String nama         = inp_nama.getText();
        String nama_kelas   = comb_kelas.getValue();
        String jk           = comb_kelamin.getValue();
        LocalDate tgl_lahir = inp_tanggal.getValue();
        String username     = inp_username.getText();
        String email        = inp_email.getText();
        
         try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE students SET nama = '"+nama+"',"
                      + "nama_kelas  = '"+nama_kelas+"',"
                      + "jk          = '"+jk+"',"
                      + "tgl_lahir   = '"+tgl_lahir+"',"
                      + "username    = '"+username+"',"
                      + "email       = '"+email+"' WHERE nis = '"+nis+"'";
            
            int berhasil = stmt.executeUpdate(query);
            if (berhasil == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Success", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("success.png"));
            }
            else{
                JOptionPane.showMessageDialog(null, "Data gagal diubah");
            }
            
            } catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada query");
        }
    }  

@FXML
    private void ruangBK(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void ruangSiswa(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    } 

    @FXML
    private void jadwalPelajaran(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/JadwalPelajaran.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoEditProfile(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/EditProfile.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoInfoDev(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/InfoDeveloper.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoLogout(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/LoginSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }    
    
}