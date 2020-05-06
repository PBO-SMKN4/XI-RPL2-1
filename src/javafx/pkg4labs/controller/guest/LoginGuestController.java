/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.pkg4labs.controller.guru.SessionId;
import javafx.pkg4labs.model.SendEmail;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Muhammad Fahru Rozi
 */
public class LoginGuestController implements Initializable {

    @FXML
    private TextField email;
    
    @FXML
    private TextField pin;
    
    @FXML
    private Label lbl_judul, lbl_false, lbl_wait;
    
    @FXML
    private Button btn_control;
    
    String validate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        email.setFocusTraversable(false);
        SessionId.setId(email.getText());
        email.setOnKeyPressed((event) -> {
            try {
                if (event.getCode()==KeyCode.ENTER) {
                    btnControl(event);
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginGuestController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        pin.setOnKeyPressed((event) -> {
            try {
                if (event.getCode()==KeyCode.ENTER) {
                    validate(event);
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginGuestController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void setElement(boolean value){
            lbl_judul.setText(value == true?"Your Email":"Verify Your Email");
            lbl_false.setVisible(!value);
            btn_control.setText(value == true?"Sign In":"Submit");
            email.setVisible(value);
            pin.setVisible(!value);
    }
    
    public void btnControl(Event event) throws IOException {
        if (btn_control.getText().equalsIgnoreCase("sign in")) {
            sendEmail(event);
        }else{
            validate(event);
        }
    }
    
    public void sendEmail(Event event) throws IOException {
        if (!email.getText().equals("")) {
            lbl_wait.setText("Please wait. . .");
            lbl_wait.setTextFill(Paint.valueOf("#00ff51"));
            lbl_wait.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> {
                try {
                    validate = String.valueOf(SendEmail.validateEmail(email.getText()));
                    lbl_wait.setVisible(false);
                    showLayer2();
                } catch (AddressException e) {
                    lbl_wait.setTextFill(Paint.valueOf("red"));
                    lbl_wait.setText("Enter Valid Email !");
                    e.printStackTrace();
                }catch(MessagingException e){
                    lbl_wait.setTextFill(Paint.valueOf("red"));
                    lbl_wait.setText("Please Check Your Connection");
                    e.printStackTrace();
                }
            }));
        timeline.play();
            
        }else{
            JOptionPane.showMessageDialog(null, "Input Your Email", "Invalid", 0);
        }
    }

    public void validate(Event event) throws IOException {
        if (pin.getText().equals(validate)) {
            SessionGuest.setEmail(email.getText());
            login(event);
        }else{
            JOptionPane.showMessageDialog(null, "Incorrect Pin!", "Access Denied", 0);
        }
    }
    
    public void showLayer1(){
        setElement(true);
    }
    
    public void showLayer2(){
        setElement(false);
    }
    
    @FXML
    private void back(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/Role.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }

    @FXML
    private void login(Event event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/pkg4labs/view/guest/HalamanUtama.fxml"));
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();        
        stage.setScene(new Scene(root));
    }
    
}
