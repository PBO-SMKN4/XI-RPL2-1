/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class RegisterGuruController implements Initializable {

    Connection koneksi;
    Statement stmt = null;
    ResultSet res = null;
    
    @FXML
    public ComboBox<String> combobox;    
    ObservableList<String> list = FXCollections.observableArrayList("Laki-Laki","Perempuan","Rahasia");
    
    @FXML
    private TextField nip;
    
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

        koneksi = (Connection) MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        combobox.setItems(list);
    }    

    @FXML
    private void login(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/LoginGuru.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
     }
  
    
    @FXML
     private void regis(javafx.scene.input.MouseEvent event) throws IOException {
         try{
           String noinduk = nip.getText();
           String name = nama.getText();
           String jenkel = combobox.getValue();
           LocalDate tanggal = ttl.getValue();
           String user = username.getText();
           String pass = password.getText();
           String real_pass;

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes("utf8"));

            real_pass = String.format("%040x", new BigInteger(1, digest.digest()));

            
           if(nip.getText().equalsIgnoreCase("")){
               JOptionPane.showMessageDialog(null, "NIP tidak boleh Kosong!");
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
            String sql = "INSERT INTO teachers (nip,nama,jk,tgl_lahir,username,password)"
                    + "VALUES"
                    + "('"+ noinduk + "','"+ name + "','"+ jenkel + "','"+ tanggal+"','"+ user +"','"+ real_pass + "')";

            
            stmt = (Statement) koneksi.createStatement();
            int berhasil = stmt.executeUpdate(sql);

            
            if(berhasil == 1){

                    JOptionPane.showMessageDialog(null, "Daftar Berhasil");

                    System.out.println("Berhasil");
                    Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/LoginGuru.fxml"));
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