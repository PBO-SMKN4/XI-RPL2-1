/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.pkg4labs.controller.siswa.MyConnection;

/**
 *
 * @author Diazs
 */
public class Peringatan {
    private String judul;
    private String isiPeringatan;
    private String tanggalPeringatan;
    
    public Peringatan(String nis){
        try{
          Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
          
          String sql = "SELECT * FROM peringatan WHERE nis ='"+nis+"'";

          Statement stmt = (Statement) koneksi.createStatement();

          ResultSet res = stmt.executeQuery(sql);
          
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
