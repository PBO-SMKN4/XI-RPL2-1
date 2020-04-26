/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

<<<<<<< HEAD
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.Students;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
=======
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
>>>>>>> 86947604812e237e83c5167413a5168ca350c233
import javafx.stage.Stage;

/**
 *
 * @author Diazs
 */
public class KehadiranSiswaController implements Initializable{
<<<<<<< HEAD
    Connection koneksi;
    Statement stmt = null;
    ResultSet res = null;
    ArrayList<Students> listSiswa = new ArrayList<>();
 
    @FXML
    private ComboBox cmb_kelas;
    
    @FXML
    private ComboBox cmb_bulan;
    
    @FXML
    private TableView tbl_data;
    
    @FXML
    private TableColumn<String, Students> clm_nis;
    
    @FXML
    private TableColumn<String, Students> clm_nama;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
    }
    public void showData(){
        try{
            cmb_kelas.getItems().clear();
            cmb_bulan.getItems().clear();
            
            Students siswa;
            int i = 0;
            int size = 0;
            koneksi =  MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
            
            java.sql.Statement stmt;
            String sql;
            
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM students";
            ResultSet rs1 = stmt.executeQuery(sql);
            
            String[] listNamaSiswa;
            
            while(rs1.next()){
                siswa = new Students(rs1.getString("nis"));
                listSiswa.add(siswa);
            }
            Label placeHolder = new Label("Belum Ada Data");
            tbl_data.setPlaceholder(placeHolder);
            for (Students student : listSiswa) {
                clm_nis.setCellValueFactory(new PropertyValueFactory<>("nis"));
                clm_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
                tbl_data.getItems().add(i, student);
                i++;
            }
            i = 0;
            
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM classes";
            ResultSet rs2 = stmt.executeQuery(sql);
            
            stmt = koneksi.createStatement();
            sql = "SELECT COUNT(classes.nama_kelas) AS kelas FROM classes";
            ResultSet rs3 = stmt.executeQuery(sql);
            
            ObservableList<String> listKelas;
            String[] listNamaKelas;
            
            if (rs3.first()) {
                size = rs3.getInt("kelas");
            }
            
            listNamaKelas = new String[size];
            
            size = 0;
            while(rs2.next()) {
                listNamaKelas[size] = rs2.getString("nama_kelas");
                size++;
            }
            size = 0;
            listKelas = FXCollections.observableArrayList(listNamaKelas);
            
            cmb_kelas.setItems(listKelas);
            
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM students JOIN classes ON students.nama_kelas = classes.nama_kelas WHERE classes.guru = '"+GuruBK.getNip()+"'";
            
            if (cmb_kelas.getValue()!=null) {
                if (!cmb_kelas.getValue().toString().equalsIgnoreCase("Pilih Kelas")) {
                    sql+=" AND students.nama_kelas = '"+cmb_kelas.getValue().toString()+"'";
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
=======

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
>>>>>>> 86947604812e237e83c5167413a5168ca350c233
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 86947604812e237e83c5167413a5168ca350c233
