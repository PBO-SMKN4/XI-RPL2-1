/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD:src/javafx/pkg4labs/controller/LoginController.java
package javafx.pkg4labs.controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import javafx.pkg4labs.main.JavaFX4Labs;
import java.net.URL;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
=======
package javafx.pkg4labs.controller.siswa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
>>>>>>> a710634933bd708d5d645f722bf959c97f62ab07:src/javafx/pkg4labs/controller/siswa/JadwalPelajaranController.java
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD:src/javafx/pkg4labs/controller/LoginController.java
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.xml.ws.Action;

/**
 *
 * @author Muhammad Fahru Rozi
 */
public class LoginController implements Initializable {
   
    @FXML
    private TextField username;
    
    @FXML
    private Button submit;
    
    @FXML
    private PasswordField password;
  
    Connection koneksi;
    String action;
    Statement stmt = null;
    ResultSet res = null;
    
    
    @FXML
    void login (MouseEvent event){
        loginSiswa();
        
    }
    
    public void loginSiswa(){
        try{
            String user = username.getText();
            String pass = password.getText();
            String real_pass;
            
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes("utf8"));
            real_pass = String.format("%040x", new BigInteger(1, digest.digest()));
            
            String sql = "SELECT * FROM students WHERE username = '"+ user + "' AND password = '"+real_pass+"'";
            stmt = (Statement) koneksi.createStatement();
            res = stmt.executeQuery(sql);
            
            if(res.next()){
                if(user.equals(res.getString("username")) && real_pass.equals(res.getString("password"))){
                     JOptionPane.showMessageDialog(null, "Login Berhasil");
                     System.out.println("Berhasil");
                }
                
            }
            else{
                    JOptionPane.showMessageDialog(null, "Login Gagal");
                    System.out.println("Gagal");
                }
          stmt.close();
          res.close();
        }
        catch(Exception Ex){
             JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");
             System.out.println(Ex);
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        
        
    }    

    @FXML
    private void register(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/Register.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void login(javafx.scene.input.MouseEvent event) {
        loginSiswa();
    }
=======
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class JadwalPelajaranController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
>>>>>>> a710634933bd708d5d645f722bf959c97f62ab07:src/javafx/pkg4labs/controller/siswa/JadwalPelajaranController.java
    
}
