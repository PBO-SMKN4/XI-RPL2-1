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
public class Kehadiran {
    private int jumlahKehadiranPerbulan;
    private int jumlahKehadiranMinggu1;
    private int jumlahKehadiranMinggu2;
    private int jumlahKehadiranMinggu3;
    private int jumlahKehadiranMinggu4;
    private int jumlahSakit;
    private int jumlahIzin;
    private int jumlahAlfa;
    private Connection koneksi;
    private Statement stmt;
    private String sql;
            
    public Kehadiran(String nis){
        try {
            koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM kehadiran WHERE nis = '"+nis+"'";
            ResultSet res = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getJumlahAlfa() {
        return jumlahAlfa;
    }

    public int getJumlahIzin() {
        return jumlahIzin;
    }

    public int getJumlahKehadiranMinggu1() {
        return jumlahKehadiranMinggu1;
    }

    public int getJumlahKehadiranMinggu2() {
        return jumlahKehadiranMinggu2;
    }

    public int getJumlahKehadiranMinggu3() {
        return jumlahKehadiranMinggu3;
    }

    public int getJumlahKehadiranMinggu4() {
        return jumlahKehadiranMinggu4;
    }

    public int getJumlahKehadiranPerbulan() {
        return jumlahKehadiranPerbulan;
    }

    public int getJumlahSakit() {
        return jumlahSakit;
    }
    
    
    
}
