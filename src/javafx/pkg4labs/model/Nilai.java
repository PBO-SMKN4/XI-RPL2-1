/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.pkg4labs.controller.siswa.MyConnection;

/**
 *
 * @author Diazs
 */
public class Nilai{
    private String nama;
    private String nis;
    private float nilaiMtk;
    private float nilaiIndo;
    private float nilaiPAI;
    private float nilaiPPKN;
    private float nilaiInggris;
    private float nilaiSunda;
    private float nilaiOR;
    private float nilaiProduktif1;
    private float nilaiProduktif2;
    private float nilaiProduktif3;
    private float nilaiProduktif4;
    private float nilaiProduktif5;
    private float nilaiProduktif6;
    private float nilaiLainnya1l;
    private float nilaiLainnya2;
    private float nilaiLainnya3;
    private String matpelProduktif1 = "Lainnya";
    private String matpelProduktif2 = "Lainnya";
    private String matpelProduktif3 = "Lainnya";
    private String matpelProduktif4 = "Lainnya";
    private String matpelProduktif5 = "Lainnya";
    private String matpelLainnya1 = "Lainnya";
    private String matpelLainnya2 = "Lainnya";
    private String matpelLainnya3 = "Lainnya";
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    
    public Nilai(String nis,String semester,String tahunAjaran){
        try {
            Students siswa = new Students(nis);
            nama = siswa.getNama();
            this.nis = siswa.getNis();
            koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            stmt = koneksi.createStatement();
            String sql = "SELECT students.nama AS nama,predikat.angka AS nilai FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND mapel = 'Inggris' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
                
            if(res.first()){
                nilaiInggris = res.getFloat("nilai");
                nama = res.getString("nama");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND mapel = 'INDO' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiIndo = res.getFloat("nilai");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND mapel = 'MTK' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiMtk = res.getFloat("nilai");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND mapel = 'PAI' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiPAI = res.getFloat("nilai");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND mapel = 'PPKN' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiPPKN = res.getFloat("nilai");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND mapel = 'PJOK' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiOR = res.getFloat("nilai");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND mapel = 'Sunda' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiSunda = res.getFloat("nilai");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai,mapel.nama_mapel AS nama FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN mapel ON mapel.mapel = nilai_mapel.mapel "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND kategori = 'produktif1' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiProduktif1 = res.getFloat("nilai");
                matpelProduktif1 = res.getString("nama");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai,mapel.nama_mapel AS nama FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN mapel ON mapel.mapel = nilai_mapel.mapel "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND kategori = 'produktif2' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiProduktif2 = res.getFloat("nilai");
                matpelProduktif2 = res.getString("nama");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai,mapel.nama_mapel AS nama FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    +  "JOIN mapel ON mapel.mapel = nilai_mapel.mapel "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND kategori = 'produktif3' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiProduktif3 = res.getFloat("nilai");
                matpelProduktif3 = res.getString("nama");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai,mapel.nama_mapel AS nama FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN mapel ON mapel.mapel = nilai_mapel.mapel "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND kategori = 'produktif4' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiProduktif4 = res.getFloat("nilai");
                matpelProduktif4 = res.getString("nama");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai,mapel.nama_mapel AS nama FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN mapel ON mapel.mapel = nilai_mapel.mapel "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND kategori = 'produktif5' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiProduktif5 = res.getFloat("nilai");
                matpelProduktif5 = res.getString("nama");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai,mapel.nama_mapel AS nama FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN mapel ON mapel.mapel = nilai_mapel.mapel "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND kategori = 'lainnya1' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiLainnya1l = res.getFloat("nilai");
                matpelLainnya1 = res.getString("nama");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai,mapel.nama_mapel AS nama FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN mapel ON mapel.mapel = nilai_mapel.mapel "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND kategori = 'lainnya2' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiLainnya2 = res.getFloat("nilai");
                matpelLainnya2 = res.getString("nama");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT predikat.angka AS nilai,mapel.nama_mapel AS nama FROM predikat JOIN nilai_mapel "
                    + "ON predikat.id_nilai = nilai_mapel.id_nilai "
                    + "JOIN mapel ON mapel.mapel = nilai_mapel.mapel "
                    + "JOIN students ON nilai_mapel.nis = students.nis "
                    + "JOIN semester ON semester.id_semester = nilai_mapel.id_semester "
                    + "WHERE students.nis = '"+nis+"'AND semester = '"+semester+"' AND kategori = 'lainnya3' AND thn_ajaran ='"+tahunAjaran+"'";
            res = stmt.executeQuery(sql);
            
            if(res.first()){
                nilaiLainnya3 = res.getFloat("nilai");
                matpelLainnya3 = res.getString("nama");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }

    public float getNilaiMtk() {
        return nilaiMtk;
    }

    public void setNilaiMtk(float nilaiMtk) {
        this.nilaiMtk = nilaiMtk;
    }

    public float getNilaiIndo() {
        return nilaiIndo;
    }

    public void setNilaiIndo(float nilaiIndo) {
        this.nilaiIndo = nilaiIndo;
    }

    public float getNilaiPAI() {
        return nilaiPAI;
    }

    public void setNilaiPAI(float nilaiPAI) {
        this.nilaiPAI = nilaiPAI;
    }

    public float getNilaiPPKN() {
        return nilaiPPKN;
    }

    public void setNilaiPPKN(float nilaiPPKN) {
        this.nilaiPPKN = nilaiPPKN;
    }

    public float getNilaiInggris() {
        return nilaiInggris;
    }

    public void setNilaiInggris(float nilaiInggris) {
        this.nilaiInggris = nilaiInggris;
    }

    public float getNilaiSunda() {
        return nilaiSunda;
    }

    public void setNilaiSunda(float nilaiSunda) {
        this.nilaiSunda = nilaiSunda;
    }

    public float getNilaiOR() {
        return nilaiOR;
    }

    public void setNilaiOR(float nilaiOR) {
        this.nilaiOR = nilaiOR;
    }

    public float getNilaiProduktif1() {
        return nilaiProduktif1;
    }

    public void setNilaiProduktif1(float nilaiProduktif1) {
        this.nilaiProduktif1 = nilaiProduktif1;
    }

    public float getNilaiProduktif2() {
        return nilaiProduktif2;
    }

    public void setNilaiProduktif2(float nilaiProduktif2) {
        this.nilaiProduktif2 = nilaiProduktif2;
    }

    public float getNilaiProduktif3() {
        return nilaiProduktif3;
    }

    public void setNilaiProduktif3(float nilaiProduktif3) {
        this.nilaiProduktif3 = nilaiProduktif3;
    }

    public float getNilaiProduktif4() {
        return nilaiProduktif4;
    }

    public void setNilaiProduktif4(float nilaiProduktif4) {
        this.nilaiProduktif4 = nilaiProduktif4;
    }

    public float getNilaiProduktif5() {
        return nilaiProduktif5;
    }

    public void setNilaiProduktif5(float nilaiProduktif5) {
        this.nilaiProduktif5 = nilaiProduktif5;
    }

    public float getNilaiProduktif6() {
        return nilaiProduktif6;
    }

    public void setNilaiProduktif6(float nilaiProduktif6) {
        this.nilaiProduktif6 = nilaiProduktif6;
    }

    public String getMatpelProduktif1() {
        return matpelProduktif1;
    }

    public void setMatpelProduktif1(String matpelProduktif1) {
        this.matpelProduktif1 = matpelProduktif1;
    }

    public String getMatpelProduktif2() {
        return matpelProduktif2;
    }

    public void setMatpelProduktif2(String matpelProduktif2) {
        this.matpelProduktif2 = matpelProduktif2;
    }

    public String getMatpelProduktif3() {
        return matpelProduktif3;
    }

    public void setMatpelProduktif3(String matpelProduktif3) {
        this.matpelProduktif3 = matpelProduktif3;
    }

    public String getMatpelProduktif4() {
        return matpelProduktif4;
    }

    public void setMatpelProduktif4(String matpelProduktif4) {
        this.matpelProduktif4 = matpelProduktif4;
    }

    public String getMatpelProduktif5() {
        return matpelProduktif5;
    }

    public void setMatpelProduktif5(String matpelProduktif5) {
        this.matpelProduktif5 = matpelProduktif5;
    }

    public float getNilaiLainnya1l() {
        return nilaiLainnya1l;
    }

    public void setNilaiLainnya1l(float nilaiLainnya1l) {
        this.nilaiLainnya1l = nilaiLainnya1l;
    }

    public float getNilaiLainnya2() {
        return nilaiLainnya2;
    }

    public void setNilaiLainnya2(float nilaiLainnya2) {
        this.nilaiLainnya2 = nilaiLainnya2;
    }

    public float getNilaiLainnya3() {
        return nilaiLainnya3;
    }

    public void setNilaiLainnya3(float nilaiLainnya3) {
        this.nilaiLainnya3 = nilaiLainnya3;
    }

    public String getMatpelLainnya1() {
        return matpelLainnya1;
    }

    public void setMatpelLainnya1(String matpelLainnya1) {
        this.matpelLainnya1 = matpelLainnya1;
    }

    public String getMatpelLainnya2() {
        return matpelLainnya2;
    }

    public void setMatpelLainnya2(String matpelLainnya2) {
        this.matpelLainnya2 = matpelLainnya2;
    }

    public String getMatpelLainnya3() {
        return matpelLainnya3;
    }

    public void setMatpelLainnya3(String matpelLainnya3) {
        this.matpelLainnya3 = matpelLainnya3;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }
    
    public String parseToQuery(String namaKelas){
       return namaKelas.split("\\s")[1]+namaKelas.split("\\s")[0];
    }
    
}
