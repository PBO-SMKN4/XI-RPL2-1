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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.RekapKasus;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class RekapKasusController implements Initializable, TabelData{
    Connection koneksi;
    
    @FXML
    private TableView tbl_kasus;
    
    @FXML
    private ComboBox bulan;
    
    @FXML
    private ComboBox tahun;
    
    @FXML
    private TableColumn<String, RekapKasus> col_tgl;
    
    @FXML
    private TableColumn<String, RekapKasus> col_nama;
     
    @FXML
    private TableColumn<String, RekapKasus> col_kelas;
    
    @FXML
    private TableColumn<String, RekapKasus> col_permasalahan;
    
    @FXML
    private TableColumn<String, RekapKasus> col_latar;
    
    @FXML
    private TableColumn<String, RekapKasus> col_penanganan;
    
    @FXML
    private TableColumn<String, RekapKasus> col_tindak;
    
    @FXML
    private TextField inp_query;
    
    @FXML
    private TableColumn<Integer,Integer> no;
    
    ArrayList<RekapKasus> list = new ArrayList<>();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        koneksi =  (Connection) MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
        
        inp_query.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    search();
                }
            }
        });
        
        bulan.setValue("Pilih Bulan");
        tahun.setValue("Pilih Tahun");
        showData();
    }
    
    public void showData(){
        RekapKasus kasus;
        int i = 0;
        try{
        String sql;
        Statement stmt;
            tbl_kasus.getItems().clear();
            list.clear();
            
            String[] Bulan = {"Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember"};
            ObservableList<String> listBulan = FXCollections.observableArrayList(Bulan);
            listBulan.addAll(Bulan);
            stmt = (Statement) koneksi.createStatement();
            sql = "SELECT * FROM students JOIN peringatan ON students.nis = peringatan.nis WHERE nip = '"+GuruBK.getNip()+"'";
            if (!inp_query.getText().equals("")) {
                sql+=" AND (nama LIKE '%"+inp_query.getText()+"%' "
                        + "OR nama_kelas LIKE '%"+inp_query.getText()+"%' "
                        + "OR students.nis LIKE '%"+inp_query.getText()+"%' "
                        + "OR judul LIKE '%"+inp_query.getText()+"%' "
                        + "OR isi_peringatan LIKE '%"+inp_query.getText()+"')";
            }
            
            System.out.println("Sql : "+sql);
            bulan.setItems(listBulan);
            
            int tempTahun = 2000;
            ObservableList<String> listTahun = FXCollections.observableArrayList();
            while(tempTahun!=2500){
                listTahun.add(String.valueOf(tempTahun));
                tempTahun++;
            }
            tahun.setItems(listTahun);
            
            if (bulan.getValue()!=null) {
               if(!bulan.getValue().toString().equalsIgnoreCase("pilih bulan")){
                   sql+=" AND MONTH(tanggal_peringatan) = '"+(bulan.getSelectionModel().getSelectedIndex()+1)+"'";
               } 
            }if (tahun.getValue()!=null) {
                if(!tahun.getValue().toString().equalsIgnoreCase("pilih tahun")){
                    sql+=" AND YEAR(tanggal_peringatan) = '"+(tahun.getValue().toString())+"'";
                }
            }
            
            ResultSet rs = stmt.executeQuery(sql);
            tbl_kasus.getItems().clear();
            list.clear();
            
             
            
            while(rs.next()){
                kasus = new RekapKasus(rs.getString("id_peringatan"));
                System.out.println("rs : "+rs.getString("id_peringatan"));
                list.add(kasus);
            }
            
            Label placeHolder = new Label("Belum Ada Data");
            tbl_kasus.setPlaceholder(placeHolder);
            
            for (RekapKasus rk : list) {
                no.setCellValueFactory(column->{
                    return new ReadOnlyObjectWrapper<>(tbl_kasus.getItems().indexOf(column.getValue())+1);
                });
                col_tgl.setCellValueFactory(new PropertyValueFactory<>("tanggalPeringatan"));
                col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
                col_kelas.setCellValueFactory(new PropertyValueFactory<>("namaKelas"));
                col_permasalahan.setCellValueFactory(new PropertyValueFactory<>("judul"));
                col_latar.setCellValueFactory(new PropertyValueFactory<>("latarBelakang"));
                col_penanganan.setCellValueFactory(new PropertyValueFactory<>("penanganan"));
                col_tindak.setCellValueFactory(new PropertyValueFactory<>("tindakLanjut"));
                tbl_kasus.getItems().add(i, rk);
                System.out.println("dip");
                i++;
            }
            i = 0;
           }catch(Exception e){
           e.printStackTrace();
        }
    }
   
    
    public void refresh() {
        bulan.setValue("Pilih Bulan");
        tahun.setValue("Pilih Tahun");
        inp_query.setText("");
        
        showData();
    }
    
    public void search(){
        if(inp_query.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan Keyword Pencarian");
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