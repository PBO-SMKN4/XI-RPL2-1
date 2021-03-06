/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.pkg4labs.model.GuruBK;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class ProfileBKController implements Initializable{
    boolean edit = false;
    
    @FXML
    private TextField inp_nip;
    
    @FXML
    private TextField inp_nama;
    
    @FXML
    public ComboBox<String> com_jk; 
    
    @FXML
    private DatePicker dat_lahir;
    
    @FXML
    private TextField inp_username;
    
    @FXML
    private PasswordField inp_password;
    
    @FXML
    private TextField inp_email;
    
    @FXML
    private TextField inp_wa;
    
    @FXML
    private Button but_edit;
    
    @FXML
    private Button but_browse = new Button();
    
    @FXML
    private Label lbl_file;
    
    @FXML
    private ImageView foto;
    
    @FXML
    private Circle profile;
    
    PreparedStatement pst;
    
    private FileInputStream fis;
    
    private FileChooser fileChooser;
    
    private File file;
    
    private Window window;
    
    private Image image;
    
    private InputStream fotoLama = null;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepareImage();
        show();
        prepareListener();
    }
    
    public void prepareImage(){
        profile.setEffect(new DropShadow(30, Color.BLACK));
        profile.setFill(new ImagePattern(GuruBK.getFoto()));
        foto.setImage(GuruBK.getFoto());
    }
    
    public void edit() throws SQLException, FileNotFoundException{       
        if(!edit){
                but_edit.setText("Simpan");
                seteditButton(true);
                edit = true;
        }
        else{
            if(!validation()){
                saveEdit();
                but_edit.setText("Edit");
                edit = false;
                seteditButton(false);
            } 
        }
    }
    
    public void seteditButton(Boolean control){
        inp_nama.setEditable(control);
        com_jk.setDisable(!control);
        dat_lahir.setDisable(!control);
        inp_username.setEditable(control);
        inp_email.setEditable(control);
        inp_wa.setEditable(control);
        but_browse.setDisable(!control);
    }
    
    public boolean validation(){
        boolean showMessage = false;
        String validation[] = {inp_nama.getText().equals("")?"Nama":null,inp_email.getText().equals("")?"Email":null,inp_username.getText().equals("")?"Username":null,inp_wa.getText().equals("")?"No Whatsapp":null};
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
    
    public void buttonBrowse(ActionEvent e){
        fileChooser = new FileChooser();
        window = ((Node)e.getTarget()).getScene().getWindow();
        file = fileChooser.showOpenDialog(window);
        if (file != null) {
            lbl_file.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString(),140,160,false,true);
            
            foto.setImage(image);
            
            profile.setFill(new ImagePattern(image));
            
        }
    }
    
    public void prepareListener(){
         but_browse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonBrowse(event);
            }
        });
    }
    
    public void saveEdit() throws SQLException, FileNotFoundException{
        String nip = inp_nip.getText();
        String nama = inp_nama.getText();
        String jk = com_jk.getValue();
        LocalDate tgl_lahir = dat_lahir.getValue();
        String username = inp_username.getText();
        String email = inp_email.getText();
        String wa = inp_wa.getText();
        
        try {
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            
            String query = "UPDATE teachers SET "
                      + "nama        = ?,"
                      + "jk          = ?,"
                      + "tgl_lahir   = ?,"
                      + "username    = ?,"
                      + "email       = ?,"
                      + "no_whatsapp = ?"+(file != null?",foto = ? ,file = ? ":" ")
                      + "WHERE nip = ?";
            pst = koneksi.prepareStatement(query);
            
            
            if (file != null) {
                fis = new FileInputStream(file);
            }else if(file == null && GuruBK.getInputStreamFoto()!=null){
                
            }
            pst = koneksi.prepareStatement(query);
            
            pst.setString(1, nama);
            pst.setString(2, jk);
            pst.setString(3, tgl_lahir.toString());
            pst.setString(4, username);
            pst.setString(5, email);
            pst.setString(6, wa);
            
            if (file==null) {
                pst.setString(7,nip);
            }else{
                pst.setBinaryStream(7,(InputStream)fis,(int)file.length());
                pst.setString(8, nip);
                pst.setString(9, nip);
            }
            
            if (pst.executeUpdate()>0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("success.png"));
                GuruBK.setGuruBK(nip);
                lbl_file.setText("");
                
            }else{
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
            }
             
        }catch(Exception ex){
                ex.printStackTrace();
        
        }
    }
    
    public void show(){
        ObservableList<String> Jenis_Kelamin = FXCollections.observableArrayList("Laki-laki","Perempuan");
       
        com_jk.setItems(Jenis_Kelamin);
        inp_nip.setText(GuruBK.getNip());
        inp_nama.setText(GuruBK.getNama());
        com_jk.setValue(GuruBK.getJenisKelamin());
        dat_lahir.setValue(LocalDate.parse(GuruBK.getTanggalLahir()));
        inp_username.setText(GuruBK.getUsername());
        inp_email.setText(GuruBK.getEmail());
        inp_wa.setText(GuruBK.getNoWa());
    }
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void gotoHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoEditProfile(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/ProfileBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoInfoDev(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/InfoDeveloper.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoLogout(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/LoginGuru.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
}