/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class RegisterSiswaController implements Initializable {
    
    Connection koneksi;
    Statement stmt = null;
    ResultSet res = null;
    
    @FXML
    public ComboBox<String> combobox1;    
   
    
    @FXML
    public ComboBox<String> combobox2;
    ObservableList<String> list2 = FXCollections.observableArrayList("Laki-Laki","Perempuan","Rahasia");
    
    @FXML
    private TextField nis;
    
    @FXML
    private TextField nama;
    
    @FXML
    private TextField username;
    
    @FXML
    private DatePicker ttl;
    
    @FXML
    private PasswordField password;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        combobox2.setItems(list2);
       try {
            int jumlahKelas = 0;
            int i = 0;
            String query = "SELECT nama_kelas FROM classes";
            stmt = (Statement) koneksi.createStatement();
            res = stmt.executeQuery(query);
            String query1 = "SELECT count(nama_kelas) AS jumlah FROM classes";
            Statement stmt1 = (Statement) koneksi.createStatement();
            ResultSet res1 = stmt1.executeQuery(query1);
            if(res1.first()){ 
                jumlahKelas = res1.getInt("jumlah");
            }
            String[] listKelas = new String[jumlahKelas];
            while (res.next()) {                
                listKelas[i] = res.getString("nama_kelas");
                i++; System.out.println(res.getString(1));

            }

            ObservableList<String> list1 = FXCollections.observableArrayList(listKelas);
            combobox1.setItems(list1);
            res.close();
         } 
         catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Gagal!");
         }
    }    

    @FXML
    private void login(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/LoginSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }      
    
    @FXML
     private void regis(javafx.scene.input.MouseEvent event) throws IOException {
         try{
           String noinduk = nis.getText();
           String name = nama.getText();
           String jenkel = combobox2.getValue();
           String kelas = combobox1.getValue();
           LocalDate tanggal = ttl.getValue();
           String email = "diisi di profile";
           String user = username.getText();
           String pass = password.getText();
           String real_pass;

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes("utf8"));

            real_pass = String.format("%040x", new BigInteger(1, digest.digest()));
            
           if(nis.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "NISN tidak boleh Kosong!");
           }
           else if(nama.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "NAMA tidak boleh Kosong!");
           }
           else if(username.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "USERNAME tidak boleh Kosong!");
           }
           else if(password.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "PASSWORD tidak boleh Kosong!");
           }
           else{
            String sql = "INSERT INTO students VALUES('"+ noinduk + "', '"+ name + "', '"+ jenkel + "', '"+ kelas + "','"+ tanggal + "', '"+ user + "', '"+ email + "', '"+ real_pass + "')";
            
            stmt = (Statement) koneksi.createStatement();
            int berhasil = stmt.executeUpdate(sql);

            
            if(berhasil == 1){

                    JOptionPane.showMessageDialog(null, "Daftar Berhasil");
                    Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/LoginSiswa.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();        
                    stage.setScene(new Scene(root));
            }
            else{
                    JOptionPane.showMessageDialog(null, "Daftar Gagal");
                    System.out.println("Gagal");
            }

          }
       }
        catch(Exception Ex){
             JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");
             System.out.println(Ex + "Terjadi kesalahan database");

        } 
       
     }
    
}
