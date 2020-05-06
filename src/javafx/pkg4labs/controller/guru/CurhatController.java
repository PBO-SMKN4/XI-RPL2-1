/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

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
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.pkg4labs.model.GuruBK;
import javafx.pkg4labs.model.PesanCurhat;
import javafx.pkg4labs.model.RuangCurhat;
import javafx.pkg4labs.model.Students;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private AnchorPane root;
    @FXML
    private ScrollPane scrlPane_chat;
    @FXML
    private Circle profile;
    @FXML
    private Label lbl_namaSiswa;
    @FXML
    private TextArea tarea_message;
    @FXML
    private ImageView imgv_send;
    
    private Label lbl;
    private AnchorPane box;
    private RuangCurhat curhatan ;
    private int jmlBelumDibaca;
    
    private boolean newMessage = false;
    private boolean belumKirim = true;
    private boolean stop = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        prepareImage();
        prepareListener();
        lbl_namaSiswa.setText(new Students(SessionId.getId()).getNama());
              
        if (jmlBelumDibaca>0) {
            newMessage = true;
        }
        showChat();
        scrlPane_chat.setVvalue(1.0);  
    }
    
    public void prepareImage(){
        profile.setEffect(new DropShadow(30, Color.BLACK));
        profile.setFill(new ImagePattern(new Students(SessionId.getId()).getFoto()));
    }
    
    public void prepareListener(){
        tarea_message.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    sendMessage();
                }
            }
        });
    }
    
    @FXML
    private void showChat() {
        curhatan = new RuangCurhat(SessionId.getId(),GuruBK.getNip());
        jmlBelumDibaca = curhatan.getJmlBelumDibaca();
        boolean temp = true;
        int i = 0;
        VBox ver = null;
        HBox hor = null;
        final TextField masukan = new TextField("Berikanlah Siswa Bimbingan Terbaik");
        masukan.setStyle("-fx-control-inner-background:#FAFAD2; -fx-pref-height:50; -fx-pref-width:315; -fx-max-width:330; -fx-font-family: Times New Roman; -fx-font-size:18; -fx-border-radius: 10%;");
        masukan.setDisable(true);
        
        ver = new VBox();
        ver.setSpacing(10);
        ver.setPadding(new Insets(10, 5, 10, 10));

        
        for (PesanCurhat pesan : curhatan.getPesan()) {
            
            box = new AnchorPane();

            hor = new HBox();
            
            Text isi = new Text(pesan.getIsiPesan());
            Text isiPesan = new Text(isi.getText());
            
            TextFlow textFlowPane = new TextFlow();
            if (pesan.getIdPengirim()==GuruBK.getNip()) {
                textFlowPane.setTextAlignment(TextAlignment.RIGHT);
            }else{
                textFlowPane.setTextAlignment(TextAlignment.LEFT);
            }
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
                
                if (newMessage&&belumKirim) {
                    if (i==curhatan.getPesan().size()-jmlBelumDibaca) {
                        isi = new Text(curhatan.getJmlBelumDibaca()+" PESAN BELUM DIBACA ");
                        isi.setFont(Font.font(null, FontWeight.BOLD, 13));
                        textFlowPane = new TextFlow();
                        textFlowPane.setTextAlignment(TextAlignment.CENTER);
                        textFlowPane.setPrefWidth(150);
                        textFlowPane.setPrefHeight(10);
                        textFlowPane.setPadding(new Insets(2, 2, 2, 2));
                        textFlowPane.setStyle("-fx-background-color:#ffffff;");
                        list = textFlowPane.getChildren();
                        list.addAll(isi);
                        hor.setPadding(new Insets(10, 10, 10, 560));
                        hor.getChildren().addAll(textFlowPane);
                        ver.getChildren().add(hor);
                        hor = new HBox();
                    }
                }
                
                if (!pesan.getIdPengirim().equalsIgnoreCase(GuruBK.getNip())) {
                    if (pesan.getDilihat().equalsIgnoreCase("belum")) {
                        pesan.setSudahDilihat();
                    }
                }
                
                
                if (pesan.getIdPengirim().equalsIgnoreCase(GuruBK.getNip())) {
                    hor.setPadding(new Insets(10, 10, 10, 868));                                        
                    box.setStyle("-fx-background-color:#ffc107; -fx-background-radius: 10;");
                }else{
                    hor.setPadding(new Insets(10, 10, 10, 10));
                    box.setStyle("-fx-background-color:#fff; -fx-background-radius: 10;");
                }
                hor.getChildren().add(box);
            
            ver.getChildren().add(hor);
                i++;
        }
        
        scrlPane_chat.setContent(ver);
    }        

    @FXML
    private void sendMessage() {
        String pesan = tarea_message.getText();
        if (pesan.equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Pesan Belum Terisi");
        }else{
            try {
                Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
                Statement stmt = koneksi.createStatement();
                if (checkRuang()) {
                    String sql = "INSERT INTO curhat (isi_chat,id_guru,id_ruang,waktu_dikirim) VALUES('"+pesan+"','"+GuruBK.getNip()+"','"+curhatan.getIdRuang()+"','"+LocalDate.now()+" "+LocalTime.now()+"')";
                    if (!stmt.execute(sql)) {
                        belumKirim = false;
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
        return curhatan!=null;
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
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guru/ListCurhat.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }    
    
}
