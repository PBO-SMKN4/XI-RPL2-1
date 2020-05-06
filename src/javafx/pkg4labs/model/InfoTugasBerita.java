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
    
    private String idBerita;
    private String pengirim;
    private String perihal;
    private String penerima;
    private String judul;
    private String isiBerita;
    private String tglUpload;
    private String tglTengat;
    
    
    public InfoTugasBerita(String id) {
        try {
            
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
          
            String sql = "SELECT * FROM berita WHERE id_berita ='"+id+"'";

            Statement stmt = (Statement) koneksi.createStatement();

            ResultSet res = stmt.executeQuery(sql);
            
            if (res.first()) {
                idBerita = res.getString("id_berita");
                pengirim = res.getString("pengirim");
                perihal = res.getString("perihal");
                judul = res.getString("judul");
                penerima = res.getString("penerima");
                isiBerita = res.getString("isi_berita");
                tglUpload = res.getString("tgl_upload");
                tglTengat = res.getString("tgl_kadaluarsa");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getPengirim() {
        return pengirim;
    }
    
    public String getPerihal() {
        return perihal;
    }
    
    public String getJudul() {
        return judul;
    }

    public String getIdBerita() {
        return idBerita;
    }

    public String getIsiBerita() {
        return isiBerita;
    }
    
    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    public void setIdBerita(String idBerita) {
        this.idBerita = idBerita;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String getTglUpload() {
        return tglUpload;
    }

    public void setTglUpload(String tglUpload) {
        this.tglUpload = tglUpload;
    }

    public String getTglTengat() {
        return tglTengat;
    }

    public void setTglTengat(String tglTengat) {
        this.tglTengat = tglTengat;
    }
    
    
}