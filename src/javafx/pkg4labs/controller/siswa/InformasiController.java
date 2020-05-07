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
import java.time.LocalDate;
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
import javafx.pkg4labs.model.InfoTugasBerita;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class InformasiController implements Initializable {

    @FXML
    private ScrollPane scrl_pane;
    
    @FXML
    private TextField inp_query;
    
    @FXML
    private ComboBox cmb_perihal;
    
    private AnchorPane box;
    private ImageView foto;
    private Label labelJudul;
    private Label labelPerihal;
    private Label labelWaktu;
    private Line garis;
    private VBox ver;
    private HBox hor;
    private Hyperlink href;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private String sql;
    private ArrayList<InfoTugasBerita> listBerita;
    private ObservableList<String> listCombo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        showData();
    }
    
    public void showData(){
        try {
            
            ver = new VBox();
            listBerita = new ArrayList<>();
            listCombo = FXCollections.observableArrayList("Tata tertib","Tugas","Berita");
            cmb_perihal.setItems(listCombo);
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM berita WHERE tgl_kadaluarsa > '"+LocalDate.now()+"'";
            listBerita.clear();
            if (cmb_perihal.getValue()!=null) {
                sql+=" AND perihal = '"+String.valueOf(cmb_perihal.getValue())+"'";
            }if(!inp_query.getText().equals("")){
                sql+= " AND (judul LIKE '%"+inp_query.getText()+"%' "
                    + " OR isi_berita LIKE '%"+inp_query.getText()+"%')";
            }
        
            res = stmt.executeQuery(sql);
            while (res.next()) {            
                listBerita.add(new InfoTugasBerita(res.getString("id_berita")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformasiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (InfoTugasBerita berita : listBerita) {
            hor = new HBox();
            box = new AnchorPane();
            foto = new ImageView("file:src\\javafx\\assets\\image\\task.png");
            labelJudul = new Label(berita.getJudul());
            labelPerihal = new Label(berita.getPerihal());
            labelWaktu = new Label(berita.getTglUpload());
            garis = new Line(-43, 0, 940, 0);
            garis.setStrokeWidth(3);
            garis.setStroke(Paint.valueOf("black"));
            garis.setStrokeType(StrokeType.CENTERED);
            href = new Hyperlink("LIHAT DETAIL >>");
            
            //Styling
            labelJudul.setStyle("-fx-font-size:30px;");
            labelPerihal.setStyle("-fx-font-size:16px;");
            labelWaktu.setStyle("-fx-font-size:12px;");
            href.setStyle("-fx-font-size:19px;");
            href.setTextFill(Paint.valueOf("black"));
            box.setPrefSize(1176, 229);
            
            //SetSize
            foto.setFitWidth(109);
            foto.setFitHeight(110);
            
            //Layouting
            foto.setLayoutX(28);
            foto.setLayoutY(58);
            labelJudul.setLayoutX(150);
            labelJudul.setLayoutY(18);
            labelPerihal.setLayoutX(150);
            labelPerihal.setLayoutY(94);
            labelWaktu.setLayoutX(155);
            labelWaktu.setLayoutY(156);
            garis.setLayoutX(180);
            garis.setLayoutY(204);
            href.setLayoutX(990);
            href.setLayoutY(144);
            hor.setPadding(new Insets(20, 0, 20, 40));
            
            //Add StyleSheet / CSS
            box.getStyleClass().add("box");
            box.getStylesheets().add("..\\..\\css\\informasi.css");
            
            
            //Add Listener
            href.setOnMouseClicked((event) -> {
                try {
                    SessionId.setIdToDetailInfo(berita.getIdBerita());
                    detailInformasi(event);
                } catch (IOException ex) {
                    Logger.getLogger(InformasiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            //Add To
            box.getChildren().addAll(foto,labelJudul,labelPerihal,labelWaktu,garis,href);
            hor.getChildren().add(box);
            ver.getChildren().add(hor);
        }
        scrl_pane.setContent(ver);
    }
    
    public void search(){
        if (inp_query==null) {
            JOptionPane.showMessageDialog(null, "Masukan keyword...!","Keyword Kosong",JOptionPane.INFORMATION_MESSAGE);
        }else{
            showData();
        }
    }

    @FXML
    private void backHome(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/SelectInfo.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void detailInformasi(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/DetailInformasi.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}