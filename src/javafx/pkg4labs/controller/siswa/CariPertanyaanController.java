/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.pkg4labs.model.Pertanyaan;
import javafx.pkg4labs.model.Students;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class CariPertanyaanController implements Initializable {
    
    @FXML
    private ScrollPane scrl_Pane;
    
    @FXML
    private TextField inp_search;
    
    @FXML
    private Button butt_refresh,butt_search;
    
    @FXML
    public ComboBox comb_matpel;
    
    private HBox hor;
    private VBox ver;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private String sql;
    private ArrayList<Pertanyaan> listPertanyaan = new ArrayList<>();
    
    private AnchorPane box;
    private Label label_jawaban;
    private Label label_siswa;
    private Label label_matpel;
    private Label label_judul;
    private Line garis_oren;
    private Line garis_foto;
    private Hyperlink href;
    private ArrayList<Integer> id;
    ObservableList<String> matpel = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        try {
            stmt = koneksi.createStatement();
            comb_matpel.setValue("Pilih Kategori");
        } catch (SQLException ex) {
            Logger.getLogger(CariPertanyaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        showData();
        prepareComboBox();
    }
    
    public void prepareComboBox(){
        
        try{
 
            stmt = koneksi.createStatement();
            id = new ArrayList<>();
            String query = "SELECT * FROM tipe_soal";
            ResultSet res = stmt.executeQuery(query);
             while (res.next()) {
                 matpel.add(res.getString("nama_matpel"));
                 id.add(res.getInt("id_tipe"));
             }
            comb_matpel.setItems(matpel);
 
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showData(){
        
        //Clear List Data From Array List
        listPertanyaan = new ArrayList<>();
        
        
        //Declaration & Query
        ver = new VBox();
        sql = "SELECT * FROM pertanyaan ";
        
        //Filter
         if (comb_matpel.getValue()!="Pilih Kategori") {
            sql+=" JOIN tipe_soal ON pertanyaan.tipe_soal = tipe_soal.id_tipe WHERE nama_matpel = '"+comb_matpel.getValue()+"'";
         }
        
        //Executed when keyreleased 
        if (!inp_search.getText().equals("")) {
            if (comb_matpel.getValue()!="Pilih Kategori") {
                sql+=" AND pertanyaan LIKE '%"+inp_search.getText()+"%'";
            }else{
                sql+=" WHERE pertanyaan LIKE '%"+inp_search.getText()+"%'";
            }
        }
         try {
            res = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CariPertanyaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            listPertanyaan.clear();
            while(res.next()){
                Pertanyaan pertanyaan = new Pertanyaan(res.getString("id_pertanyaan"));
                listPertanyaan.add(pertanyaan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CariPertanyaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Pertanyaan pertanyaan : listPertanyaan){
            
            Students siswa = new Students(pertanyaan.getIdPengirim());
            
            hor = new HBox();
            box = new AnchorPane();
            Circle circle = new Circle();
            href = new Hyperlink();
            label_siswa = new Label();
            label_matpel = new Label();
            label_judul = new Label();
            label_jawaban = new Label();
            garis_oren = new Line(-3, 0, 940, 0);
            garis_foto = new Line(-101, -51, -101, 83);
            
            circle.setRadius(75);
            circle.setFill(new ImagePattern(new Students(pertanyaan.getIdPengirim()).getFoto()));
            circle.setLayoutX(95);
            circle.setLayoutY(98);
            
            label_siswa.setLayoutX(200);
            label_siswa.setLayoutY(25);
            
            label_matpel.setLayoutX(200);
            label_matpel.setLayoutY(118);
            
            label_judul.setLayoutX(200);
            label_judul.setLayoutY(59);
            
            label_jawaban.setLayoutX(200);
            label_jawaban.setLayoutY(160);
            
            href.setLayoutX(1003);
            href.setLayoutY(149);
            
            garis_oren.setLayoutX(197);
            garis_oren.setLayoutY(190);
            
            garis_foto.setLayoutX(288);
            garis_foto.setLayoutY(89);
            garis_foto.setOpacity(0.4);
            
            label_siswa.setText(siswa.getNama());
            label_judul.setText(pertanyaan.getIsiPertanyaan());
            label_matpel.setText(pertanyaan.getNamaTipeSoal());
            label_jawaban.setText(pertanyaan.getJmlJawaban()+" Jawaban");
            href.setText("Lihat Detail >>");
            
            label_siswa.setStyle("-fx-text-fill:black; -fx-font-size:18;");
            label_matpel.setStyle("-fx-text-fill:#07dbee; -fx-font-size:14;");
            label_judul.setStyle("-fx-text-fill:black; -fx-font-size:30;");
            label_jawaban.setStyle("-fx-text-fill:black; -fx-font-size:12;");
            href.setStyle("-fx-text-fill:black; -fx-font-size:19;");
            garis_oren.setStyle("-fx-stroke:#ff8800; -fx-stroke-width:3; -fx-stroke-type:centered; -fx-stroke-line-cap:square; -fx-stroke-line-join:miter; -fx-stroke-miter-limit:10;");
            garis_foto.setStyle("-fx-stroke:#000000; -fx-stroke-width:2; -fx-stroke-type:centered; -fx-stroke-line-cap:square; -fx-stroke-line-join:miter; -fx-stroke-miter-limit:10; ");
            
            box.getChildren().addAll(circle,garis_foto,label_siswa,label_matpel,label_judul,label_jawaban,href,garis_oren); 
            box.getStyleClass().add("box");
            box.getStylesheets().add("..\\..\\css\\ruangdiskusi.css");
            box.setPrefSize(1176, 216.0);
            hor.getChildren().add(box);
            hor.setPadding(new Insets(26, 20, 20, 37));
            ver.getChildren().add(hor);
            href.setOnMouseClicked((event) -> {
                SessionId.setIdToPertanyaan(pertanyaan.getIdPertanyaan());
                try {
                    detailPertanyaan(event);
                } catch (IOException ex) {
                    Logger.getLogger(PertanyaanSayaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        scrl_Pane.setContent(ver);
    }
    
    public void search(){

        if(inp_search.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan Keyword Pencarian");
        }else{
            showData();
        }
    }
    
    public void refresh() {
        comb_matpel.setValue("Pilih Kategori");
        inp_search.setText("");
        
        showData();
    }
    

    @FXML
    private void backToSelect(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangDiskusi.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void detailPertanyaan(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/DetailPertanyaan.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}