/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.time.LocalTime;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Diazs
 */
public class SendEmail
{
    
  public static int validateEmail(String address) throws AddressException, MessagingException{
      String to = address;//change accordingly
      String from = "4Labolatory";//change accordingly
      String host = "smtp.gmail.com";//or IP address
      int random = 0;

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
         random = ran.nextInt(8000) + 1000;
         String kode = String.valueOf(random);
         message.setSubject("Kode verifikasi 4Lab");
         String waktu = Integer.valueOf(LocalTime.now().toString().split(":")[0])<11?"pagi":Integer.valueOf(LocalTime.now().toString().split(":")[0])<18?"sore":"malam";
         message.setText("Halo, selamat "+waktu+" "+address+", terimakasih telah menggunakan 4Lab, ini kode verifikasi anda "+kode+", semoga hari anda menyenangkan bersama kami :) \n\nSalam hangat kami\n-4Lab developer");
         // Send message
         Transport.send(message, "diazsmartiansyah28@gmail.com", "fuckyouhacker");
         System.out.println("message sent successfully....");
      return random;
   }
}

