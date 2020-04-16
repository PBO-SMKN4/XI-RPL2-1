/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class KehadiranSiswaController implements Initializable {

    Connection koneksi;
    Statement stmt = null;
    ResultSet res = null;
   
    @FXML
    public ComboBox<String> combobox1;    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
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
    private void gotoHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

}
