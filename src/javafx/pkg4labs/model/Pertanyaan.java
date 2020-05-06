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
public class Pertanyaan {
    private String idPertanyaan;
    private String isiPertanyaan;
    private String idPengirim;
    private String tanggalUpload;
    private String tipe_soal;
    private String namaTipeSoal;
    private String fileFoto;
    private InputStream foto;
    private Image image;
    private int jmlJawaban;
    
    public Pertanyaan(String idPertanyaan) {
       try{
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            String sql = "SELECT * FROM pertanyaan WHERE id_pertanyaan='"+idPertanyaan+"'";

            Statement stmt;
            stmt = (Statement) koneksi.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            
            while(res.next()){
                this.idPertanyaan = res.getString("id_pertanyaan");
                tipe_soal = res.getString("tipe_soal");
                isiPertanyaan = res.getString("pertanyaan");
                idPengirim = res.getString("nis");
                foto = res.getBinaryStream("foto");
                tanggalUpload = res.getString("tanggal_upload");
                fileFoto = res.getString("file_foto");
            }
            stmt = (Statement) koneksi.createStatement();
            sql = "SELECT * FROM tipe_soal WHERE id_tipe = "+tipe_soal;
            res = stmt.executeQuery(sql);
            if (res.first()) {
               namaTipeSoal = res.getString("nama_matpel");
           }
            
            stmt = koneksi.createStatement();
            sql = "SELECT COUNT(id_jawaban) AS jumlah FROM jawaban WHERE id_pertanyaan ='"+idPertanyaan+"'";
            res = stmt.executeQuery(sql);
            if (res.first()) {
               jmlJawaban = res.getInt("jumlah");
            }
            
            if (foto != null) {
               InputStream is = foto;
                OutputStream os = new FileOutputStream(new File("src/QnA/"+fileFoto+".jpg"));
                byte[] content = new byte[1024];
                int size = 0;
                while((size = is.read(content)) != -1){
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
               
               image = new Image("file:src/QnA/"+fileFoto+".jpg",657,349,false,true);
           }
                
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
           
        
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

    public Image getFoto() {
        if (foto == null) {
            return null;
        }
        return image;
    }

    public String getNamaTipeSoal() {
        return namaTipeSoal;
    }

    public int getJmlJawaban() {
        return jmlJawaban;
    }
    
}
    