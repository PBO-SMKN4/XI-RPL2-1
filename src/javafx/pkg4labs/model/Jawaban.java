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
public class Jawaban {
    private String idJawaban;
    private String idPenjawab;
    private String idPertanyaan;
    private String isiJawaban;
    private String tanggalJawab;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;

    public Jawaban() {
        //TODO instance koneksi, statement
        
        //TODO query select * from jawaban
        
        //TODO while
        
        /*while(){
            TODO set -> where id_pertanyaan = 'idPertanyaan'
        }*/
    }

    public String getIdJawaban() {
        return idJawaban;
    }

    public void setIdJawaban(String idJawaban) {
        this.idJawaban = idJawaban;
    }

    public String getIdPenjawab() {
        return idPenjawab;
    }

    public void setIdPenjawab(String idPenjawab) {
        this.idPenjawab = idPenjawab;
    }

    public String getIdPertanyaan() {
        return idPertanyaan;
    }

    public void setIdPertanyaan(String idPertanyaan) {
        this.idPertanyaan = idPertanyaan;
    }

    public String getIsiJawaban() {
        return isiJawaban;
    }

    public void setIsiJawaban(String isiJawaban) {
        this.isiJawaban = isiJawaban;
    }

    public String getTanggalJawab() {
        return tanggalJawab;
    }

    public void setTanggalJawab(String tanggalJawab) {
        this.tanggalJawab = tanggalJawab;
    }
    
    
}
