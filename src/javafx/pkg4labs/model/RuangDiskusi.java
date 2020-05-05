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
 * @author ASUS
 */
public class RuangDiskusi {
    
    private static String nis;
    private static String id_pertanyaan;
    private static String tipe_soal;
    private static String pertanyaan;
    private static String id_jawaban;
    private static String jawaban;
    private static InputStream foto;
    private static Image image;
    private static ArrayList<Pertanyaan> listPertanyaan = new ArrayList<>();
    private static ArrayList<Jawaban> listJawaban = new ArrayList<>();
    
    
    public RuangDiskusi(String id){
        try{
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            String sql = "SELECT * FROM students JOIN jawaban ON students.nis = jawaban.nis JOIN pertanyaan ON jawaban.id_pertanyaan = pertanyaan.id_pertanyaan JOIN tipe_soal ON pertanyaan.tipe_soal = tipe_soal.id_tipe WHERE pertanyaan.id_pertanyaan='"+id+"'";

            Statement stmt;
            stmt = (Statement) koneksi.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            
            if(res.next()){
                nis = res.getString("nis");
                id_pertanyaan = res.getString("id_pertanyaan");
                tipe_soal = res.getString("tipe_soal");
                pertanyaan = res.getString("pertanyaan");
                id_jawaban = res.getString("id_jawaban");
                jawaban = res.getString("jawaban");
                
            }   
            if (foto != null) {
                InputStream is = foto;
                OutputStream os = new FileOutputStream(new File("profile/profile.jpg"));
                byte[] content = new byte[1024];
                int size = 0;
                while((size = is.read(content)) != -1){
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                
                image = new Image("file:profile/pertanyaan.jpg",100,150,true,true);
            }    
                
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNis() {
        return nis;
    }

    public static void setNis(String nis) {
        RuangDiskusi.nis = nis;
    }

    public static String getId_pertanyaan() {
        return id_pertanyaan;
    }

    public static void setId_pertanyaan(String id_pertanyaan) {
        RuangDiskusi.id_pertanyaan = id_pertanyaan;
    }

    public static String getTipe_soal() {
        return tipe_soal;
    }

    public static void setTipe_soal(String tipe_soal) {
        RuangDiskusi.tipe_soal = tipe_soal;
    }

    public static String getPertanyaan() {
        return pertanyaan;
    }

    public static void setPertanyaan(String pertanyaan) {
        RuangDiskusi.pertanyaan = pertanyaan;
    }

    public static String getId_jawaban() {
        return id_jawaban;
    }

    public static void setId_jawaban(String id_jawaban) {
        RuangDiskusi.id_jawaban = id_jawaban;
    }

    public static String getJawaban() {
        return jawaban;
    }

    public static void setJawaban(String jawaban) {
        RuangDiskusi.jawaban = jawaban;
    }

    public static Image getFoto() {
        if(foto == null){
            return new Image("file:profile/siswa.png");
        }
        return image;
    }

    public static void setFoto(InputStream foto) {
        RuangDiskusi.foto = foto;
    }

    public static Image getImage() {
        return image;
    }

    public static void setImage(Image image) {
        RuangDiskusi.image = image;
    }
    
    
}