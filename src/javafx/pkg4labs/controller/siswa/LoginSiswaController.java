/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;


import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class LoginSiswaController implements Initializable {

    Connection koneksi;
    Statement stmt = null;
    ResultSet res = null;
    
    
    @FXML
    private TextField username;
    
    @FXML 
    private PasswordField password;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Label greeting;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    try {
                        loginSuccess(ke);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSiswaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
         greeting.setText(Integer.valueOf(LocalTime.now().toString().split(":")[0])<11?"Good Morning":Integer.valueOf(LocalTime.now().toString().split(":")[0])<18?"Good Afternoon":"Good Evening");
         koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        // TODO 
    }    

    @FXML
    private void register(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RegisterSiswa.fxml"));
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

    public void loginSuccess(Event event) throws IOException {
         try{

            String user = username.getText();
            String pass = password.getText();
            String real_pass;

            MessageDigest digest = MessageDigest.getInstance("SHA-1");

            digest.reset();

            digest.update(pass.getBytes("utf8"));

            real_pass = String.format("%040x", new BigInteger(1, digest.digest()));

            

            String sql = "SELECT students.nis AS nis,teachers.nip FROM students JOIN classes ON students.nama_kelas = classes.nama_kelas JOIN teachers ON classes.guru = teachers.nip WHERE students.username = '"+ user + "' AND students.password = '"+real_pass+"'";

            stmt = (Statement) koneksi.createStatement();

            res = stmt.executeQuery(sql);

            

            if(res.next()){
                    SessionSiswa.setSession(res.getString("nis"));
                    Siswa.setSiswa(res.getString("nis"));
                    GuruBK.setGuruBK(res.getString("nip"));
                    JOptionPane.showMessageDialog(null, "Login Berhasil");

                    Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/HalamanUtama.fxml"));
                    Node node = (Node) event.getSource();        
                    Stage stage = (Stage) node.getScene().getWindow();        
                    stage.setScene(new Scene(root));

            }

            else{
                    if(username.getText().equals("")&&password.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Masukan Username & Password");
                    }
                    else if(username.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Masukan Username!");
                    }else if(password.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Masukan Password !");
                    }else{
                        JOptionPane.showMessageDialog(null, "Login Gagal");  
                    }
            }

        }

        catch(Exception Ex){

             JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");

             Ex.printStackTrace();

        } 
        
    }
    
}
