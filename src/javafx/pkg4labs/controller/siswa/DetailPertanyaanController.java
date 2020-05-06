/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.Pertanyaan;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dean Beniqno A
 */
public class DetailPertanyaanController implements Initializable {
    
    Connection koneksi;
    
    @FXML
    public ComboBox<String> comb_matpel;  
    
    @FXML
    private TextField inp_kategori;
    ObservableList<String> matpel = FXCollections.observableArrayList();
    
    @FXML
    private TextArea txt_isi;
    
    @FXML
    private ImageView foto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        showData();
    }
    
    public void showData(){
        
        try{
            
            Pertanyaan pertanyaan = new Pertanyaan(SessionId.getIdToPertanyaan());
          
            inp_kategori.setText(pertanyaan.getNamaTipeSoal());
            txt_isi.setText(pertanyaan.getIsiPertanyaan());
            foto.setImage(pertanyaan.getFoto());
            inp_kategori.setEditable(false);
            txt_isi.setEditable(false);
            
          }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                e.printStackTrace();
        }
        
    }
    
    public void lihatJawaban(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/Jawaban.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    public void jawabPertanyaan(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/JawabPertanyaan.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    private void backToSelect(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangDiskusi.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void back(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/CariPertanyaan.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();          
        stage.setScene(new Scene(root));
    }
    
}