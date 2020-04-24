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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.model.GuruBK;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class LoginGuruController implements Initializable {
    Connection koneksi;
    Statement stmt = null;
    ResultSet res = null;
    
    
    @FXML
    private TextField username;
    
    @FXML 
    private PasswordField password;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        // TODO
    }    

    @FXML
    private void register(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/RegisterGuru.fxml"));
        Node node = (Node) event.getSource();        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void role(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/Role.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void login(javafx.scene.input.MouseEvent event) throws IOException {
         try{
             
            String user = username.getText();
            String pass = password.getText();
            String real_pass;

            MessageDigest digest = MessageDigest.getInstance("SHA-1");

            digest.reset();

            digest.update(pass.getBytes("utf8"));

            real_pass = String.format("%040x", new BigInteger(1, digest.digest()));

            

            String sql = "SELECT * FROM teachers WHERE username = '"+ user + "' AND password = '"+real_pass+"'";

            stmt = (Statement) koneksi.createStatement();

            res = stmt.executeQuery(sql);

            

            if(res.next()){

                if(user.equals(res.getString("username")) && real_pass.equals(res.getString("password"))){

                    JOptionPane.showMessageDialog(null, "Login Berhasil");

                    System.out.println("Berhasil");
                    GuruBK.setGuruBK(res.getString("nip"));
                    Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
                    Node node = (Node) event.getSource();

                    Stage stage = (Stage) node.getScene().getWindow();        
                    stage.setScene(new Scene(root));

                } 

            }

            else{

                    JOptionPane.showMessageDialog(null, "Login Gagal");
                    System.out.println("Gagal");

            }

        }

        catch(Exception Ex){

             JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");

             System.out.println(Ex);

        } 
        
    }
    
}
