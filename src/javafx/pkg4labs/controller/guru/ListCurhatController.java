/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.ListCurhat;
import javafx.pkg4labs.model.PesanCurhat;
import javafx.pkg4labs.model.RuangCurhat;
import javafx.pkg4labs.model.Students;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class ListCurhatController implements Initializable {

    @FXML
    private Circle profile;
    
    @FXML
    private Label lbl_namaGuru;
    
    @FXML
    private ScrollPane scrl_pane;
    
    @FXML
    private TextField inp_query;
    
    private ListCurhat listPesan;
    
    private AnchorPane box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       show();
       inp_query.setOnKeyPressed((event) -> {
           if (event.getCode()==KeyCode.ENTER) {
                search();
           }else if(event.getCode()==KeyCode.BACK_SPACE){
               if (inp_query.getText().length()==1) {
                   show();
               }
           }
       });
       Image foto = new Image("file:src/profile/"+GuruBK.getFileFoto()+".jpg", false);
       profile.setEffect(new DropShadow(30, Color.BLACK));
       profile.setFill(new ImagePattern(foto));
    }
    
    public void show(){
        
        VBox ver = null;
        HBox hor = null;
        lbl_namaGuru.setText(GuruBK.getNama());
        Circle imgSiswa;
        ver = new VBox();
        if (inp_query.getText().equals("")) {
            listPesan = new ListCurhat();
        }else{
            listPesan = new ListCurhat(inp_query.getText());
        }
        RuangCurhat rc;
        
        int temp = 0;
        for(PesanCurhat psn : listPesan.getPesan()){
            rc = new RuangCurhat(psn.getIdPengirim(), GuruBK.getNip());
            SessionId.setIdInt(rc.getJmlBelumDibaca());
            Students siswa = new Students(psn.getIdPengirim());
            imgSiswa = new Circle(50);
            Line garis = new Line(-100, 0, 992, 0);
            Image pf = new Image("file:src/profile/"+siswa.getFileFoto()+".jpg", false);
            imgSiswa.setFill(new ImagePattern(pf));
            imgSiswa.setLayoutY(61);
            imgSiswa.setLayoutX(75);
            hor = new HBox();
            box = new AnchorPane();
            Label nama = new Label(psn.getNamaPengirim());
            Label isi = new Label(psn.getIsiPesan());
            Label waktu = new Label(psn.getWaktuKirim());
            if (rc.getJmlBelumDibaca()>0) {
                waktu.setStyle("-fx-font-color:#18f518;");
            }
            
            nama.setLayoutX(151);
            nama.setLayoutY(22);
            nama.setStyle("-fx-font-size:26px;");
            isi.setLayoutX(151);
            isi.setLayoutY(65);
            isi.setStyle("-fx-font-size:18px;");
            waktu.setLayoutX(1201);
            waktu.setLayoutY(20);
            garis.setLayoutX(252);
            garis.setLayoutY(110);
            garis.setStroke(Paint.valueOf("#8a8a8a"));
            
            Circle belumDibaca = new Circle(17);
            belumDibaca.setFill(Paint.valueOf("#18f518"));
            Text text = new Text(String.valueOf(rc.getJmlBelumDibaca()));
            text.setStyle("-fx-font-weight:bold;");
            text.setFill(Paint.valueOf("#ffffff"));
            text.setBoundsType(TextBoundsType.VISUAL); 
            StackPane stack = new StackPane();
            stack.setLayoutX(1201);
            stack.setLayoutY(58);
            stack.getChildren().addAll(belumDibaca,text);
            
            if (rc.getJmlBelumDibaca()==0) {
                stack.setVisible(false);
            }
            
            box.setPrefSize(1266, 122);
            box.getChildren().addAll(imgSiswa,nama,isi,waktu,garis,stack);
            hor.getChildren().add(box);
            if (temp>0&&temp!=listPesan.getPesan().size()) {
                box.setPadding(new Insets(20, 0, 0, 0));
            }
            
            hor.setOnMouseClicked((event) -> {
                SessionId.setId(siswa.getNis());
                try {
                    detailCurhat(event);
                } catch (IOException ex) {
                    Logger.getLogger(ListCurhatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            hor.getStylesheets().add("..\\..\\css\\listcurhat.css");
            hor.getStyleClass().add("list-chat");
            ver.getChildren().add(hor);
        }
        scrl_pane.setContent(ver);
        
    }
    
    public void search(){
        show();
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
