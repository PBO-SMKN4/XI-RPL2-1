/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.guest.SessionGuest;
import javafx.pkg4labs.controller.guru.SessionId;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.Mading;
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
    private TextField inp_judul,inp_pengirim,inp_contact,hdn_kategori,hdn_tenggat;
    
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
    
    @FXML
    private Button butt_control;
    
    private Image image;
    
    private FileInputStream fis;
    
    private FileChooser fileChooser;
    
    private Window window;
    
    private File file;
    
    //Prepare For Query Database
    private Connection koneksi;
    private PreparedStatement pst;
    private ResultSet res;
    private String sql;
    
    private boolean mine = false,edit = false;
    
    private ArrayList<Mading> mading;
    
    public void setValueInput(String id){
        if (id != null) {
            try {
                Mading mading = new Mading(id);
                inp_judul.setText(mading.getTema());
                inp_pengirim.setText(mading.getPengirim());
                inp_contact.setText(mading.getContactPerson());
                hdn_kategori.setText(mading.getKategori());
                hdn_tenggat.setText(mading.getTanggalKadaluarsa());
            } catch (SQLException | IOException ex) {
                Logger.getLogger(UploadPosterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        prepareComboBox();
        prepareListener();
    }
    
    public void browse(ActionEvent e){
        fileChooser = new FileChooser();
        window = ((Node)e.getTarget()).getScene().getWindow();
        file = fileChooser.showOpenDialog(window);
        if (file != null) {
            lbl_file.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString(),249,326,false,true,false);
            imgv_poster.setImage(image);
        }else{
            imgv_poster.setImage(null);
        }
    }
    
    public void checkInput(){
        if(!validation()) {
            posting();
        }
    }
    
    public void prepareListener(){
        butt_browse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                browse(event);
            }
        });
    }
    
    public void posting(){
        String kategori = String.valueOf(cmb_kategori.getValue());
        String tema = inp_judul.getText();
        String pengirim = inp_pengirim.getText();
        String pengenal = GuruBK.getNip()==null?SessionId.getId():GuruBK.getNip();
        String tglUpload = String.valueOf(LocalDate.now());
        String tglTenggat = String.valueOf(inp_tenggat.getValue());
        String contactPerson = inp_contact.getText();
        String role = GuruBK.getNip()==null?"guest":"guru";
        String fileFoto = String.valueOf(Math.random()-Math.random()+Math.random());
        
        try {
            if (file != null) {
                fis = new FileInputStream(file);
            }
            sql = "INSERT INTO mading(kategori,tema,foto,pengirim,pengenal,tgl_upload,tgl_kadaluarsa,role_pengirim,file_foto,contact_person) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            pst = koneksi.prepareStatement(sql);
            pst.setString(1, kategori);
            pst.setString(2, tema);
            pst.setBinaryStream(3,(InputStream) fis,(int) file.length());
            pst.setString(4, pengirim);
            pst.setString(5, pengenal);
            pst.setString(6, tglUpload);
            pst.setString(7, tglTenggat);
            pst.setString(8, role);
            pst.setString(9, fileFoto);
            pst.setString(10, contactPerson);

            

            if (pst.executeUpdate()>0) {
                InputStream is = (InputStream) fis;
                OutputStream os = new FileOutputStream(new File("src/mading/"+fileFoto+".jpg"));
                byte[] content = new byte[1024];
                int size = 0;
                while((size = is.read()) != -1){
                    os.write(content, 0, size);
                }
                os.close();
                is.close();

                image = new Image("file:src/mading/"+fileFoto+".jpg",249,326,true,true);
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("success.png"));
                lbl_file.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadPosterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadPosterController.class.getName()).log(Level.SEVERE, null, ex);
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
        cmb_kategori.setItems(FXCollections.observableArrayList("Event","Lomba","Workshop","Pengumuman","Lowongan"));
    }
    
    @FXML
    private void gotoHome(javafx.scene.input.MouseEvent event) throws IOException {
        String target = GuruBK.getNip()==null?"Mading.fxml":"guru/HalamanUtama.fxml";
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/"+target));
    }    

    @FXML
    private void backHome(javafx.scene.input.MouseEvent event) throws IOException {
        SessionId.setIdToDetPoster(null);
        SessionGuest.setIdToDetPoster(null);
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/Mading.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}
