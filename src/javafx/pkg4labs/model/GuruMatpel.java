/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.scene.image.Image;

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
    private String noWa;
    private InputStream foto;
    private Image image;
    Connection koneksi;
    
    public GuruMatpel(String nip){
        this.nip = nip;
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        
        try{
            Statement stmt =  koneksi.createStatement();
            String query = "SELECT * FROM guru_matpel WHERE nip ='"+nip+"'";
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.first()){
                nama = rs.getString("nama");
                matpel = rs.getString("mapel");
                jenisKelamin = rs.getString("jk");
                email = rs.getString("email");
                noWa = rs.getString("no_wa");
                foto = rs.getBinaryStream("foto");
            }
            
            if (foto != null) {
                InputStream is = foto;
                OutputStream os = new FileOutputStream(new File("src/guru/guru.jpg"));
                byte[] content = new byte[1024] ;
                int size = 0;
                while((size = is.read(content)) != -1){
                    os.write(content, 0, size);
                }
                os.close();
                is.close();

               image = new Image("file:src/guru/guru.jpg",100,150,false,true);
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

    public String getNoWa() {
        return noWa;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setMatpel(String matpel) {
        this.matpel = matpel;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoWa(String no_wa) {
        this.noWa = no_wa;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public Image getFoto() {
        if (foto == null) {
            return new Image("file:src/javafx/assets/image/ava3.png");
        }
        return image;
    }
    
    
    
    
    
}
