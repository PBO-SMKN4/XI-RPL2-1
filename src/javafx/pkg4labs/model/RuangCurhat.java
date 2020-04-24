/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.pkg4labs.controller.siswa.MyConnection;

/**
 *
 * @author Diazs
 */
public class RuangCurhat {
    
    private String idRuang;
    private ArrayList<PesanCurhat> pesan;
    
    
    public RuangCurhat(String nis,String nip){
        pesan = new ArrayList<>();
        PesanCurhat tempPesan = new PesanCurhat();
        Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        
        try {
            Statement stmt = koneksi.createStatement();
            
            String sql = "SELECT * FROM ruang_curhat WHERE id_siswa = '"+nis+"'"+" AND id_guru = '"+nip+"'";
            
            ResultSet res = stmt.executeQuery(sql);
            
            if (res.first()) {
                idRuang=res.getString("id_ruang");
            }
            
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM curhat WHERE id_ruang = '"+idRuang+"'";
            res = stmt.executeQuery(sql);
            
            while(res.next()) {
                tempPesan = new PesanCurhat();
                tempPesan.setIdPesan(res.getString("id_curhat"));
                tempPesan.setIsiPesan(res.getString("isi_chat"));
                tempPesan.setIdPengirim(res.getString("id_siswa")!=null?res.getString("id_siswa"):res.getString("id_guru"));
                tempPesan.setWaktuKirim(res.getString("waktu_dikirim"));
                
                pesan.add(tempPesan);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIdRuang() {
        return idRuang;
    }

    public ArrayList<PesanCurhat> getPesan() {
        return pesan;
    }

}




















