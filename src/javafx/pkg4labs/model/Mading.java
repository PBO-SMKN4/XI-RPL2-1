/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.scene.image.Image;

/**
 *
 * @author Diazs
 */
public class Mading {
    private String idMading;
    private String kategori;
    private String tema;
    private InputStream foto;
    private String pengirim;
    private String emailPengirim;
    private String tanggalUpload;
    private String tanggalKadaluarsa;
    private String rolePengirim;
    private String fileFoto;
    private String contactPerson;
    private String sql;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private Image image;
    

    public Mading(String id) throws SQLException, FileNotFoundException, IOException {
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        stmt = koneksi.createStatement();
        sql = "SELECT * FROM mading WHERE id_mading = '"+id+"'";
        res = stmt.executeQuery(sql);
        
        if(res.first()){
            idMading = res.getString("id_mading");
            kategori = res.getString("kategori");
            tema = res.getString("tema");
            foto = res.getBinaryStream("foto");
            pengirim = res.getString("pengirim");
            emailPengirim = res.getString("email");
            tanggalUpload = res.getString("tgl_upload");
            tanggalKadaluarsa = res.getString("tgl_kadaluarsa");
            rolePengirim = res.getString("role_pengirim");
            fileFoto = res.getString("file_foto");
            contactPerson = res.getString("contact_person");
        }
        
        if (foto != null) {
            InputStream is = foto;
            OutputStream os = new FileOutputStream(new File("src/mading/"+fileFoto+".jpg"));
            byte[] content = new byte[1024];
            int size = 0;
            while((size = is.read(content)) != -1){
                os.write(content, 0, size);
            }
            os.close();
            is.close();

            image = new Image("file:src/mading/"+fileFoto+".jpg",249,326,true,true);
        }
        
    }

    public String getIdMading() {
        return idMading;
    }

    public void setIdMading(String idMading) {
        this.idMading = idMading;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Image getFoto() {
        if (foto==null) {
            return new Image("file:src/javafx/assets/image/poster1.png");
        }
        return image;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getEmailPengirim() {
        return emailPengirim;
    }

    public void setEmailPengirim(String emailPengirim) {
        this.emailPengirim = emailPengirim;
    }

    public String getTanggalUpload() {
        return tanggalUpload;
    }

    public void setTanggalUpload(String tanggalUpload) {
        this.tanggalUpload = tanggalUpload;
    }

    public String getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    public void setTanggalKadaluarsa(String tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public String getRolePengirim() {
        return rolePengirim;
    }

    public void setRolePengirim(String rolePengirim) {
        this.rolePengirim = rolePengirim;
    }
    
    
    
}