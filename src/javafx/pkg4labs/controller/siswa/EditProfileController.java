package javafx.pkg4labs.controller.siswa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
public class EditProfileController implements Initializable {
    
    Connection koneksi;
    String nis;
    boolean edit = false;
    int i;
    
    @FXML
    public ComboBox<String> comb_kelas;    
    ObservableList<String> kelas = FXCollections.observableArrayList();
    
    @FXML
    public ComboBox<String> comb_kelamin;    
    ObservableList<String> kelamin = FXCollections.observableArrayList();
    
    @FXML
    private TextField inp_nis;
    
    @FXML
    private TextField inp_nama;
    
    @FXML
    private TextField inp_email;
    
    @FXML
    private TextField inp_username;
    
    @FXML
    private DatePicker inp_tanggal;
    
    @FXML
    private PasswordField inp_password;
    
    @FXML
    private Button butt_edit;
    
    @FXML
    private Button butt_pilih;
    
    @FXML
    private Label lbl_file;
    
    @FXML
    private ImageView foto;
    
    @FXML
    private ImageView profile;
    
    PreparedStatement pst;
    
    private FileInputStream fis;
    
    private FileChooser fileChooser;
    
    private File file;
    
    private Window window;
    
    private Image image;
    
    private InputStream fotoLama = null;
    
  @Override
  public void initialize(URL url, ResourceBundle rb) {
       showData();
   }
        
    public void edit() throws FileNotFoundException, SQLException{
        if(!edit){
            setEditButton(true);
            butt_edit.setText("Simpan");
            edit = true;
        }
        else{
            if(!validation()){
                butt_edit.setText("Edit");
                edit = false;
                simpanEdit();
                setEditButton(false);
            }
        }
    }
    
     public void setEditButton(Boolean control){
        inp_nis.setEditable(control);
        inp_nama.setEditable(control);
        inp_email.setEditable(control);
        inp_tanggal.setDisable(!control);
        inp_username.setEditable(control);
        comb_kelamin.setDisable(!control);
        comb_kelas.setDisable(!control);
        butt_pilih.setDisable(!control);
        
    }
    
    public void showData(){
        try{
            
            profile.setImage(Siswa.getFoto());
            foto.setImage(Siswa.getFoto());
            
            // set a clip to apply rounded border to the original image.
            Rectangle clip = new Rectangle(
                profile.getFitWidth(), profile.getFitHeight()
            );
            
            clip.setArcWidth(100);
            clip.setArcHeight(100);
            profile.setClip(clip);

            // snapshot the rounded image.
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = profile.snapshot(parameters, null);

            // remove the rounding clip so that our effect can show through.
            profile.setClip(null);

            // apply a shadow effect.
            profile.setEffect(new DropShadow(30, Color.BLACK));

            // store the rounded image in the imageView.
            profile.setImage(image);
        
            koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            Statement stmt = koneksi.createStatement();
            
            nis = SessionSiswa.getSession();
            int length = 0;
            
            ObservableList<String> data = FXCollections.observableArrayList("Laki-laki","Perempuan");
            
             String query = "SELECT count(classes.nama_kelas) AS length FROM classes";     
             ResultSet rsItung = stmt.executeQuery(query);
           
             if(rsItung.first()){
                   length = rsItung.getInt("length");
              }   
        
             String[] listKelas = new String[length];
        
             String queryKelas = "SELECT * FROM classes";
             ResultSet rsKelas = stmt.executeQuery(queryKelas);
             
             while(rsKelas.next()){
                listKelas[i] = rsKelas.getString("nama_kelas");
                i++;
             }
             
            ObservableList<String> kelas = FXCollections.observableArrayList(listKelas);
            
            comb_kelas.setItems(kelas);
            comb_kelas.setValue(Siswa.getNamaKelas());
            comb_kelamin.setItems(data);
            inp_nis.setText(Siswa.getNis());
            inp_nama.setText(Siswa.getNama());
            comb_kelamin.setValue(Siswa.getJenisKelamin());
            inp_tanggal.setValue(LocalDate.parse(Siswa.getTanggalLahir()));
            inp_username.setText(Siswa.getUsername());
            inp_email.setText(Siswa.getEmail());
        
          }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                 e.printStackTrace();
        }
        butt_pilih.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonBrowse(event);
            }
        });
       
    }
     
    public boolean validation(){
        boolean showMessage = false;
        String validation[] = {inp_nama.getText().equals("")?"Nama":null,inp_email.getText().equals("")?"Email":null,inp_username.getText().equals("")?"Username":null};
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
            image = new Image(file.toURI().toString(),100,150,true,true);
            foto.setImage(image);
            
            profile.setImage(image);
            
        }
    }
    
    
    public void simpanEdit(){
        
        String nis          = inp_nis.getText();
        String nama         = inp_nama.getText();
        String nama_kelas   = comb_kelas.getValue();
        String jk           = comb_kelamin.getValue();
        LocalDate tgl_lahir = inp_tanggal.getValue();
        String username     = inp_username.getText();
        String email        = inp_email.getText();
        
         try{
            
             String query = "UPDATE students SET "
                      + "nama        = ?,"
                      + "nama_kelas  = ?,"
                      + "jk          = ?,"
                      + "tgl_lahir   = ?,"
                      + "username    = ?,"
                      + "email       = ?"+(file != null?",foto = ? ,file = ? ":" ")
                      + "WHERE nis = ?";
             
            pst = koneksi.prepareStatement(query);
            
            if (file != null) {
                fis = new FileInputStream(file);
            }
            
            pst.setString(1, nama);
            pst.setString(2, nama_kelas);
            pst.setString(3, jk);
            pst.setString(4, tgl_lahir.toString());
            pst.setString(5, username);
            pst.setString(6, email);
             if (file==null) {
                 pst.setString(7,nis);
            }else{
                pst.setBinaryStream(7,(InputStream)fis,(int)file.length());
                pst.setString(8, nis);
                pst.setString(9, nis);
            }
            
            if (pst.executeUpdate()>0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("success.png"));
                Siswa.setSiswa(nis);
                lbl_file.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("error.png"));
            }
 
            } catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada query");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Siswa.setSiswa(nis);
         foto.setImage(Siswa.getFoto());
         profile.setImage(Siswa.getFoto());
    }  

@FXML
    private void ruangBK(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void ruangSiswa(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    } 

    @FXML
    private void jadwalPelajaran(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/JadwalPelajaran.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoEditProfile(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/EditProfile.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoInfoDev(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/InfoDeveloper.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoLogout(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/LoginSiswa.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }    
    
}