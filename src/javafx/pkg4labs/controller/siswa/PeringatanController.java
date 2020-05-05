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
import javafx.pkg4labs.model.RekapKasus;
import javafx.pkg4labs.model.Siswa;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class PeringatanController implements Initializable {
    
    Connection koneksi;
    
    private Label label_isi;
    
    private Label label_judul;
    
    private Label label_tanggal;
    
    @FXML
    private ScrollPane scrl_pane;
    
    private AnchorPane box;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // TODO
            label_judul = new Label();
            label_isi = new Label();
            label_tanggal = new Label();
            showPeringatan();
        } catch (SQLException ex) {
            Logger.getLogger(PeringatanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public void showPeringatan() throws SQLException{
        
        
        VBox ver = null;
        HBox hor = null;
        ver = new VBox();
        int temp = 0;
        
        ArrayList<RekapKasus> ListKasus = new ArrayList<>();
        
         
        koneksi = MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
        String sql = "SELECT * FROM peringatan WHERE nis = '"+Siswa.getNis()+"'";
            
        Statement stmt = koneksi.createStatement();
        ResultSet res = stmt.executeQuery(sql);
            
        while(res.next()){
            RekapKasus rekap = new RekapKasus(res.getString("id_peringatan"));
            ListKasus.add(rekap);
        }
            
        for (RekapKasus rk : ListKasus) {
            hor = new HBox();
            box = new AnchorPane();
            ImageView imgv = new ImageView();
            label_judul = new Label();
            label_isi = new Label();
            label_tanggal = new Label();
            imgv.setImage(new Image("file:src/javafx/assets/image/peringatan.png"));
            imgv.setFitWidth(120);
            imgv.setFitHeight(120);
            imgv.setLayoutX(50);
            imgv.setLayoutY(42);
            label_judul.setLayoutX(219);
            label_judul.setLayoutY(51);
            label_isi.setLayoutX(219);
            label_isi.setLayoutY(87);
            label_tanggal.setLayoutX(219);
            label_tanggal.setLayoutY(130);
            label_judul.setText(rk.getJudul());
            label_isi.setText(rk.getIsiPeringatan());
            label_tanggal.setText(rk.getTanggalPeringatan());
            box.getChildren().addAll(imgv,label_judul,label_isi,label_tanggal); 
            box.setStyle("-fx-background-color: #6221ea; -fx-background-radius:25px;");
            box.setPrefSize(862, 191);
            hor.getChildren().add(box);
            if (temp == 0) {
                hor.setPadding(new Insets(35, 0, 20, 100));
            }else if(temp == ListKasus.size()-1){
                hor.setPadding(new Insets(20, 0, 35, 100));
            }else{
                hor.setPadding(new Insets(20, 0, 20, 100));
            }
            ver.getChildren().add(hor);
            temp++;
        }
        scrl_pane.setContent(ver);
           
    }
    
    
    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}