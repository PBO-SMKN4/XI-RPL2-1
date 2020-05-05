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
 * @author Muhammad Fahru Rozi
 */
public class InfoTugasBerita {
    
    private static String pengirim;
    private static String perihal;
    private static String judul;
    private static String isi_berita;
    private static String tgl_tengat;
    
    public InfoTugasBerita(String penerima) {
        try {
            
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
          
            String sql = "SELECT * FROM berita WHERE penerima ='"+penerima+"'";

            Statement stmt = (Statement) koneksi.createStatement();

            ResultSet res = stmt.executeQuery(sql);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static String getPengirim() {
        return pengirim;
    }
    
    public static String getPerihal() {
        return perihal;
    }
    
    public static String getJudul() {
        return judul;
    }
    
    public static String getIsiBerita() {
        return isi_berita;
    }
    
    public static String getTglTengat() {
        return tgl_tengat;
    }
    
    
}
