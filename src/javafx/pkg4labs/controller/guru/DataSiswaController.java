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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.interfaceModel.TabelData;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.Students;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class DataSiswaController implements Initializable, TabelData {

    @FXML
    private TableView tbl_data;
    
    @FXML
    private ComboBox cmb_kelas;
    
    @FXML
    private TableColumn<String, Students> clm_nis;
    
    @FXML
    private TableColumn<String, Students> clm_nama;
    
    @FXML
    private TableColumn<String, Students> clm_ttl;
    
    @FXML
    private TableColumn<String, Students> clm_agama;
    
    @FXML
    private TableColumn<String, Students> clm_alamat;
    
    @FXML
    private TableColumn<String, Students> clm_hp;
    
    @FXML
    private TableColumn<String, Students> clm_wali;
    
    @FXML
    private TableColumn<String, Students> clm_jk;
    
    @FXML
    private TableColumn<String, Students> clm_hpWali;
    
    @FXML
    private TableColumn<String, Students> clm_catatan;
    
    @FXML
    private TextField inp_query;
    
    @FXML
    private Button btn_refresh;
    
    @FXML
    private Button btn_detail;
    
    @FXML
    private AnchorPane root;
    
    ArrayList<Students> listSiswa = new ArrayList<>();
    Connection koneksi;
    String id;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
        root.setOnMouseClicked((event) -> {
            btn_detail.setDisable(true);
        });
        
        inp_query.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                search();
            }
        });
        
        tbl_data.setOnMouseClicked((MouseEvent event) -> {
                setId();
                btn_detail.setDisable(false);
        });
    }
    
    @Override
    public void showData(){
        try{
            //Menghapus data untuk memastikan data kosong
            tbl_data.getItems().clear();
            listSiswa.clear();
            
            Students siswa;
            int i = 0;
            int size = 0;
            koneksi =  MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
            
            Statement stmt;
            String sql;
            
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
            
            if (!inp_query.getText().equals("")) {
                sql+=" AND (students.nama LIKE '%"+inp_query.getText()+"%' OR students.alamat LIKE '%"+inp_query.getText()+"%' OR students.email LIKE '%"+inp_query.getText()+"%' OR students.no_tlp LIKE '%"+inp_query.getText()+"%')";
            }
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                siswa = new Students(rs.getString("nis"));
                listSiswa.add(siswa);
            }
            
            Label placeHolder = new Label("Belum Ada Data");
            tbl_data.setPlaceholder(placeHolder);
            for (Students student : listSiswa) {
                clm_nis.setCellValueFactory(new PropertyValueFactory<>("nis"));
                clm_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
                clm_agama.setCellValueFactory(new PropertyValueFactory<>("agama"));
                clm_alamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
                clm_hp.setCellValueFactory(new PropertyValueFactory<>("noHP"));
                clm_catatan.setCellValueFactory(new PropertyValueFactory<>("catatan"));
                clm_ttl.setCellValueFactory(new PropertyValueFactory<>("tanggalLahir"));
                clm_jk.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));
                if (student.getNamaOrtu1()!=null) {
                    clm_wali.setCellValueFactory(new PropertyValueFactory<>("namaOrtu1"));
                    clm_hpWali.setCellValueFactory(new PropertyValueFactory<>("noWali1"));
                }else{
                    clm_wali.setCellValueFactory(new PropertyValueFactory<>("namaOrtu2"));
                    clm_hpWali.setCellValueFactory(new PropertyValueFactory<>("noWali2"));
                }
                tbl_data.getItems().add(i, student);
                i++;
            }
            i = 0;
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void refresh(){
        cmb_kelas.setValue("Pilih Kelas");
        inp_query.setText("");
        
        showData();
    }
    
    @Override
    public void search(){
        if(inp_query.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan Keyword Pencarian");
        }else{
            showData();
        }
    }
    

    public void setId(){
        Students student = (Students) tbl_data.getSelectionModel().getSelectedItem();
        id = student.getNis();
        SessionId.setId(id);
    }
    
    public void gotoDetail(javafx.scene.input.MouseEvent event) throws IOException{
        if (id==null) {
           JOptionPane.showMessageDialog(null, "Pilih siswa yang akan dilihat");
        }else{
            SessionId.setId(id);
            Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/ProfileSiswa.fxml"));

            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();        
            stage.setScene(new Scene(root));
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

