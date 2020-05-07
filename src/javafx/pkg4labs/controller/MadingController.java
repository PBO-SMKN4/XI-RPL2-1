/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.pkg4labs.controller.guest.SessionGuest;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.interfaceModel.TabelData;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.Mading;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class MadingController implements Initializable, TabelData {
    
    @FXML
    private ScrollPane scrlPane_mading;
    
    private HBox hor;
    private VBox ver;
    private AnchorPane box;
    private Button btnUpload;
    private AnchorPane paneRefresh;
    private ImageView imgvRefresh;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private String sql;
    private ArrayList<Mading> listMading = new ArrayList<>();
    private ImageView content;
    private Hyperlink href;
    private TextField inp_search = new TextField();
    private Timeline searchDelay = new Timeline();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        try {
            stmt = koneksi.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MadingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        inp_search.setOnKeyTyped((event) -> {
            searchDelay.stop();
            search();
        });
        
        showData();
        
    }
    
    @Override
    public void showData(){
        //Clear List Data From Array List
        listMading.clear();
        
        //Declaration & Query
        int counterHor = 0;
        int counterSize = 0;
        ver = new VBox();
        hor = new HBox();
        sql = "SELECT * FROM mading WHERE tgl_kadaluarsa > '"+String.valueOf(LocalDate.now())+"'";
        //Executed when keyreleased 
        if (!inp_search.getText().equals("")) {
            sql+=" AND kategori LIKE '%"+inp_search.getText()+"%' "
                    + "OR tema LIKE '%"+inp_search.getText()+"%' "
                    + "OR pengirim LIKE '%"+inp_search.getText()+"%' ";
        }
        try {
            res = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MadingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(res.next()){
                Mading mading = new Mading(res.getString("id_mading"));
                listMading.add(mading);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MadingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MadingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Set Style & Layout
        box = new AnchorPane();
        btnUpload = new Button("Upload Poster");
        imgvRefresh = new ImageView(new Image("file:src/javafx/assets/image/refresh2.png"));
        paneRefresh = new AnchorPane();
        btnUpload.setLayoutX(124);
        btnUpload.setLayoutY(41);
        btnUpload.setPrefSize(120,50);
        imgvRefresh.setLayoutX(8);
        imgvRefresh.setLayoutY(7);
        imgvRefresh.setFitWidth(30);
        imgvRefresh.setFitHeight(30);
        paneRefresh.setLayoutX(1108);
        paneRefresh.setLayoutY(40);
        paneRefresh.setPrefSize(45, 45);
        paneRefresh.setStyle("-fx-background-color:#ff8800; -fx-background-radius:10;");
        paneRefresh.getChildren().add(imgvRefresh);
        inp_search.setLayoutX(375);
        inp_search.setLayoutY(40);
        inp_search.setPrefSize(590, 50);
        //Add Listener
        btnUpload.setOnMouseClicked((event) -> {
            try {
                uploadPoster(event);
            } catch (IOException ex) {
                Logger.getLogger(MadingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
        if (Siswa.getNis()!=null) {
            btnUpload.setDisable(true);
            btnUpload.setText("Mading SMKN 4");
        }
        paneRefresh.setOnMouseClicked((event) -> {
            inp_search.setText("");
            refresh();
        });
        box.getChildren().addAll(btnUpload,inp_search,paneRefresh);
        hor.getChildren().add(box);
        hor.setPadding(new Insets(30, 0, 50, 0));
        ver.getChildren().add(hor);
        hor = new HBox();
        
        for(Mading mading : listMading){
            //Do Increment
            counterHor++;
            counterSize++;
            
            //Preparing Elements
            box = new AnchorPane();
            content = new ImageView(mading.getFoto());
            href = new Hyperlink("Lihat Detail >>>");
            
            //Set Fit Size
            content.setFitWidth(249);
            content.setFitHeight(326);
            
            //Set Pref Size
            box.setPrefSize(307, 421);
            
            //Set Font Size
            href.setFont(new Font(20));
            
            //Set Layout
            content.setLayoutX(30);
            content.setLayoutY(27);
            href.setLayoutX(82);
            href.setLayoutY(368);
            
            switch(counterHor){
                case 1:
                    box.setLayoutX(116);
                    break;
                case 2:
                    box.setLayoutX(494);
                    break;
                case 3:
                    box.setLayoutX(883);
                    break;
                default:
                    break;
            }
            
            
            //Set Padding
            if (counterSize==1) {
                hor.setPadding(new Insets(700, 100, 20, 123));
            }else{
                hor.setPadding(new Insets(20, 100, 20, 123));
            }
            
            //Set Spacing
            hor.setSpacing(55);
            
            //Set Style
            box.setStyle("-fx-background-color:white; -fx-background-radius:20;");
            
            //Add EventListener
            href.setOnMouseClicked((event) -> {
                try {
                    SessionGuest.setIdToDetPoster(mading.getIdMading());
                    detailPoster(event);
                } catch (IOException ex) {
                    Logger.getLogger(MadingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
           
            //Bound Contents to AnchorPane
            box.getChildren().addAll(content,href);
            
            //Add to HBox
            hor.getChildren().add(box);
            
            //TODO logic
            if (counterHor == 3 || counterSize==listMading.size()) {
                ver.getChildren().add(hor);
                hor = new HBox();
                counterHor = 0;
            }
        }
        scrlPane_mading.setContent(ver);
        
    }
    
    
    @FXML
    private void backHome(javafx.scene.input.MouseEvent event) throws IOException {
        String role = Siswa.getNis()==null?(GuruBK.getNip()==null?null:"guru"):"siswa";
        Parent root = FXMLLoader.load(getClass().getResource(role==null?"/javafx/pkg4labs/view/guest/HalamanUtama.fxml":"/javafx/pkg4labs/view/"+role+"/SelectInfo.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void detailPoster(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/DetailPoster.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @Override
    public void search(){
        searchDelay = new Timeline(new KeyFrame(Duration.millis(400), ev -> {
            showData();
        }));
        searchDelay.play();
        
    }

    @Override
    public void refresh() {
        showData();
    }

    @FXML
    private void uploadPoster(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/UploadPoster.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}
