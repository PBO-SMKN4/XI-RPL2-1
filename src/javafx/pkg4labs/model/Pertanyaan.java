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
public class Pertanyaan {
    private String idPertanyaan;
    private String isiPertanyaan;
    private String idPengirim;
    private String tanggalUpload;
    private String tipe_soal;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;

    public Pertanyaan(String idPertanyaan) {
       try{
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            String sql = "SELECT * FROM pertanyaan";

            Statement stmt;
            stmt = (Statement) koneksi.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            
            while(res.next()){
                idPertanyaan = res.getString("id_pertanyaan");
                tipe_soal = res.getString("tipe_soal");
                isiPertanyaan = res.getString("pertanyaan");
                idPengirim = res.getString("nis");
                tanggalUpload = res.getString("tanggal_upload");              
            }      
                
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        //TODO instance koneksi, statement
        
        //TODO query select * from pertanyaan
        
        //TODO while
        
        /*while(){
            TODO set -> where id_pertanyaan = 'idPertanyaan'
        }*/
        
    }

    public String getIdPertanyaan() {
        return idPertanyaan;
    }

    public void setIdPertanyaan(String idPertanyaan) {
        this.idPertanyaan = idPertanyaan;
    }

    public String getIsiPertanyaan() {
        return isiPertanyaan;
    }

    public void setIsiPertanyaan(String isiPertanyaan) {
        this.isiPertanyaan = isiPertanyaan;
    }

    public String getIdPengirim() {
        return idPengirim;
    }

    public void setIdPengirim(String idPengirim) {
        this.idPengirim = idPengirim;
    }

    public String getTanggalUpload() {
        return tanggalUpload;
    }

    public void setTanggalUpload(String tanggalUpload) {
        this.tanggalUpload = tanggalUpload;
    }

    public String getTipe_soal() {
        return tipe_soal;
    }

    public void setTipe_soal(String tipe_soal) {
        this.tipe_soal = tipe_soal;
    }
    
}