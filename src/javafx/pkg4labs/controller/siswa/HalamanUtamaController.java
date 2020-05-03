/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class HalamanUtamaController implements Initializable {

    @FXML
    private Circle profile;
    
    @FXML
    private TextField inp_query;
    
    @FXML
    private ImageView imgv_search;
    
    @FXML
    private Label lbl_error;
    
    @FXML
    private AnchorPane root;
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            prepareImage();
            prepareListener();
            lbl_error.setOpacity(0);
            lbl_error.setTextFill(Paint.valueOf("#f80303"));
    }
    
    public void prepareImage(){
        profile.setEffect(new DropShadow(30, Color.BLACK));
        profile.setFill(new ImagePattern(Siswa.getFoto()));
    }
    
    public void search() throws URISyntaxException, InterruptedException{
        if (!inp_query.getText().equals("")) {
             if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    String query = inp_query.getText();
                    lbl_error.setText("Mohon Tunggu...");
                    lbl_error.setTextFill(Paint.valueOf("#0583F7"));;
                    opacityDecrease(1.2);
                    URI uri = new URI(
                    "https://google.co.id/search?q="+parseToValidURL(query));
                  desktop.browse(uri);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Pilih Browser Default Anda di setting > apps > default apps > browser lalu pilih browser yang ingin anda gunakan");
                  ex.printStackTrace();
                }
               }
        }else{
            opacityDecrease(1);
        }
    }
    
    public void prepareListener(){
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               inp_query.setFocusTraversable(false);
               root.requestFocus();
            }
        });
        
       inp_query.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    try {
                        search();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    
    private void opacityDecrease(double opacity){
        double dec = opacity-0.1;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), ev -> {
                lbl_error.setOpacity(dec);
                if (dec>0) {
                    opacityDecrease(dec); 
                }else{
                    lbl_error.setTextFill(Paint.valueOf("#f80303"));
                    lbl_error.setText("Masukan Keyword !");
                }
            }));
        timeline.play();
    }
    
    private String parseToValidURL(String url){
        String[] division = url.split("\\s");
        String result = "";
        int counter = 1;
        for (String string : division) {
            if (counter!=division.length) {
                result+=string+"+";
            }else{
                result+=string;
            }
            counter++;
        }
        return result;
    }

    @FXML
    private void ruangBK(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
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
    private void gotoInfoDev(javafx.scene.input.MouseEvent event) throws IOException, URISyntaxException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/InfoDeveloper.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void gotoLogout(javafx.scene.input.MouseEvent event) throws IOException {
        if(JOptionPane.showConfirmDialog(null, "Yakin Ingin Logout?")==0){
            Siswa.setSiswa(null);
            Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/LoginSiswa.fxml"));
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();        
            stage.setScene(new Scene(root));
        }

    }
    
    @FXML
    private void selectInfo(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/SelectInfo.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void ruangBelajar(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBelajar.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    
}
