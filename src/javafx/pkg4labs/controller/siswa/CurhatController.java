/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.PesanCurhat;
import javafx.pkg4labs.model.RuangCurhat;
import javafx.pkg4labs.model.Siswa;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class CurhatController implements Initializable {

    @FXML
    private ScrollPane scrlPane_chat;
    
    @FXML
    private Label lbl_namaGuru;
    
    @FXML
    private ImageView imgv_fotoGuru;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private TextArea tarea_message;
    
    private Label lbl;
    private AnchorPane box;
    
    private String idRuang = new RuangCurhat(Siswa.getNis(), GuruBK.getNip()).getIdRuang();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    sendMessage();
                }
            }

            private void sendMessage() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        imgv_fotoGuru.setImage(GuruBK.getFoto());
        
        lbl_namaGuru.setText((GuruBK.getJenisKelamin().equalsIgnoreCase("perempuan")?"Bu ":"Pak ")+GuruBK.getNama());
        
        showChat();
        scrlPane_chat.setVvalue(1.0);
        
    }
    
    public void showChat(){
        boolean temp = true;
        VBox ver = null;
        HBox hor = null;
        final TextField masukan = new TextField("Gunakanlah Bahasa Yang Santun");
        masukan.setStyle("-fx-control-inner-background:#FAFAD2; -fx-pref-height:50; -fx-pref-width:283; -fx-max-width:285; -fx-font-family: Times New Roman; -fx-font-size:18; -fx-border-radius: 10%;");
        masukan.setDisable(true);
        
        ver = new VBox();
        ver.setSpacing(10);
        ver.setPadding(new Insets(10, 5, 10, 10));
        
        RuangCurhat curhatan = new RuangCurhat(Siswa.getNis(),GuruBK.getNip());
        
        for (PesanCurhat pesan : curhatan.getPesan()) {
            
            box = new AnchorPane();

            hor = new HBox();
            
            Text isi = new Text(pesan.getIsiPesan());
            Text isiPesan = new Text(isi.getText());
            
            TextFlow textFlowPane = new TextFlow();
            textFlowPane.setTextAlignment(TextAlignment.LEFT);
            textFlowPane.setPrefWidth(300);
            textFlowPane.setPadding(new Insets(10, 0, 10, 10));
            ObservableList list = textFlowPane.getChildren();
            list.addAll(isiPesan);
                                
            lbl = new Label(parseToMinuteHours(pesan.getWaktuKirim()));
            lbl.setLayoutX(300.0);
            lbl.setPadding(new Insets(10, 8, 10, 10));            
            
            box.getChildren().addAll(textFlowPane,lbl);
            box.setPrefHeight(20);
            box.setMaxHeight(box.getMaxHeight());
                
                if (temp) {
                    hor.setPadding(new Insets(10, 10, 40, 470));
                    hor.getChildren().add(masukan);
                    ver.getChildren().add(hor);
                    hor = new HBox();
                    temp = false;
                }
                
                if (pesan.getIdPengirim().equalsIgnoreCase(Siswa.getNis())) {
                    hor.setPadding(new Insets(10, 10, 10, 868));                                        
                    box.setStyle("-fx-background-color:#ffc107; -fx-background-radius: 10;");
                }else{
                    hor.setPadding(new Insets(10, 10, 10, 10));
                    box.setStyle("-fx-background-color:#fff; -fx-background-radius: 10;");
                }
                hor.getChildren().add(box);
            
            ver.getChildren().add(hor);
        }
        
        scrlPane_chat.setContent(ver);
    }
    
    public void sendMessage(){
        String pesan = tarea_message.getText();
        if (pesan.equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Pesan Belum Terisi");
        }else{
            try {
                Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
                Statement stmt = koneksi.createStatement();
                if (checkRuang()) {
                    String sql = "INSERT INTO curhat (isi_chat,id_siswa,id_ruang,waktu_dikirim) VALUES('"+pesan+"','"+Siswa.getNis()+"','"+idRuang+"','"+LocalDate.now()+" "+LocalTime.now()+"')";
                    if (!stmt.execute(sql)) {
                        showChat();
                        tarea_message.setText("");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean checkRuang(){
        return idRuang!=null;
    }
    
    public String parseToMinuteHours(String dateTime){
        String s = dateTime.split("\\s")[1];
        String[] s1 = {s.split(":")[0],":",s.split(":")[1]};
        String result = "";
        for (String s2 : s1) {
            result+=s2;
        }
        return result;
    }

    @FXML
    private void backToMain(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBK.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root)); 
    }
    
}
