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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.pkg4labs.controller.siswa.MyConnection;

/**
 *
 * @author Diazs
 */
public class ListCurhat {
    private ArrayList<PesanCurhat> pesan;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private String sql;
    private PesanCurhat psn;

    public ListCurhat() {
        pesan = new ArrayList<>();
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        try {
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM ruang_curhat JOIN teachers "
                    + "ON ruang_curhat.id_guru = teachers.nip "
                    + "JOIN students ON students.nis = ruang_curhat.id_siswa "
                    + "JOIN curhat ON curhat.id_ruang = ruang_curhat.id_ruang "
                    + "WHERE teachers.nip = '"+GuruBK.getNip()+"' "
                    + "GROUP BY students.nis ";
            res = stmt.executeQuery(sql);
            
            while(res.next()){
                stmt = koneksi.createStatement();
                sql = "SELECT * FROM ruang_curhat JOIN teachers " +
                "ON ruang_curhat.id_guru = teachers.nip " +
                "JOIN students ON students.nis = ruang_curhat.id_siswa " +
                "JOIN curhat ON curhat.id_ruang = ruang_curhat.id_ruang " +
                "WHERE teachers.nip = '"+GuruBK.getNip()+"'" +
                "AND students.nis = '"+res.getString("students.nis")+"'" +
                "AND curhat.id_siswa = '"+res.getString("students.nis")+"'" +
                "ORDER BY curhat.id_curhat DESC ";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.first()) {
                    psn = new PesanCurhat();
                    psn.setNamaPengirim(rs.getString("students.nama"));
                    psn.setIdPengirim(rs.getString("curhat.id_siswa"));
                    psn.setIdPesan(rs.getString("curhat.id_curhat"));
                    psn.setDilihat(rs.getString("status_dilihat"));
                    psn.setIsiPesan(rs.getString("curhat.isi_chat"));
                    psn.setWaktuKirim(parseToMinuteHours(rs.getString("waktu_dikirim")));
                    
                    pesan.add(psn);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ListCurhat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ListCurhat(String query) {
        pesan = new ArrayList<>();
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        try {
            stmt = koneksi.createStatement();
            sql = "SELECT * FROM ruang_curhat JOIN teachers "
                    + "ON ruang_curhat.id_guru = teachers.nip "
                    + "JOIN students ON students.nis = ruang_curhat.id_siswa "
                    + "JOIN curhat ON curhat.id_ruang = ruang_curhat.id_ruang "
                    + "WHERE teachers.nip = '"+GuruBK.getNip()+"' "
                    + "AND (students.nama LIKE '%"+query+"%' "
                    + "OR curhat.isi_chat LIKE '%"+query+"%') "
                    + "GROUP BY students.nis";
            res = stmt.executeQuery(sql);
            
            while(res.next()){
                stmt = koneksi.createStatement();
                sql = "SELECT * FROM ruang_curhat JOIN teachers " +
                "ON ruang_curhat.id_guru = teachers.nip " +
                "JOIN students ON students.nis = ruang_curhat.id_siswa " +
                "JOIN curhat ON curhat.id_ruang = ruang_curhat.id_ruang " +
                "WHERE teachers.nip = '"+GuruBK.getNip()+"'" +
                "AND students.nis = '"+res.getString("students.nis")+"'" +
                "AND curhat.id_siswa = '"+res.getString("students.nis")+"'" +
                "ORDER BY curhat.id_curhat DESC ";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.first()) {
                    psn = new PesanCurhat();
                    psn.setNamaPengirim(rs.getString("students.nama"));
                    psn.setIdPengirim(rs.getString("curhat.id_siswa"));
                    psn.setIdPesan(rs.getString("curhat.id_curhat"));
                    psn.setDilihat(rs.getString("status_dilihat"));
                    psn.setIsiPesan(rs.getString("curhat.isi_chat"));
                    psn.setWaktuKirim(parseToMinuteHours(rs.getString("waktu_dikirim")));
                    
                    pesan.add(psn);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ListCurhat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String parseToMinuteHours(String dateTime){
        String s = dateTime.split("\\s")[1];
        String[] s1 = {s.split(":")[0],":",s.split(":")[1]};
        String result = "";
        for (String s2 : s1) {
            result+=s2;
        }
        return result;
    }

    public ArrayList<PesanCurhat> getPesan() {
        return pesan;
    }
    
}