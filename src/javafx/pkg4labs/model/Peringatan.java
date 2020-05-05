/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.pkg4labs.controller.siswa.MyConnection;

/**
 *
 * @author Diazs
 */
public class Peringatan {
    private static String judul;
    private static String isiPeringatan;
    private static String tanggalPeringatan;
    
    public Peringatan(String nis){
        try{
          Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
          
          String sql = "SELECT * FROM peringatan WHERE nis ='"+nis+"'";

          Statement stmt = (Statement) koneksi.createStatement();

          ResultSet res = stmt.executeQuery(sql);
          
          if(res.first()){
              judul = res.getString("judul");
              isiPeringatan = res.getString("isi_peringatan");
              tanggalPeringatan = res.getString("tanggal_peringatan");
          }
          
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public static String getJudul() {
        return judul;
    }

    public static String getIsiPeringatan() {
        return isiPeringatan;
    }

    public static String getTanggalPeringatan() {
        return tanggalPeringatan;
    }
    
}
