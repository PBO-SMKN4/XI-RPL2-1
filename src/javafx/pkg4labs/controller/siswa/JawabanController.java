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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.pkg4labs.model.Jawaban;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Dean Beniqno A
 */
public class JawabanController implements Initializable {
    
    @FXML
    private ScrollPane scrl_Pane;
    
    private HBox hor;
    private VBox ver;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private String sql;
    private ArrayList<Jawaban> listJawaban = new ArrayList<>();
    private AnchorPane box;
    
    private Label label_penjawab;
    private TextField txt_jawaban;
    private Hyperlink href;
    private Rating rating;
    private ArrayList<Integer> id;
    private boolean asc = true;
    private boolean desc = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        try {
            stmt = koneksi.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PertanyaanSayaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        showData();
    }

    public void showData(){
        
        try {
            
            //Declaration & Query
            ver = new VBox();
            sql = "SELECT * FROM jawaban";
            
            //Filter
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM jawaban WHERE id_pertanyaan = '"+SessionId.getIdToPertanyaan()+"'";
            if (asc) {
                sql+=" ORDER BY rating asc";
            }else if (desc) {
                sql+=" ORDER BY rating desc";
            }
            res = stmt.executeQuery(sql);
            
            //Clear List Data From Array List
            listJawaban.clear();
            while(res.next()){
                Jawaban jawab = new Jawaban(res.getString("id_jawaban"));
                listJawaban.add(jawab);
            }
            
            for(Jawaban jawaban : listJawaban){
                
                Label vote = new Label(jawaban.getVote()+" Vote(s)");
                hor = new HBox();
                box = new AnchorPane();
                ImageView imgv = new ImageView();
                href = new Hyperlink();
                label_penjawab = new Label();
                txt_jawaban = new TextField();
                rating = new Rating();
                rating.setRating(jawaban.getRating());
                
                imgv.setImage(jawaban.getFotoPenjawab());
                imgv.setFitWidth(50);
                imgv.setFitHeight(50);
                imgv.setLayoutX(27);
                imgv.setLayoutY(17);
                
                label_penjawab.setLayoutX(97);
                label_penjawab.setLayoutY(29);
                
                txt_jawaban.setLayoutX(99);
                txt_jawaban.setLayoutY(77);
                txt_jawaban.setPrefSize(1026,304);
                txt_jawaban.setEditable(false);
                
                href.setLayoutX(99);
                href.setLayoutY(404);
                
                rating.setLayoutX(102);
                rating.setLayoutY(448);
                
                vote.setLayoutX(278);
                vote.setLayoutY(463);
                
                label_penjawab.setText(jawaban.getNamaPenjawab());
                txt_jawaban.setText(jawaban.getIsiJawaban());
                href.setText("Lihat Gambar");
                
                label_penjawab.setStyle("-fx-text-fill:black; -fx-font-size:18;");
                txt_jawaban.setStyle("-fx-text-fill:black;");
                href.setStyle("-fx-text-fill:black; -fx-font-size:12;");
                rating.setStyle("");
                
                box.getChildren().addAll(imgv,label_penjawab,txt_jawaban,href,rating,vote);
                box.setStyle("-fx-background-radius:20; -fx-background-color:white;");
                box.setPrefSize(1170, 506);
                hor.getChildren().add(box);
                hor.setPadding(new Insets(40, 40, 40, 40));
                ver.getChildren().add(hor);
                href.setOnMouseClicked((event) -> {
                    new ShowImages("src/QnA/"+jawaban.getFileFoto()+".jpg");
                });
                if (jawaban.getFoto()==null) {
                    href.setVisible(false);
                }
                
                if (jawaban.alreadyVote(Siswa.getNis())) {
                    rating.setDisable(true);
                }
                
                rating.setOnMouseClicked((event) -> {
                    updateRating(jawaban,rating);
                    rating.setDisable(true);
                });
            }
            scrl_Pane.setContent(ver);
        } catch (SQLException ex) {
            Logger.getLogger(JawabanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void controlAsc(){
        if (!asc) {
            asc = true;
            desc = false;
            showData();
        }
    }
    public void controlDesc(){
        if (!desc) {
            asc = false;
            desc = true;
            showData();
        }
    }
    
    public void setDisable(Node node){
        node.setDisable(true);
    }
    
    public void updateRating(Jawaban jawab,Rating rating){
        try {
            System.out.println("Rating : "+rating.getRating());
            float ratingBaru = (float) (jawab.getRating()!=0?((jawab.getRating()+rating.getRating())/2):rating.getRating());
            stmt = koneksi.createStatement();
            sql = "UPDATE jawaban SET rating = "+ratingBaru+" WHERE id_jawaban = '"+jawab.getIdJawaban()+"'";
            int berhasil = stmt.executeUpdate(sql);
            if (berhasil == 1) {
                System.out.println("Berhasil!");
            }else{
                System.out.println("Gagal!");
            }
            stmt = koneksi.createStatement();
            sql = "INSERT INTO voter(nis,id_jawaban) VALUES('"+Siswa.getNis()+"','"+jawab.getIdJawaban()+"')";
            berhasil = stmt.executeUpdate(sql);
            if (berhasil == 1) {
                System.out.println("Berhasil!");
            }else{
                System.out.println("Gagal!");
            }
            showData();
        } catch (SQLException ex) {
            Logger.getLogger(JawabanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backToSelect(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/DetailPertanyaan.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}