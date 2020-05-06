/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.RuangDiskusi;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dean Beniqno
 */
public class AjukanPertanyaanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    Connection koneksi;
    Statement stmt = null;
    PreparedStatement pst;
    
    @FXML
    public ComboBox<String> comb_matpel;    
    ObservableList<String> matpel = FXCollections.observableArrayList();
    
    @FXML
    private TextArea txt_isi;
    
    @FXML
    private Button butt_pilih;
    
    @FXML
    private Button butt_submit;
    
    @FXML
    private Label lbl_file;
    
    @FXML
    private ImageView foto;
    
    private FileInputStream fis;
    
    private FileChooser fileChooser;
    
    private File file;
    
    private Window window;
    private ArrayList<Integer> id;
    private Image image;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        prepareComboBox();
    }
    
    public void buttonBrowse(MouseEvent e){
        fileChooser = new FileChooser();
        window = ((Node)e.getTarget()).getScene().getWindow();
        file = fileChooser.showOpenDialog(window);
        if (file != null) {
            lbl_file.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString(),100,150,true,true);
            foto.setImage(image);
            
        }
    }
    
    public void prepareComboBox(){
        
        try{
 
            stmt = koneksi.createStatement();
            id = new ArrayList<>();
            String query = "SELECT * FROM tipe_soal";
            ResultSet res = stmt.executeQuery(query);
             while (res.next()) {
                 matpel.add(res.getString("nama_matpel"));
                 id.add(res.getInt("id_tipe"));
             }
            comb_matpel.setItems(matpel);
 
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareElements(){
        txt_isi.setText("");
        lbl_file.setText("");
        file = null;
        comb_matpel.setValue("Pilih Tipe Soal");
        foto.setImage(null);
        comb_matpel.setValue("Pilih Tipe Soal");
    }
    
    public void ajukan(){
        
       try{
           String isi = txt_isi.getText();
           String nis = Siswa.getNis();
           String tanggal = String.valueOf(LocalDate.now());
            
           String sql = "INSERT INTO pertanyaan(nis,tipe_soal"+(file!=null?",foto,file_foto":"")+",pertanyaan,tanggal_upload) "
                   + "VALUES(?,?"+(file!=null?",?,?":"")+",?,?)";
            stmt = (Statement) koneksi.createStatement();
           
            
            if (file != null) {
                fis = new FileInputStream(file);
            }
            pst = koneksi.prepareStatement(sql);
            
            pst.setString(1, nis);
            pst.setInt(2, id.get(comb_matpel.getSelectionModel().getSelectedIndex()));
            
            if (file != null) {
               Random random = new Random();
               pst.setBinaryStream(3,(InputStream)fis,(int)file.length());
               pst.setString(4, String.valueOf(random.nextInt(100000)));
               pst.setString(5, isi);
               pst.setString(6,tanggal);
           }else{
                pst.setString(3,isi);
                pst.setString(4, tanggal);
            } 
            pst.getWarnings();
            
             if (pst.executeUpdate()>0) {
                JOptionPane.showMessageDialog(null, "Pertanyaan Berhasil Diposting", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("success.png"));
                prepareElements();
            }else{
                JOptionPane.showMessageDialog(null, "Pertanyaan Gagal Diposting", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
            }
             
        }

        catch(Exception Ex){
             JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");
             Ex.printStackTrace();
        } 
        foto.setImage(RuangDiskusi.getFoto());
    }

    @FXML
    private void backToSelect(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangDiskusi.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}