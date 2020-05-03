  
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
public class GuruMatpel {
    private String nip;
    private String nama;
    private String matpel;
    private String jenisKelamin;
    private String email;
    private String no_wa;
    
    Connection koneksi;
    public GuruMatpel(String nip){
        this.nip = nip;
        
        Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        
        try{
            Statement stmt =  koneksi.createStatement();
            String query = "SELECT * FROM guru_matpel WHERE nip ='"+nip+"'";
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.first()){
                nama = rs.getString("nama");
                matpel = rs.getString("matpel");
                jenisKelamin = rs.getString("jk");
                email = rs.getString("email");
                no_wa = rs.getString("no_wa");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getNip() {
        return nip;
    }

    public String getEmail() {
        return email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getMatpel() {
        return matpel;
    }

    public String getNama() {
        return nama;
    }

    public String getNo_wa() {
        return no_wa;
    } 
    
}