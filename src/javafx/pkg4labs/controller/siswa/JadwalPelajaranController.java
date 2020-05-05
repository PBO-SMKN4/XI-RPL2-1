/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;


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
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.interfaceModel.TabelData;
import javafx.pkg4labs.model.JadwalPelajaran;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.xml.ws.Action;

/**
 *
 * @author Muhammad Fahru Rozi
 */

public class JadwalPelajaranController implements Initializable, TabelData {
    /**
     * Initializes the controller class.
     */
    Connection koneksi;
    
    @FXML
    private TableView tbl_jadwal;
    
    @FXML
    private ComboBox cmb_hari;
    
    @FXML
    private TableColumn<String, JadwalPelajaran> col_mapel;
    
    @FXML
    private TableColumn<String, JadwalPelajaran> col_guru;
    
    @FXML
    private TableColumn<String, JadwalPelajaran> col_jam; 
    
    @FXML
    private TableColumn<String, JadwalPelajaran> col_ruangan;
    
    @FXML
    private TableColumn<String, JadwalPelajaran> col_kategori;
    
    @FXML
    private TextField inp_query;
    
    @FXML
    private Button btn_search;
    
    @FXML
    private Button btn_detail;
    
    @FXML
    private TableColumn<Integer,Integer> no;
    
    ArrayList<JadwalPelajaran> list = new ArrayList<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi =  (Connection) MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
        
        inp_query.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    search();
                }
            }
            
        });
         tbl_jadwal.setOnMouseClicked((MouseEvent event) -> {
               setId();
               btn_detail.setDisable(false);
        });
        cmb_hari.setValue("Pilih Hari");
        showData();
    }
    
    @Override
    public void showData(){
        tbl_jadwal.getItems().clear();
        list.clear();
        JadwalPelajaran jadwal;
        int i = 0;
        try{
        String sql;
        Statement stmt;
            
            ObservableList<String> listHari = FXCollections.observableArrayList("Senin", "Selasa", "Rabu", "Kamis", "Jumat");
            cmb_hari.setItems(listHari);
            
            stmt = (Statement) koneksi.createStatement();
            sql = "SELECT * FROM mapel JOIN guru_matpel ON mapel.mapel = guru_matpel.mapel "
                    + "JOIN detail_jadwal ON detail_jadwal.guru_matpel = guru_matpel.nip "
                    + "JOIN jadwal_pelajaran ON jadwal_pelajaran.id_jadwal = detail_jadwal.id_jadwal "
                    + "WHERE nama_kelas = '"+Siswa.getNamaKelas()+"'";
            
            
            if (!inp_query.getText().equals("")) {
                sql+=" AND (nama LIKE '%"+inp_query.getText()+"%' "
                        + "OR detail_jadwal.hari LIKE '%"+inp_query.getText()+"%' "
                        + "OR guru_matpel.nama LIKE '%"+inp_query.getText()+"%' "
                        + "OR mapel.nama_mapel '%"+inp_query.getText()+"%' "
                        + "OR mapel.jurusan LIKE '%"+inp_query.getText()+"')";
            }
            
            
            if (cmb_hari.getValue()!=null) {
               if(!cmb_hari.getValue().toString().equalsIgnoreCase("pilih hari")){
                   sql+=" AND detail_jadwal.hari = '"+cmb_hari.getValue()+"'";
                }
            }
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                jadwal = new JadwalPelajaran(rs.getString("mapel"));
                list.add(jadwal);
            }
            
            Label placeHolder = new Label("Belum Ada Data");
            tbl_jadwal.setPlaceholder(placeHolder);
            
            for (JadwalPelajaran schedule : list) {
                no.setCellValueFactory(column->{
                    return new ReadOnlyObjectWrapper<>(tbl_jadwal.getItems().indexOf(column.getValue())+1);
                });
                col_mapel.setCellValueFactory(new PropertyValueFactory<>("namaMapel"));
                col_guru.setCellValueFactory(new PropertyValueFactory<>("guruMapel"));
                col_jam.setCellValueFactory(new PropertyValueFactory<>("jam"));
                col_ruangan.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
                col_kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
                tbl_jadwal.getItems().add(i, schedule);
                i++;
            }
            i = 0;
           }catch(SQLException e){
           e.printStackTrace();
        }
    }
    
    @Override
    public void refresh() {
        inp_query.setText("");
        cmb_hari.setValue("Pilih Hari");
    }
    
    @Override
    public void search(){
        
    }
    
    public void detailPelajaran(javafx.scene.input.MouseEvent event) throws IOException {
        setId();
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/DetailPelajaran.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void backHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }   

    private void setId() {
        JadwalPelajaran jadwal = (JadwalPelajaran) tbl_jadwal.getSelectionModel().getSelectedItem();
        sessionId.setIdToDetailJadwal(jadwal.getMapel());
    }
}