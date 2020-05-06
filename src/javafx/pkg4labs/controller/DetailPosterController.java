/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.guest.SessionGuest;
import javafx.pkg4labs.controller.guru.SessionId;
import javafx.pkg4labs.controller.siswa.EditProfileController;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.Mading;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class DetailPosterController implements Initializable {
    
    @FXML
    private TextField inp_tema,inp_pengirim,inp_posting,inp_tenggat,inp_contact;
    
    @FXML
    private Button button,button_browse;
    
    @FXML
    private ImageView imgv_poster;
    
    @FXML
    private Label lbl_file;
    
    @FXML
    private DatePicker picker_tenggat;
    
    private Mading poster;
    
    private InputStream is;
    
    private FileChooser fc;
    
    private File file;
    
    private FileInputStream fis;
    
    private Window window;
    
    private Image image;
    
    private PreparedStatement pst;
    
    private Connection koneksi;
    
    private String id;
    
    private boolean edit = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        showData();
        prepareListener();
    
    }
    public void prepareListener(){
        button_browse.setOnAction((event) -> {
                fc = new FileChooser();
                window = ((Node)event.getTarget()).getScene().getWindow();
                file = fc.showOpenDialog(window);
                if (file != null) {
                lbl_file.setText(file.getAbsolutePath());
                image = new Image(file.toURI().toString(),433,553,false,true);
                imgv_poster.setImage(image);

            }
        });
        
        if (checkOwner()) {
            button.setOnMouseClicked((event) -> {
                controlEdit();
            });
        }
    }
    
    public void controlEdit(){
        if (!edit) {
            controlElement(true);
            edit = !edit;
        }else{
            checkInput();
        }
    }
    
    public void checkInput(){
        if(!validation()) {
            update();
            controlElement(false);
            edit = !edit;
        }
    }
    
    public boolean validation(){
        boolean showMessage = false;
        String validation[] = {inp_tema.getText().equals("")?"Tema":null,inp_pengirim.getText().equals("")?"Pengirim":null,inp_contact.getText().equals("")?"Contact Person":null,picker_tenggat.getValue()==null?"Tenggat":null,imgv_poster.getImage()==null?"Poster":null};
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
    
    public void showData(){
        id = SessionGuest.getIdToDetPoster()!=null?SessionGuest.getIdToDetPoster():SessionId.getIdToDetPoster();
        try {
            System.out.println("Session : "+id);
            poster = new Mading(id);
            inp_tema.setText(poster.getTema());
            inp_contact.setText(poster.getContactPerson());
            inp_pengirim.setText(poster.getPengirim());
            inp_posting.setText(poster.getTanggalUpload());
            inp_tenggat.setText(poster.getTanggalKadaluarsa());
            picker_tenggat.setValue(LocalDate.parse(poster.getTanggalKadaluarsa()));
            imgv_poster.setImage(poster.getFoto());
        } catch (SQLException ex) {
            Logger.getLogger(DetailPosterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailPosterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void controlElement(boolean bln){
        inp_contact.setEditable(bln);
        inp_pengirim.setEditable(bln);
        inp_tema.setEditable(bln);
        inp_tenggat.setEditable(bln);
        picker_tenggat.setVisible(bln);
        picker_tenggat.setEditable(false);
        button_browse.setVisible(bln);
        button.setText(bln==true?"Simpan":"Edit");
    }
    
    public void update(){
        String tema         = inp_tema.getText();
        String contact      = inp_contact.getText();
        String pengirim     = inp_pengirim.getText();
        String tenggat      = String.valueOf(picker_tenggat.getValue());
        
        try{
            
             String query = "UPDATE mading SET "
                      + "tema           = ?,"
                      + "pengirim       = ?,"
                      + "tgl_kadaluarsa = ?,"
                      + "contact_person = ?"+(file != null?",foto = ? ":" ")
                      + "WHERE id_mading = ?";
             
            pst = koneksi.prepareStatement(query);
            
            if (file != null) {
                fis = new FileInputStream(file);
            }
            
            pst.setString(1, tema);
            pst.setString(2, pengirim);
            pst.setString(3, tenggat);
            pst.setString(4, contact);
             if (file==null) {
                 pst.setString(5, id);
            }else{
                pst.setBinaryStream(5,(InputStream)fis,(int)file.length());
                pst.setString(6, id);
            }
            
            if (pst.executeUpdate()>0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("success.png"));
                lbl_file.setText("");
                controlElement(false);
            }else{
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
            }
 
            } catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada query");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkOwner(){
        boolean owner = false;
        if (SessionGuest.getEmail()!=null) {
            if (SessionGuest.getEmail().equalsIgnoreCase(poster.getTandaPengenal())) {
                owner = true;
            }
            
        }else if(GuruBK.getNip()!=null){
           if (GuruBK.getNip().equalsIgnoreCase(poster.getTandaPengenal())) {
                owner = true;
            }     
        }
        button.setVisible(owner);
        return owner;
    }

    @FXML
    private void backHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/Mading.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}
