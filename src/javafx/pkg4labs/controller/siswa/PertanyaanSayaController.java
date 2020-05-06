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
import javafx.pkg4labs.interfaceModel.TabelData;
import javafx.pkg4labs.model.Pertanyaan;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dean Beniqno A
 */
public class PertanyaanSayaController implements Initializable, TabelData {
    
    @FXML
    private ScrollPane scrl_Pane;
    
    @FXML
    private TextField inp_search;
    
    private HBox hor;
    private VBox ver;
    Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private String sql;
    private ArrayList<Pertanyaan> listPertanyaan = new ArrayList<>();
    private ComboBox comb_filter;
    private Button butt_refresh;
    private Button butt_search;
    private AnchorPane box;
    private Label label_jawaban;
    private Label label_judul;
    private Label label_matpel;
    private Line garis_item;
    private Hyperlink href;
    private ArrayList<Integer> id;
    public ComboBox<String> comb_matpel;    
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
            Logger.getLogger(PertanyaanSayaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        inp_search = new TextField();
        
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
        listPertanyaan.clear();
        
        //Declaration & Query
        ver = new VBox();
        sql = "SELECT * FROM pertanyaan JOIN tipe_soal ON pertanyaan.tipe_soal = tipe_soal.id_tipe WHERE 1";
        
       //Filter
         if (comb_matpel.getValue()!=null) {
               if(comb_matpel.getValue()!="Pilih Kategori"){
                   sql+=" JOIN tipe_soal ON pertanyaan.tipe_soal = tipe_soal.id_tipe WHERE nama_matpel = '"+comb_matpel.getValue()+"'";
               }
         }
        
        //Executed when keyreleased 
        if (!inp_search.getText().equals("")) {
            sql+=" AND (tipe_soal LIKE '%"+inp_search.getText()+"%' "
                    + "OR pertanyaan LIKE '%"+inp_search.getText()+"%') ";
        }
         try {
            res = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PertanyaanSayaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(res.next()){
                Pertanyaan pertanyaan = new Pertanyaan(res.getString("id_pertanyaan"));
                listPertanyaan.add(pertanyaan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PertanyaanSayaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Pertanyaan pertanyaan : listPertanyaan){
            
            hor = new HBox();
            box = new AnchorPane();
            ImageView imgv = new ImageView();
            href = new Hyperlink();
            label_judul = new Label();
            label_jawaban = new Label();
            label_matpel = new Label();
            garis_item = new Line(-43, 0, 940, 0);
            
            imgv.setImage(new Image("file:src/javafx/assets/image/task.png"));
            imgv.setFitWidth(109);
            imgv.setFitHeight(110);
            imgv.setLayoutX(28);
            imgv.setLayoutY(38);
            
            label_judul.setLayoutX(150);
            label_judul.setLayoutY(36);
            
            label_matpel.setLayoutX(153);
            label_matpel.setLayoutY(89);
            
            label_jawaban.setLayoutX(152);
            label_jawaban.setLayoutY(124);
            
            href.setLayoutX(1003);
            href.setLayoutY(94);
            
            garis_item.setLayoutX(197);
            garis_item.setLayoutY(154);
            
            label_judul.setText(pertanyaan.getIsiPertanyaan());
            label_matpel.setText(pertanyaan.getNamaTipeSoal());
            label_jawaban.setText(pertanyaan.getJmlJawaban()+" Jawaban");
            href.setText("Lihat Detail >>");
            
            label_judul.setStyle("-fx-text-fill:black; -fx-font-size:30;");
            label_jawaban.setStyle("-fx-text-fill:black; -fx-font-size:12;");
            href.setStyle("-fx-text-fill:black; -fx-font-size:19;");
            label_matpel.setStyle("-fx-text-fill:#07dbee; -fx-font-size:14;");
            garis_item.setStyle("-fx-stroke:black; -fx-stroke-width:3; -fx-stroke-type:centered; -fx-stroke-line-cap:square; -fx-stroke-line-join:miter; -fx-stroke-miter-limit:10;");
            
            box.getChildren().addAll(imgv,label_judul,label_matpel,label_jawaban,href,garis_item); 
            box.getStyleClass().add("box");
            box.getStylesheets().add("..\\..\\css\\ruangdiskusi.css");
            box.setPrefSize(1176, 194);
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
    @FXML
    private void backToSelect(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangDiskusi.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
    @Override
    public void search(){
        if(inp_search.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan Keyword Pencarian");
        }else{
            showData();
        }
    }
    
    @Override
    public void refresh() {
        comb_matpel.setValue("Pilih Kategori");
        inp_search.setText("");
        
        showData();
    }
    
    
    @FXML
    private void detailPertanyaan(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/DetailPertanyaan.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}