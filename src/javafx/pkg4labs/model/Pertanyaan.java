/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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