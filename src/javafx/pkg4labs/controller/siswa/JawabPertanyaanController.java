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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Dean Beniqno A
 */
public class JawabPertanyaanController implements Initializable {
    
    Connection koneksi;
    Statement stmt = null;
    PreparedStatement pst;
    
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
    
    @FXML
    private FileInputStream fis;
    
    @FXML
    private FileChooser fileChooser;
    
    @FXML
    private File file;
    
    @FXML
    private Window window;
    
    @FXML
    private Image image;
    
    @FXML
    private Rating Rating;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
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
    
    public void jawab(){
        
       try{
           String isi = txt_isi.getText();
           String nis = Siswa.getNis();
           String tanggal = String.valueOf(LocalDate.now());
           String id_pertanyaan = String.valueOf(SessionId.getIdToPertanyaan());
           
            
           String sql = "INSERT INTO jawaban(nis"+(file!=null?",foto,file_foto":"")+",jawaban,tanggal_upload,id_pertanyaan) "
                   + "VALUES(?"+(file!=null?",?,?":"")+",?,?,?)";
           stmt = (Statement) koneksi.createStatement();
           
            
            if (file != null) {
                fis = new FileInputStream(file);
            }
            pst = koneksi.prepareStatement(sql);
            
            pst.setString(1, nis);
            
            if (file != null) {
               Random random = new Random();
               pst.setBinaryStream(2,(InputStream)fis,(int)file.length() );
               pst.setString(3, String.valueOf(random.nextInt(100000)));
               pst.setString(4, isi);
               pst.setString(5, tanggal);
               pst.setString(6, id_pertanyaan);
               
           }else{
                pst.setString(2, isi);
                pst.setString(3, tanggal);
                pst.setString(4, id_pertanyaan);
             
            } 
            pst.getWarnings();
            
             if (pst.executeUpdate()>0) {
                JOptionPane.showMessageDialog(null, "Jawaban Berhasil Diposting", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("success.png"));
            }else{
                JOptionPane.showMessageDialog(null, "Jawaban Gagal Diposting", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
            }
             
        }

        catch(Exception Ex){
             JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");
             Ex.printStackTrace();
        } 
        foto.setImage(image);
    }
    @FXML
    private void backToSelect(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/DetailPertanyaan.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}