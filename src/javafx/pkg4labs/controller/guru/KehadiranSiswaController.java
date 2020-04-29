/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.Kehadiran;
import javafx.pkg4labs.model.Students;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Fajar
 */
public class KehadiranSiswaController implements Initializable{
    Connection koneksi;
    Statement stmt = null;
    ResultSet res = null;
    ArrayList<Kehadiran> listSiswa = new ArrayList<>();
 
    @FXML
    private ComboBox cmb_kelas;
    
    @FXML
    private ComboBox cmb_bulan;
    
    @FXML
    private ComboBox cmb_tahun;
    
    @FXML
    private TableView tbl_data;
    
    @FXML
    private TableColumn<String, Students> clm_nis;
    
    @FXML
    private TableColumn<String, Students> clm_nama;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_minggu1;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_minggu2;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_minggu3;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_minggu4;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_kehadiran;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_alfa;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_izin;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_sakit;
    
    @FXML
    private TableColumn<String, Kehadiran> clm_keterangan;
    
    @FXML
    private TextField inp_query;
    
    @FXML
    private AnchorPane root;
    
    public String currentYear = String.valueOf(Year.now().getValue());
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inp_query.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                search();
            }
        });
        
        root.setOnMouseClicked((event) -> {
            root.requestFocus();
        });
        cmb_bulan.setValue("Januari");
        cmb_tahun.setValue(String.valueOf(Year.now().getValue()));
        cmb_kelas.setValue("Pilih Kelas");
        showData();
    }
    
    public void showData(){
        listSiswa.clear();
        try{
            String[] Bulan = {"Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};
            ObservableList<String> listBulan = FXCollections.observableArrayList(Bulan);
            listBulan.addAll(Bulan);
            cmb_bulan.setItems(listBulan);
            
            Kehadiran kehadiran;
            int i = 0;
            int size = 0;
            koneksi = (Connection) MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
            
            Statement stmt;
            String sql;
            
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM students JOIN classes ON students.nama_kelas = classes.nama_kelas WHERE classes.guru = '"+GuruBK.getNip()+"'";
            if (cmb_kelas.getValue()!="Pilih Kelas") {
                if (!cmb_kelas.getValue().toString().equalsIgnoreCase("Pilih Kelas")) {
                    sql+=" AND students.nama_kelas = '"+cmb_kelas.getValue().toString()+"'";
                }
            }
            
            if (!inp_query.getText().equals("")) {
                sql+=" AND students.nama LIKE '%"+inp_query.getText()+"%'";
            }
            
            ResultSet rs = stmt.executeQuery(sql);
            
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM classes";
            ResultSet rs2 = stmt.executeQuery(sql);
            
            int tempTahun = 2000;
            ObservableList<String> listTahun = FXCollections.observableArrayList();
            while(tempTahun!=2500){
                listTahun.add(String.valueOf(tempTahun));
                tempTahun++;
            }
            cmb_tahun.setItems(listTahun);
            
            ObservableList<String> listKelas;
            
            listKelas = FXCollections.observableArrayList();
            
            while(rs2.next()) {
                if (rs2.getString("nama_kelas").split("\\s")[0].equalsIgnoreCase("XI")) {
                    listKelas.add(rs2.getString("nama_kelas"));
                }
            }
            cmb_kelas.setItems(listKelas);
            
            listSiswa.clear();
            while(rs.next()){
                kehadiran = new Kehadiran(rs.getString("nis"),String.valueOf(cmb_bulan.getValue()),String.valueOf(cmb_tahun.getValue()));
                kehadiran.setNis(rs.getString("nis"));
                kehadiran.setNama(rs.getString("nama"));
                listSiswa.add(kehadiran);
            }
            
            Label placeHolder = new Label("Belum Ada Data");
            tbl_data.getItems().clear();
            tbl_data.setPlaceholder(placeHolder);
            for (Kehadiran hadir : listSiswa) {
                clm_nis.setCellValueFactory(new PropertyValueFactory<>("nis"));
                clm_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
                clm_minggu1.setCellValueFactory(new PropertyValueFactory<>("jumlahKehadiranMinggu1"));
                clm_minggu2.setCellValueFactory(new PropertyValueFactory<>("jumlahKehadiranMinggu2"));
                clm_minggu3.setCellValueFactory(new PropertyValueFactory<>("jumlahKehadiranMinggu3"));
                clm_minggu4.setCellValueFactory(new PropertyValueFactory<>("jumlahKehadiranMinggu4"));
                clm_kehadiran.setCellValueFactory(new PropertyValueFactory<>("jumlahKehadiranPerbulan"));
                clm_alfa.setCellValueFactory(new PropertyValueFactory<>("jumlahAlfa"));
                clm_izin.setCellValueFactory(new PropertyValueFactory<>("jumlahIzin"));
                clm_sakit.setCellValueFactory(new PropertyValueFactory<>("jumlahSakit"));
                clm_keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
                tbl_data.getItems().add(i, hadir);
                i++;
            }
            i = 0;
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void refresh(){
        cmb_kelas.setValue("Pilih Kelas");
        cmb_bulan.setValue("Januari");
        cmb_tahun.setValue(currentYear);
        inp_query.setText("");
        showData();
    }
    
    public void search(){
        if (inp_query.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Isikan keyword terlebih dahulu");
        }else{
            showData();
        }
    }
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
}