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
import java.util.ArrayList;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.scene.image.Image;

/**
 *
 * @author Dean Beniqno A
 */
public class Jawaban {
    private String idJawaban;
    private String idPenjawab;
    private String idPertanyaan;
    private String isiJawaban;
    private String tanggalJawab;
    private String fileFoto;
    private String namaPenjawab;
    private InputStream foto;
    private Image image;
    private Image fotoPenjawab;
    private float rating;
    private int vote;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private ArrayList<Students> listVoters;

    public Jawaban(String id) {
        
        try{
            koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            String sql = "SELECT * FROM jawaban WHERE id_jawaban = '"+id+"'";

            stmt = (Statement) koneksi.createStatement();
            res = stmt.executeQuery(sql);
            
            while(res.next()){
                idJawaban = res.getString("id_jawaban");
                idPertanyaan = res.getString("id_pertanyaan");
                idPenjawab = res.getString("nis");
                isiJawaban = res.getString("jawaban");
                foto = res.getBinaryStream("foto");
                rating = res.getFloat("rating");
                tanggalJawab = res.getString("tanggal_upload"); 
                fileFoto = res.getString("file_foto");
                namaPenjawab = new Students(idPenjawab).getNama();
                fotoPenjawab = new Students(idPenjawab).getFoto();
            }
            
            stmt = (Statement) koneksi.createStatement();
            sql = "SELECT * FROM voter WHERE id_jawaban = "+idJawaban;
            res = stmt.executeQuery(sql);
            int total = 0;
            listVoters = new ArrayList<>();
            
            while (res.next()) {
                listVoters.add(new Students(res.getString("nis")));
                total++;
            }
            
            vote = total;
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
    
    public Image getFoto(){
        if (foto == null) {
            return null;
        }
        return image;
    }
    
    public boolean alreadyVote(String nis){
        boolean alrd = false;
        for (Students listVoter : listVoters) {
            if (listVoter.getNis().equals(nis)) {
                alrd = true;
            }
        }
        return alrd;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }    
    
    public InputStream getInputStreamFoto(){
        return foto;
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

    public String getFileFoto() {
        return fileFoto;
    }

    public void setFileFoto(String fileFoto) {
        this.fileFoto = fileFoto;
    }

    public String getNamaPenjawab() {
        return namaPenjawab;
    }

    public void setNamaPenjawab(String namaPenjawab) {
        this.namaPenjawab = namaPenjawab;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getFotoPenjawab() {
        return fotoPenjawab;
    }

    public void setFotoPenjawab(Image fotoPenjawab) {
        this.fotoPenjawab = fotoPenjawab;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
}