/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;


import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.scene.input.MouseEvent;
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
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
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
           String email = "diisi di profile";
           String user = username.getText();
           String pass = password.getText();
           String real_pass;

           MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes("utf8"));

            real_pass = String.format("%040x", new BigInteger(1, digest.digest()));

            
          
            String sql = "INSERT INTO teachers VALUES('"+ noinduk + "', '"+ name + "', '"+ jenkel + "', "
                    + "'"+ tanggal + "', '"+ user + "', '"+ email + "', '"+ real_pass + "')";
            
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

        catch(Exception Ex){
             JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");
             System.out.println(Ex + "Terjadi kesalahan database");

        } 
        
     }
        
   }
    

