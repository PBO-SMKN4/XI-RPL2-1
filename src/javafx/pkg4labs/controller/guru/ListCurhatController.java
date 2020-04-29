/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.ListCurhat;
import javafx.pkg4labs.model.PesanCurhat;
import javafx.pkg4labs.model.Students;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class ListCurhatController implements Initializable {

    @FXML
    private ImageView imgv_fotoGuru;
    
    @FXML
    private Label lbl_namaGuru;
    
    @FXML
    private ScrollPane scrl_pane;
    
    private ListCurhat listPesan;
    
    private AnchorPane box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       show();
    }
    
    public void show(){
        
        VBox ver = null;
        HBox hor = null;
        imgv_fotoGuru.setImage(GuruBK.getFoto());
        lbl_namaGuru.setText(GuruBK.getNama());
        Circle imgSiswa;
        ver = new VBox();
        listPesan = new ListCurhat();
        
        for(PesanCurhat psn : listPesan.getPesan()){
            Students siswa = new Students(psn.getIdPengirim());
            imgSiswa = new Circle();
            Line garis = new Line(-100, 0, 992, 0);
            imgSiswa.setStroke(Color.TRANSPARENT);
            System.out.println("Foto : "+siswa.getFoto());
            System.out.println("File : "+siswa.getFileFoto());
            Image pf = new Image("file:src/profile/"+siswa.getFileFoto()+".jpg", false);
            hor = new HBox();
            box = new AnchorPane();
            Label nama = new Label(psn.getNamaPengirim());
            Label isi = new Label(psn.getIsiPesan());
            Label waktu = new Label(psn.getWaktuKirim());
            imgSiswa.setFill(new ImagePattern(pf));
            imgSiswa.setLayoutY(61);
            imgSiswa.setLayoutX(75);
            nama.setLayoutX(151);
            nama.setLayoutY(22);
            nama.setStyle("-fx-font-size:26px;");
            isi.setLayoutX(151);
            isi.setLayoutY(65);
            isi.setStyle("-fx-font-size:18px;");
            waktu.setLayoutX(1207);
            waktu.setLayoutY(68);
            garis.setLayoutX(252);
            garis.setLayoutY(110);
            garis.setStroke(Paint.valueOf("#8a8a8a"));
            box.setPrefSize(1266, 122);
            box.getChildren().addAll(imgSiswa,nama,isi,waktu,garis);
            hor.getChildren().add(box);
            
            ver.getChildren().add(hor);
        }
        scrl_pane.setContent(ver);
        
    }

    @FXML
    private void detailCurhat(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/Curhat.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }    
    
}
