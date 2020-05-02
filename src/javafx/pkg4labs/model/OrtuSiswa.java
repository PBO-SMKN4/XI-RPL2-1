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
public class OrtuSiswa {
    private String idWali;
    private String namaWali = null;
    private String jenKel;
    private String golDar;
    private String tglLahir;
    private String noHP = null;
    private String alamat;
    private String hubungan;
    private String agama;
    
    public OrtuSiswa(String id){
        try {
            Connection koneksi =  MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
            Statement stmt = koneksi.createStatement();
            String sql = "SELECT * FROM wali WHERE nik = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.first()){
                idWali = rs.getString("nik");
                namaWali = rs.getString("nama");
                jenKel = rs.getString("jk");
                golDar = rs.getString("goldar");
                tglLahir = rs.getString("tgl_lahir");
                noHP = rs.getString("no_hp");
                hubungan = rs.getString("hubungan");
                agama = rs.getString("agama");
                alamat = rs.getString("alamat");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIdWali() {
        return idWali;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getGolDar() {
        return golDar;
    }

    public String getHubungan() {
        return hubungan;
    }

    public String getJenKel() {
        return jenKel;
    }

    public String getNamaWali() {
        return namaWali;
    }

    public String getNoHP() {
        return noHP;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public String getAgama() {
        return agama;
    }
    
}