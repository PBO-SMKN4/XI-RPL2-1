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
import javafx.pkg4labs.interfaceModel.TabelData;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.Nilai;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class NilaiSiswaController implements Initializable, TabelData{
    @FXML
    private TableColumn<String, Nilai> clm_nis;
    
    @FXML
    private TableColumn<String, Nilai> clm_nama;
    
    @FXML
    private TableColumn<String, Nilai> clm_ppl;
    
    @FXML
    private TableColumn<String, Nilai> clm_inggris;
    
    @FXML
    private TableColumn<String, Nilai> clm_mtk;
    
    @FXML
    private TableColumn<String, Nilai> clm_ppkn;
    
    @FXML
    private TableColumn<String, Nilai> clm_bd;
    
    @FXML
    private TableColumn<String, Nilai> clm_pai;
    
    @FXML
    private TableColumn<String, Nilai> clm_pwpb;
    
    @FXML
    private TableColumn<String, Nilai> clm_pjok;
    
    @FXML
    private TableColumn<String, Nilai> clm_pkk;
    
    @FXML
    private TableColumn<String, Nilai> clm_sunda;
    
    @FXML
    private TableColumn<String, Nilai> clm_pbo;
    
    @FXML
    private TableColumn<String, Nilai> clm_indo;
    
    @FXML
    private TableColumn<String, Nilai> clm_produktif1,clm_produktif2,clm_produktif3,clm_produktif4,clm_produktif5;
    
    @FXML
    private TableColumn<String, Nilai> clm_lainnya1,clm_lainnya2,clm_lainnya3,clm_lainnya4;
    
    @FXML
    private ComboBox cmb_kelas;
    
    @FXML
    private ComboBox cmb_semester;
    
    @FXML
    private TableView tbl_data;
    
    @FXML
    private TextField inp_query;
    
    @FXML
    private Button btn_cari;
    
    @FXML
    private Button btn_refresh;
    
    ArrayList<Nilai> listNilai = new ArrayList<>();
    Connection koneksi;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmb_kelas.setValue("Pilih Kelas");
        cmb_semester.setValue("Genap");
        koneksi = MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
        showData();
    }
    
    public void showData(){
         Nilai nilaiMatpel;
         try {
            tbl_data.getItems().clear();
            listNilai.clear();
            
            int i = 0;
            Statement stmt;
            String sql;
            stmt = koneksi.createStatement();
            
            ResultSet rs;
            
            ObservableList listKelas = FXCollections.observableArrayList();
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM classes WHERE guru = '"+GuruBK.getNip()+"'";
            rs = stmt.executeQuery(sql);
             while (rs.next()) {                 
                 listKelas.add(rs.getString("nama_kelas"));
             }
            cmb_kelas.setItems(listKelas);
            ObservableList sem = FXCollections.observableArrayList();
            sem.addAll("Genap","Ganjil");
            cmb_semester.setItems(sem);
            
             if (cmb_kelas.getValue()!="Pilih Kelas"&&cmb_kelas.getValue()!=null) {
                 String grade = String.valueOf(cmb_kelas.getValue()).split("\\s")[0];
                 String jurusan = String.valueOf(cmb_kelas.getValue()).split("\\s")[1];
                 sql = "SELECT * FROM mapel WHERE jurusan = '"+jurusan+grade+"' ";
                 if (grade.equalsIgnoreCase("XI")||grade.equalsIgnoreCase("XII")) {
                     stmt = koneksi.createStatement();
                     rs = stmt.executeQuery(sql);
                     while(rs.next()) {
                        if (rs.getString("kategori").equalsIgnoreCase("produktif1")) {
                            clm_produktif1.setText(rs.getString("mapel"));
                            clm_produktif1.setVisible(true);
                        }else if(rs.getString("kategori").equalsIgnoreCase("produktif2")){
                            clm_produktif2.setText(rs.getString("mapel"));
                            clm_produktif2.setVisible(true);
                        }else if(rs.getString("kategori").equalsIgnoreCase("produktif3")){
                            clm_produktif3.setText(rs.getString("mapel"));
                            clm_produktif3.setVisible(true);
                        }else if(rs.getString("kategori").equalsIgnoreCase("produktif4")){
                            clm_produktif4.setText(rs.getString("mapel"));
                            clm_produktif4.setVisible(true);
                        }else if(rs.getString("kategori").equalsIgnoreCase("produktif5")){
                            clm_produktif5.setText(rs.getString("mapel"));
                            clm_produktif5.setVisible(true);
                       }
                     }
                     
                 }else{
                     stmt = koneksi.createStatement();
                     rs = stmt.executeQuery(sql);
                     while(rs.next()) {
                        if (rs.getString("kategori").equalsIgnoreCase("lainnya1")) {
                            clm_lainnya1.setText(rs.getString("mapel"));
                            clm_lainnya1.setVisible(true);
                        }else if(rs.getString("kategori").equalsIgnoreCase("lainnya2")){
                            clm_lainnya2.setText(rs.getString("mapel"));
                            clm_lainnya2.setVisible(true);
                        }else if(rs.getString("kategori").equalsIgnoreCase("lainnya3")){
                            clm_lainnya3.setText(rs.getString("mapel"));
                            clm_lainnya3.setVisible(true);
                        }else if(rs.getString("kategori").equalsIgnoreCase("lainnya4")){
                            clm_lainnya4.setText(rs.getString("mapel"));
                            clm_lainnya4.setVisible(true);
                        }
                     }
                 }
                 
                Image refresh = new Image("file:src/javafx/assets/image/refresh.png");
                btn_refresh.setGraphic(new ImageView(refresh));
                Label placeHolder = new Label("Belum Ada Data");
                tbl_data.setPlaceholder(placeHolder);
                
                sql = "SELECT * FROM students JOIN classes ON students.nama_kelas = classes.nama_kelas WHERE classes.guru = '"+GuruBK.getNip()+"' AND classes.nama_kelas = '"+cmb_kelas.getValue()+"'";
                System.out.println("Sql : "+sql);
                
                if (!inp_query.getText().equals("")) {
                sql+=" AND (students.nama LIKE '%"+inp_query.getText()+"%' OR students.nis LIKE '%"+inp_query.getText()+"%')";
                } 
                
                tbl_data.getItems().clear();
                listNilai.clear();
                ResultSet rsSiswa = stmt.executeQuery(sql);
                
                while (rsSiswa.next()) {
                     nilaiMatpel = new Nilai(rsSiswa.getString("nis"), String.valueOf(cmb_semester.getValue()).toLowerCase(),String.valueOf(Year.now().getValue())+"-"+String.valueOf(Year.now().getValue()+1));
                     listNilai.add(nilaiMatpel);
                }
                
                for (Nilai nilai : listNilai) {
                    clm_nis.setCellValueFactory(new PropertyValueFactory<>("nis"));
                    clm_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
                    clm_inggris.setCellValueFactory(new PropertyValueFactory<>("nilaiInggris"));
                    clm_indo.setCellValueFactory(new PropertyValueFactory<>("nilaiIndo"));
                    clm_mtk.setCellValueFactory(new PropertyValueFactory<>("nilaiMtk"));
                    clm_pai.setCellValueFactory(new PropertyValueFactory<>("nilaiPAI"));
                    clm_sunda.setCellValueFactory(new PropertyValueFactory<>("nilaiSunda"));
                    clm_pjok.setCellValueFactory(new PropertyValueFactory<>("nilaiOR"));
                    clm_ppkn.setCellValueFactory(new PropertyValueFactory<>("nilaiPPKN"));
                    clm_produktif1.setCellValueFactory(new PropertyValueFactory<>("nilaiProduktif1"));
                    clm_produktif2.setCellValueFactory(new PropertyValueFactory<>("nilaiProduktif2"));
                    clm_produktif3.setCellValueFactory(new PropertyValueFactory<>("nilaiProduktif3"));
                    clm_produktif4.setCellValueFactory(new PropertyValueFactory<>("nilaiProduktif4"));
                    clm_produktif5.setCellValueFactory(new PropertyValueFactory<>("nilaiProduktif5"));
                    clm_lainnya1.setCellValueFactory(new PropertyValueFactory<>("nilaiLainnya1"));
                    clm_lainnya2.setCellValueFactory(new PropertyValueFactory<>("nilaiLainnya2"));
                    clm_lainnya2.setCellValueFactory(new PropertyValueFactory<>("nilaiLainnya3"));
                    clm_lainnya3.setCellValueFactory(new PropertyValueFactory<>("nilaiLainnya4"));
                    tbl_data.getItems().add(i,nilai);
                    i++;
                }
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
        
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    public void search() {
        if(inp_query.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan Keyword Pencarian");
        }else{
            showData();
        }
    }

    public void refresh() {
       cmb_kelas.setValue("Pilih Kelas");
       cmb_semester.setValue("Genap");
       inp_query.setText("");
        
       showData();
    }
    
}

