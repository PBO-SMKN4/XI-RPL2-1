/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.pow;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class RumusController implements Initializable {

    @FXML
    private ComboBox<String> cmb_select2;
    @FXML
    private TextField text_field1;
    @FXML
    private Label label1;
    @FXML
    private TextField text_field2;
    @FXML
    private Label label2;
    @FXML
    private TextField text_field3;
    @FXML
    private Label label3;
    @FXML
    private ComboBox<String> cmb_select1;
    @FXML
    private Button btn_hasil;
    @FXML
    private Button btn_clear;
    @FXML
    private TextField text_field_hasil;
    @FXML
    private ImageView img_rumus;
    ObservableList<String> list1 = FXCollections.observableArrayList("Persegi","Persegi Panjang","Segitiga","Jajargenjang","Belah Ketupat","Layang Layang","Trapesium","Lingkaran");
    ObservableList<String> list2 = FXCollections.observableArrayList("Keliling","Luas");
    @FXML
    private TextField text_field4;
    @FXML
    private Label label4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        cmb_select1.setItems(list1);
        cmb_select2.setItems(list2);
    }   

    public void Keliling() {
        try {
            String filter = cmb_select1.getValue();
            switch(filter){

                case "Persegi" :
                    double sisi = Double.parseDouble(text_field1.getText().trim());
                    double keliling1 = sisi*4;
                    String hasil1 = Double.toString(keliling1);
                    text_field_hasil.setText(hasil1);
                    text_field_hasil.setEditable(false);
                break;

                case "Persegi Panjang" :
                    double panjang = Double.parseDouble(text_field1.getText().trim());
                    double lebar = Double.parseDouble(text_field2.getText().trim());
                    double keliling2 = 2*(panjang+lebar);
                    String hasil2 = Double.toString(keliling2);
                    text_field_hasil.setText(hasil2);
                    text_field_hasil.setEditable(false);
                break;

                case "Segitiga" :
                    double aS = Double.parseDouble(text_field1.getText().trim());
                    double bS = Double.parseDouble(text_field2.getText().trim());
                    double cS = Double.parseDouble(text_field3.getText().trim());
                    double keliling3 = aS+bS+cS;
                    String hasil3 = Double.toString(keliling3);
                    text_field_hasil.setText(hasil3);
                    text_field_hasil.setEditable(false);                
                break;

                case "Jajargenjang" :            
                case "Belah Ketupat" :            
                case "Layang Layang" :                            
                case "Trapesium" :
                    double a = Double.parseDouble(text_field1.getText().trim());
                    double b = Double.parseDouble(text_field2.getText().trim());
                    double c = Double.parseDouble(text_field3.getText().trim());
                    double d = Double.parseDouble(text_field4.getText().trim());
                    double keliling4 = a+b+c+d;
                    String hasil4 = Double.toString(keliling4);
                    text_field_hasil.setText(hasil4);
                    text_field_hasil.setEditable(false);
                break;

                case "Lingkaran" :
                    double r = Double.parseDouble(text_field1.getText().trim());
                    double keliling5 = 0;
                    if (r%7 == 0) {
                     keliling5 = 2*22*r/7;
                    } else {
                     keliling5 = 2*3.14*r;
                    } 
                    String hasil5 = Double.toString(keliling5);
                    text_field_hasil.setText(hasil5);
                    text_field_hasil.setEditable(false);
                break;

            } 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Masukan Angka dan Angka Tidak Boleh Kosong ", " Error ", JOptionPane.ERROR_MESSAGE);
        }
                       
    }
    
    public void Luas() {
        try {
            String filter = cmb_select1.getValue();
            switch(filter){

                case "Persegi" :
                    double sisi = Double.parseDouble(text_field1.getText().trim());
                    double luas1 = pow(sisi,3);
                    String hasil1 = Double.toString(luas1);
                    text_field_hasil.setText(hasil1);
                    text_field_hasil.setEditable(false);
                break;

                case "Persegi Panjang" :
                    double panjang = Double.parseDouble(text_field1.getText().trim());
                    double lebar = Double.parseDouble(text_field2.getText().trim());
                    double luas2 = panjang*lebar;
                    String hasil2 = Double.toString(luas2);
                    text_field_hasil.setText(hasil2);
                    text_field_hasil.setEditable(false);
                break;

                case "Segitiga" :
                case "Belah Ketupat" :
                case "Layang Layang" :
                    double label1 = Double.parseDouble(text_field1.getText().trim());
                    double label2 = Double.parseDouble(text_field2.getText().trim());
                    double luas3 = label1*label2*0.5;
                    String hasil3 = Double.toString(luas3);
                    text_field_hasil.setText(hasil3);
                    text_field_hasil.setEditable(false); 
                break;

                case "Jajargenjang" :
                    double alas = Double.parseDouble(text_field1.getText().trim());
                    double tinggi = Double.parseDouble(text_field2.getText().trim());
                    double luas4 = alas*tinggi;
                    String hasil4 = Double.toString(luas4);
                    text_field_hasil.setText(hasil4);
                    text_field_hasil.setEditable(false); 
                break;                               

                case "Trapesium" :
                    double a = Double.parseDouble(text_field1.getText().trim());
                    double b = Double.parseDouble(text_field2.getText().trim());
                    double t = Double.parseDouble(text_field3.getText().trim());
                    double luas5 = ((a+b)/2)*t;
                    String hasil5 = Double.toString(luas5);
                    text_field_hasil.setText(hasil5);
                    text_field_hasil.setEditable(false);
                break;

                case "Lingkaran" :
                    double r = Double.parseDouble(text_field1.getText().trim());
                    double luas6 = 0;
                    if (r%7 == 0) {
                     luas6 = 22*r/7*r;
                    } else {
                     luas6 = 3.14*r*r;
                    } 
                    String hasil6 = Double.toString(luas6);
                    text_field_hasil.setText(hasil6);
                    text_field_hasil.setEditable(false);
                break;

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Masukan Angka dan Angka Tidak Boleh Kosong ", " Error ", JOptionPane.ERROR_MESSAGE);
        }        
        
    }

    @FXML
    private void backToSelect(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/siswa/RuangBelajar.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void Hasil(MouseEvent event) {
        String rumus = cmb_select2.getValue();
        if (rumus == "Keliling") {
            Keliling();
        } else if (rumus == "Luas") {
            Luas();
        }
    }

    @FXML
    private void Clear(MouseEvent event) {        
        cmb_select1.setValue("Pilih Bangun Datar");
        cmb_select2.setValue("Pilih Rumus");
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        text_field1.setText("");
        text_field2.setText("");
        text_field3.setText("");
        text_field4.setText("");
        text_field1.setDisable(true);
        text_field2.setDisable(true);
        text_field3.setDisable(true);
        text_field4.setDisable(true);
        text_field_hasil.setText("");
        Image image = new Image ("file:src/javafx/assets/image/Screenshot-2018-12-16-at-21.06.29.png");
        img_rumus.setImage(image);
    }

    @FXML
    private void ApplyFilter(MouseEvent event) {
        String filter1 = cmb_select1.getValue();
        String filter2 = cmb_select2.getValue();
        switch(filter1){
            case "Persegi" :
                label1.setText("Sisi");
                label2.setText("");
                label3.setText("");
                label4.setText("");
                text_field1.setDisable(false);
                text_field2.setDisable(true);
                text_field3.setDisable(true);
                text_field4.setDisable(true);
                Image image1 = new Image ("file:src/javafx/assets/rumus/persegi.png");
                img_rumus.setImage(image1);
            break;
            
            case "Persegi Panjang" :
                label1.setText("Panjang");
                label2.setText("Lebar");
                label3.setText("");
                label4.setText("");
                text_field1.setDisable(false);
                text_field2.setDisable(false);
                text_field3.setDisable(true);
                text_field4.setDisable(true);
                Image image2 = new Image ("file:src/javafx/assets/rumus/persegipanjang.png");
                img_rumus.setImage(image2);
            break;
            
            case "Segitiga" :
                label1.setText("Panjang a");
                label2.setText("Panjang b");
                label3.setText("Panjang c");
                label4.setText("");
                text_field1.setDisable(false);
                text_field2.setDisable(false);
                text_field3.setDisable(false);
                text_field4.setDisable(true);
                Image image3 = new Image ("file:src/javafx/assets/rumus/segitiga.png");
                img_rumus.setImage(image3);
                if(filter2 == "Luas") {
                    label1.setText("Alas");
                    label2.setText("Tinggi");
                    label3.setText("");
                    label4.setText("");
                    text_field1.setDisable(false);
                    text_field2.setDisable(false);
                    text_field3.setDisable(true);
                    text_field4.setDisable(true);
                }
            break;
            
            case "Jajargenjang" :
                label1.setText("Panjang a");
                label2.setText("Panjang b");
                label3.setText("Panjang c");
                label4.setText("Panjang d");
                text_field1.setDisable(false);
                text_field2.setDisable(false);
                text_field3.setDisable(false);
                text_field4.setDisable(false);
                Image image4 = new Image ("file:src/javafx/assets/rumus/jajargenjang.png");
                img_rumus.setImage(image4);
                if(filter2 == "Luas") {
                    label1.setText("Alas");
                    label2.setText("Tinggi");
                    label3.setText("");
                    label4.setText("");
                    text_field1.setDisable(false);
                    text_field2.setDisable(false);
                    text_field3.setDisable(true);
                    text_field4.setDisable(true);
                }
            break;
            
            case "Belah Ketupat" :
                label1.setText("Panjang a");
                label2.setText("Panjang b");
                label3.setText("Panjang c");
                label4.setText("Panjang d");
                text_field1.setDisable(false);
                text_field2.setDisable(false);
                text_field3.setDisable(false);
                text_field4.setDisable(false);
                Image image5 = new Image ("file:src/javafx/assets/rumus/belahketupat.png");
                img_rumus.setImage(image5);
                if(filter2 == "Luas") {
                    label1.setText("diagonal 1");
                    label2.setText("diagonal 2");
                    label3.setText("");
                    label4.setText("");
                    text_field1.setDisable(false);
                    text_field2.setDisable(false);
                    text_field3.setDisable(true);
                    text_field4.setDisable(true);
                }
            break;
            
            case "Layang Layang" :
                label1.setText("Panjang a");
                label2.setText("Panjang b");
                label3.setText("Panjang c");
                label4.setText("Panjang d");
                text_field1.setDisable(false);
                text_field2.setDisable(false);
                text_field3.setDisable(false);
                text_field4.setDisable(false);
                Image image6 = new Image ("file:src/javafx/assets/rumus/layang.png");
                img_rumus.setImage(image6);
                if(filter2 == "Luas") {
                    label1.setText("diagonal 1");
                    label2.setText("diagonal 2");
                    label3.setText("");
                    label4.setText("");
                    text_field1.setDisable(false);
                    text_field2.setDisable(false);
                    text_field3.setDisable(true);
                    text_field4.setDisable(true);
                }
            break;
            
            case "Trapesium" :
                label1.setText("Panjang a");
                label2.setText("Panjang b");
                label3.setText("Panjang c");
                label4.setText("Panjang d");
                text_field1.setDisable(false);
                text_field2.setDisable(false);
                text_field3.setDisable(false);
                text_field4.setDisable(false);
                Image image7 = new Image ("file:src/javafx/assets/rumus/trapesium.png");
                img_rumus.setImage(image7);
                if(filter2 == "Luas") {
                    label1.setText("Panjang a");
                    label2.setText("Panjang b");
                    label3.setText("Tinggi");
                    label4.setText("");
                    text_field1.setDisable(false);
                    text_field2.setDisable(false);
                    text_field3.setDisable(false);
                    text_field4.setDisable(true);
                }
            break;
            
            case "Lingkaran" :
                label1.setText("Panjang r");
                label2.setText("");
                label3.setText("");
                label4.setText("");
                text_field1.setDisable(false);
                text_field2.setDisable(true);
                text_field3.setDisable(true);
                text_field4.setDisable(true);
                Image image8 = new Image ("file:src/javafx/assets/rumus/lingkaran.png");
                img_rumus.setImage(image8);
            break;
            
        }
    }

    @FXML
    private void Conflict(ActionEvent event) {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        text_field1.setText("");
        text_field2.setText("");
        text_field3.setText("");
        text_field4.setText("");
        text_field1.setDisable(true);
        text_field2.setDisable(true);
        text_field3.setDisable(true);
        text_field4.setDisable(true);
        text_field_hasil.setText("");
        Image image = new Image ("file:src/javafx/assets/image/Screenshot-2018-12-16-at-21.06.29.png");
        img_rumus.setImage(image);
    }

    
}
