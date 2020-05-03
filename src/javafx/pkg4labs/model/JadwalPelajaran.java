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
public class JadwalPelajaran {
    ArrayList<GuruMatpel> listGuru = new ArrayList<>();
    String kelas;
    
    Connection koneksi;
    public JadwalPelajaran(String kelas){
        this.kelas = kelas;
        GuruMatpel GM;
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        
        try{
            Statement stmt =  koneksi.createStatement();
            String query = "SELECT jadwal_pelajaran.*,detail_jadwal.*,guru_matpel.* FROM jadwal_pelajaran JOIN detail_jadwal ON jadwal_pelajaran.id_jadwal = detail_jadwal.id_jadwal JOIN guru_matpel ON detail_jadwal.guru_matpel = guru_matpel.nip WHERE jadwal_pelajaran.nama_kelas = '"+kelas+"'";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                GM = new GuruMatpel(rs.getString("nip"));
                listGuru.add(GM);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<GuruMatpel> getMatpelByDay(String hari){
        GuruMatpel GM;
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        
        try{
            Statement stmt =  koneksi.createStatement();
            String query = "SELECT jadwal_pelajaran.*,detail_jadwal.*,guru_matpel.* FROM jadwal_pelajaran JOIN detail_jadwal ON jadwal_pelajaran.id_jadwal = detail_jadwal.id_jadwal JOIN guru_matpel ON detail_jadwal.guru_matpel = guru_matpel.nip WHERE jadwal_pelajaran.nama_kelas = '"+kelas+"' AND detail_jadwal.hari = '"+hari+"'";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                GM = new GuruMatpel(rs.getString("nip"));
                listGuru.add(GM);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return this.listGuru;
    }

    public ArrayList<GuruMatpel> getListGuru() {
        return listGuru;
    }
}