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
    private int jmlBelumDibaca = 0;
    
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
            }else{
                stmt = koneksi.createStatement();
                sql = "INSERT INTO ruang_curhat(id_siswa,id_guru) VALUES('"+nis+"','"+nip+"')";
                int berhasil = stmt.executeUpdate(sql);
                if (berhasil == 1) {
                    sql = "SELECT * FROM ruang_curhat WHERE id_siswa = '"+nis+"'"+" AND id_guru = '"+nip+"'";
                    res = stmt.executeQuery(sql);
                }if (res.first()) {
                    idRuang=res.getString("id_ruang");
                }
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
                tempPesan.setDilihat(res.getString("status_dilihat"));
                if (res.getString("status_dilihat").equalsIgnoreCase("belum")) {
                    if (Siswa.getNis()!=null) {
                        if (tempPesan.getIdPengirim().equals(Siswa.getNis())) {
                            jmlBelumDibaca++; 
                        }
                    }else{
                        if (tempPesan.getIdPengirim().equals(GuruBK.getNip())) {
                            jmlBelumDibaca++;
                        }else if(tempPesan.getIdPengirim().equals(nis)){
                            jmlBelumDibaca++;
                        }
                    }
                }
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

    public int getJmlBelumDibaca() {
        return jmlBelumDibaca;
    }
    
    
}