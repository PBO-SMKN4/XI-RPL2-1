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
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class PemberitahuanInformasiController implements Initializable{

    @FXML
    private ComboBox<String> cmb_kelas;
    
    @FXML
    private TextArea tarea_isi_berita;
    
    @FXML
    private DatePicker dp_batas_informasi;
    
    @FXML
    private TextField field_judul;
    
    @FXML
    private ComboBox<String> cmb_perihal;  
    
    ObservableList<String> list = FXCollections.observableArrayList("Tugas","Tata Tertib");
    
    ArrayList<Students> listSiswa = new ArrayList<>();
    
    Connection connection;    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();        
        cmb_perihal.setItems(list);
    }
    
    public void showData(){
        try{
            
            listSiswa.clear();
            int size = 0;
            connection =  MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
            
            Statement stmt;
            String sql;
            
            stmt = connection.createStatement();
            sql = "SELECT * FROM classes";
            ResultSet rs2 = stmt.executeQuery(sql);
            
            stmt = connection.createStatement();
            sql = "SELECT COUNT(classes.nama_kelas) AS kelas FROM classes";
            ResultSet rs3 = stmt.executeQuery(sql);
            
            ObservableList<String> listKelas;
            String[] listNamaKelas;
            
            if (rs3.
                    first()) {
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
            
            stmt = connection.createStatement();
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
    
    private void insertData() {
        
        try{
            String pengirim = GuruBK.getNip();
            String penerima = cmb_kelas.getValue();
            String perihal = cmb_perihal.getValue();
            String judul = field_judul.getText();
            String isi_berita = tarea_isi_berita.getText();
            LocalDate tanggal_upload = LocalDate.now();
            LocalDate tanggal_kadaluarsa = dp_batas_informasi.getValue();
            
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            Statement stmnt = koneksi.createStatement();
            String sql = "INSERT INTO berita (pengirim,penerima,perihal,judul,isi_berita,tgl_upload,tgl_kadaluarsa) VALUES ('"+ pengirim + "','"+ penerima + "','"+ perihal + "','"+ judul + "','"+ isi_berita + "','"+ tanggal_upload + "','"+ tanggal_kadaluarsa + "')";
            int proses = stmnt.executeUpdate(sql);
            
            if(proses == 1){
                System.out.println("Data Berhasil Dikirim");
                refresh();
            } else {
                System.out.println("Data Gagal Dikirim");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/SelectInfo.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void sendData(MouseEvent event) {
        insertData();
    }

    private void refresh() {
        cmb_kelas.setValue("Pilih Kelas");
        cmb_perihal.setValue("Perihal");
        field_judul.setText("");
        tarea_isi_berita.setText("");
        dp_batas_informasi.setValue(null);        
    }
}