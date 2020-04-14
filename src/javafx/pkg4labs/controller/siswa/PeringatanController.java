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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class PeringatanController implements Initializable {
    
    Connection koneksi;
    String nis;
    
    @FXML
    private Label label_keterangan;
    
    @FXML
    private Label label_peringatan;
    
    @FXML
    private Label label_tanggal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      try{
        
            koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            Statement stmt = koneksi.createStatement();
            
            nis = SessionSiswa.getSession();
           
        
          }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                 e.printStackTrace();
        }
       showData();
    } 
    
    
     void showData() {
        try{
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM peringatan WHERE nis = '"+nis+"'";
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){
                label_keterangan.setText(rs.getString("isi_peringatan"));
                
            }
            
        } catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan query");
        }
    }
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}
