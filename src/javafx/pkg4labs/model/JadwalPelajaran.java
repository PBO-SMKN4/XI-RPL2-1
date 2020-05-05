/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.scene.image.Image;

/**
 *
 * @author Diazs
 */
public class JadwalPelajaran {
    private String namaMapel, jam, ruangan, kategori, hari, kelas, mapel;
    private GuruMatpel guru = null;
    
    Connection koneksi;
    public JadwalPelajaran(String mapel){
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        
        try{
            Statement stmt =  koneksi.createStatement();
            String query = "SELECT * "
                    + "FROM jadwal_pelajaran JOIN detail_jadwal ON "
                    + "jadwal_pelajaran.id_jadwal = detail_jadwal.id_jadwal "
                    + "JOIN guru_matpel ON detail_jadwal.guru_matpel = guru_matpel.nip "
                    + "JOIN mapel ON mapel.mapel = guru_matpel.mapel "
                    + "WHERE mapel.mapel = '"+mapel+"'";
            ResultSet rs = stmt.executeQuery(query);
   
            if(rs.first()){
                guru = new GuruMatpel(rs.getString("nip"));
                this.mapel = rs.getString("mapel");
                hari = rs.getString("hari");
                namaMapel = rs.getString("nama_mapel");
                jam = rs.getString("jam_ke");
                ruangan = rs.getString("ruangan");
                kategori = rs.getString("kategori");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }

    public String getGuruMapel() {
        return guru.getNama();
    }

    public void setGuruMapel(String guruMapel) {
        this.guru.setNama(guruMapel);
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNip() {
        return "1819117614";
    }

    public void setNip(String nip) {
        this.guru.setNip(nip);
    }

    public String getNoWa() {
        return guru.getNoWa();
    }

    public void setNoWa(String noWa) {
        this.guru.setNoWa(noWa);
    }

    public String getEmail() {
        return guru.getEmail();
    }

    public void setEmail(String email) {
        this.guru.setEmail(email);
    }

    public Image getFoto() {
        return guru.getFoto();
    }

    public void setFoto(InputStream foto) {
        this.guru.setFoto(foto);
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getHari() {
        return hari;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }
    
    
}