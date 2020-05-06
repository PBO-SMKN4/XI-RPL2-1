/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */

public class ForgotPassword {
    
    public static void forgotPassword(String address) throws AddressException, MessagingException{
      String to = address;//change accordingly
      String from = "4Labolatory";//change accordingly
      String host = "smtp.gmail.com";//or IP address
      String kode = String.valueOf(new Random().nextInt(8000)+1000);

     //Get the session object
      Properties properties = System.getProperties();
      properties.put("mail.smtp.ssl.enable", "true");
      properties.put("mail.host", host);
      properties.put("mail.smtp.port", "465");
      
      Session session = Session.getDefaultInstance(properties);

     //compose the message
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
         Random ran = new Random();
         message.setSubject("Kode verifikasi 4Lab");
         String waktu = Integer.valueOf(LocalTime.now().toString().split(":")[0])<11?"pagi":Integer.valueOf(LocalTime.now().toString().split(":")[0])<18?"sore":"malam";
         message.setText("Halo, selamat "+waktu+" "+address+", terimakasih telah menggunakan 4Lab, ini kode verifikasi anda "+kode+", semoga hari anda menyenangkan bersama kami :) \n\nSalam hangat kami\n-4Lab developer");
         // Send message
         Transport.send(message, "diazsmartiansyah28@gmail.com", "fuckyouhacker");
         System.out.println("message sent successfully....");
         ForgotPassword.changePassword(kode,address);
   }

   public static boolean changePassword(String kode,String email){
        try {
            String pin = JOptionPane.showInputDialog("Masukan Pin Yang Dikirimkan");
            if (!pin.equals(kode)) {
                JOptionPane.showMessageDialog(null, "Pin Salah");
                return false;
            }
            
            String pass = JOptionPane.showInputDialog("Masukan Password Baru");
            String real_pass;

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes("utf8"));
            
            real_pass = String.format("%040x", new BigInteger(1, digest.digest()));
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            Statement stmt = koneksi.createStatement();
            String sql = "UPDATE students SET password = '"+real_pass+"' WHERE email = '"+email+"'";
            int berhasil = stmt.executeUpdate(sql);
            if (berhasil == 1 ) {
                JOptionPane.showMessageDialog(null, "Berhasil Di Ubah!");
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Di Ubah!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
       
   }
}
