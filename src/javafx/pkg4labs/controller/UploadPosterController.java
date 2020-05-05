/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.guru.SessionId;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.model.GuruBK;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
 * @author Muhammad Fahru Rozi
 */
public class UploadPosterController implements Initializable {
    
    @FXML
    private TextField inp_judul,inp_pengirim,inp_contact;
    
    @FXML
    private DatePicker inp_tenggat;
    
    @FXML
    private ImageView imgv_poster;
    
    @FXML
    private Label lbl_file;
    
    @FXML
    private Button butt_browse;
    
    @FXML 
    private ComboBox cmb_kategori;
    
    private Image image;
    
    private InputStream is;
    
    private FileInputStream fis;
    
    private FileChooser fileChooser;
    
    private Window window;
    
    private File file;
    
    //Prepare For Query Database
    private Connection koneksi;
    private PreparedStatement pst;
    private ResultSet res;
    private String sql;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        prepareComboBox();
    }
    
    public void browse(MouseEvent e){
        fileChooser = new FileChooser();
        window = ((Node)e.getTarget()).getScene().getWindow();
        file = fileChooser.showOpenDialog(window);
        if (file != null) {
            lbl_file.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString(),249,326,true,true);
            imgv_poster.setImage(image);
        }else{
            imgv_poster.setImage(null);
        }
    }
    
    public void checkInput(){
        if (!validation()) {
            posting();
        }
    }
    
    public void posting(){
        String kategori = String.valueOf(cmb_kategori.getValue());
        String tema = inp_judul.getText();
        String pengirim = inp_pengirim.getText();
        String email = GuruBK.getNip()==null?SessionId.getId():GuruBK.getEmail();
        String tglUpload = String.valueOf(LocalDateTime.now());
        String tglTenggat = String.valueOf(inp_tenggat.getValue());
        String contactPerson = inp_contact.getText();
        String role = GuruBK.getNip()==null?"guest":"guru";
        String fileFoto = String.valueOf(Math.random()-Math.random()+Math.random());
        
        try {
            sql = "INSERT INTO mading(kategori,tema,foto,pengirim,email,tgl_upload,tgl_kadaluarsa,role_pengirim,file_foto,contact_person) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            pst = koneksi.prepareStatement(sql);
            pst.setString(1, kategori);
            pst.setString(2, tema);
            pst.setBinaryStream(3,(InputStream) fis,(int)file.length() );
            pst.setString(4, pengirim);
            pst.setString(5, email);
            pst.setString(6, tglUpload);
            pst.setString(7, tglTenggat);
            pst.setString(8, role);
            pst.setString(9, fileFoto);
            pst.setString(10, contactPerson);
            
            if (pst.executeUpdate()>0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("success.png"));
                lbl_file.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean validation(){
        boolean showMessage = false;
        String validation[] = {inp_judul.getText().equals("")?"Judul":null,inp_pengirim.getText().equals("")?"Pengirim":null,inp_contact.getText().equals("")?"Contact Person":null,inp_tenggat.getValue()==null?"Tenggat":null,file==null?"Poster":null};
        String message = "Masukan ";
        for(String i : validation){
            if (i!=null) {
                showMessage = true;
                break;
            }
        }
        
        if (showMessage) {
            for (int i = 0; i < validation.length; i++) {
                if (validation[i]!=null) {
                    message+=validation[i];
                }
                if (i!=validation.length-1) {
                    message+=validation[i+1]!=null?", ":"";  
                }
            }
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
        }
        return showMessage;
    }
    
    private void prepareComboBox(){
        cmb_kategori.setItems(FXCollections.observableArrayList("Lomba","Workshop","Pengumuman","Lowongan"));
    }
    
    @FXML
    private void gotoHome(javafx.scene.input.MouseEvent event) throws IOException {
        String target = GuruBK.getNip()==null?"Mading.fxml":"guru/HalamanUtama.fxml";
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/"+target));
    }    

    @FXML
    private void backHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/Mading.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}
