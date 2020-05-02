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
 * @author Syihab
 */
public class RekapKasus {
    private String tanggalPeringatan, judul, isiPeringatan, penanganan, tindakLanjut,permasalahan,latarBelakang,namaKelas,nama;
    
    Connection koneksi;
    public RekapKasus(String id){
         try{
            koneksi =  (Connection) MyConnection.getKoneksi("localhost","3306", "root", "", "project_java");
            String sql = "SELECT peringatan.*,students.* FROM peringatan JOIN students ON students.nis = peringatan.nis WHERE peringatan.id_peringatan = '"+id+"'";

            Statement stmt;
            stmt = (Statement) koneksi.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            
            if(res.first()){
                judul = res.getString("judul");
                isiPeringatan = res.getString("isi_peringatan");
                tanggalPeringatan = res.getString("tanggal_peringatan");
                penanganan = res.getString("penanganan");
                tindakLanjut = res.getString("tindak_lanjut");
                nama = res.getString("nama");
                namaKelas = res.getString("nama_kelas");
                permasalahan = res.getString("permasalahan");
                latarBelakang = res.getString("latar_belakang");
            }
           
        } catch (SQLException e) {
        }
    }

    public RekapKasus() {
        
    }
    
    public String getNama() {
        return nama;
    }

    public String getNamaKelas() {
        return namaKelas;
    }
    
    public String getIsiPeringatan() {
        return isiPeringatan;
    }

    public String getTanggalPeringatan() {
        return tanggalPeringatan;
    }

    public String getJudul() {
        return judul;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTindakLanjut() {
        return tindakLanjut;
    }

    public void setIsiPeringatan(String isiPeringatan) {
        this.isiPeringatan = isiPeringatan;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public void setTanggalPeringatan(String tanggalPeringatan) {
        this.tanggalPeringatan = tanggalPeringatan;
    }

    public void setPermasalahan(String permasalahan) {
        this.permasalahan = permasalahan;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }
    
    public String getPenanganan() {
        return penanganan;
    }

    public void setPenanganan(String penanganan) {
        this.penanganan = penanganan;
    }

    public String getPermasalahan() {
        return permasalahan;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }
     
}