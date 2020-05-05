/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.pkg4labs.controller.siswa.MyConnection;

/**
 *
 * @author AJAY OK
 */
public class Kehadiran {
    private String nis;
    private String nama;
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
    private ResultSet res;
            
    public Kehadiran(String nis,String bulan,String tahun){
        try {
            koneksi = (Connection) MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            stmt = koneksi.createStatement();
            sql = "SELECT COUNT(minggu.id_minggu) AS jumlah FROM kehadiran JOIN minggu ON kehadiran.id_kehadiran = minggu.id_kehadiran JOIN hari ON minggu.id_minggu = hari.id_minggu WHERE minggu.minggu_ke = 1 AND bulan = '"+bulan+"' AND tahun = "+tahun+" AND nis ='"+nis+"' AND status_kehadiran = 'Hadir'";
            res = stmt.executeQuery(sql);
            if(res.first()){
                jumlahKehadiranMinggu1 = res.getInt("jumlah");
            }
            
            sql = "SELECT COUNT(minggu.id_minggu) AS jumlah FROM kehadiran JOIN minggu ON kehadiran.id_kehadiran = minggu.id_kehadiran JOIN hari ON minggu.id_minggu = hari.id_minggu WHERE minggu.minggu_ke = 2 AND bulan = '"+bulan+"' AND tahun = '"+tahun+"' AND nis ='"+nis+"' AND status_kehadiran = 'Hadir'";
            stmt = koneksi.createStatement();
            res = stmt.executeQuery(sql);
            if (res.first()) {
                jumlahKehadiranMinggu2 = res.getInt("jumlah");
            }
            
            sql = "SELECT COUNT(minggu.id_minggu) AS jumlah FROM kehadiran JOIN minggu ON kehadiran.id_kehadiran = minggu.id_kehadiran JOIN hari ON minggu.id_minggu = hari.id_minggu WHERE minggu.minggu_ke = 3 AND bulan = '"+bulan+"' AND tahun = '"+tahun+"' AND nis ='"+nis+"' AND status_kehadiran = 'Hadir'";
            stmt = koneksi.createStatement();
            res = stmt.executeQuery(sql);
            if (res.first()) {
                jumlahKehadiranMinggu3 = res.getInt("jumlah");
            }
            
            sql = "SELECT COUNT(minggu.id_minggu) AS jumlah FROM kehadiran JOIN minggu ON kehadiran.id_kehadiran = minggu.id_kehadiran JOIN hari ON minggu.id_minggu = hari.id_minggu WHERE minggu.minggu_ke = 4 AND bulan = '"+bulan+"' AND tahun = '"+tahun+"' AND nis ='"+nis+"' AND status_kehadiran = 'Hadir'";
            stmt = koneksi.createStatement();
            res = stmt.executeQuery(sql);
            if (res.first()) {
                jumlahKehadiranMinggu4 = res.getInt("jumlah");
            }
            
            sql = "SELECT COUNT(id_hari) AS jumlah FROM kehadiran JOIN minggu ON kehadiran.id_kehadiran = minggu.id_kehadiran JOIN hari ON minggu.id_minggu = hari.id_minggu WHERE status_kehadiran = 'Sakit' AND bulan = '"+bulan+"' AND tahun = '"+tahun+"' AND nis ='"+nis+"'";
            stmt = koneksi.createStatement();
            res = stmt.executeQuery(sql);
            if (res.first()) {
                jumlahSakit = res.getInt("jumlah");
            }
            
            sql = "SELECT COUNT(id_hari) AS jumlah FROM kehadiran JOIN minggu ON kehadiran.id_kehadiran = minggu.id_kehadiran JOIN hari ON minggu.id_minggu = hari.id_minggu WHERE status_kehadiran = 'Izin' AND bulan = '"+bulan+"' AND tahun = '"+tahun+"' AND nis ='"+nis+"'";
            stmt = koneksi.createStatement();
            res = stmt.executeQuery(sql);
            if (res.first()) {
                jumlahIzin = res.getInt("jumlah");
            }
            
            sql = "SELECT COUNT(id_hari) AS jumlah FROM kehadiran JOIN minggu ON kehadiran.id_kehadiran = minggu.id_kehadiran JOIN hari ON minggu.id_minggu = hari.id_minggu WHERE status_kehadiran = 'Alfa' AND bulan = '"+bulan+"' AND tahun = '"+tahun+"' AND nis ='"+nis+"'";
            stmt = koneksi.createStatement();
            res = stmt.executeQuery(sql);
            if (res.first()) {
                jumlahAlfa = res.getInt("jumlah");
            }
            
            sql = "SELECT COUNT(id_hari) AS jumlah FROM kehadiran JOIN minggu ON kehadiran.id_kehadiran = minggu.id_kehadiran JOIN hari ON minggu.id_minggu = hari.id_minggu WHERE bulan = '"+bulan+"' AND tahun = '"+tahun+"' AND nis ='"+nis+"' AND status_kehadiran = 'Hadir'";
            stmt = koneksi.createStatement();
            res = stmt.executeQuery(sql);
            if (res.first()) {
                jumlahKehadiranPerbulan = res.getInt("jumlah");
            }
            
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public void setNis(String nis) {
        this.nis = nis;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNis(){
        return nis;
    }
    
    public String getNama(){
        return nama;
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