package javafx.pkg4labs.controller.siswa;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Syihab
 */
public class MyConnection {
    
    public static Connection getKoneksi(String host, String port, 

	String username, String password, String db) {

        String konString = "jdbc:mysql://" + host + ":" + port + 

		"/" + db;

        Connection koneksi = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            koneksi = (Connection) DriverManager.getConnection(konString, 

		     username, password);

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(null, "Koneksi Database Error");

            koneksi = null;

        }

        return koneksi;

    }
}
