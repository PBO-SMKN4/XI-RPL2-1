/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class Siswa {
    private static String nis;
    private static String nama;
    private static String jenisKelamin;
    private static String tanggalLahir;
    private static String namaKelas;
    private static String username;
    private static String email;
    private static String nilaiSikap;
    private static int scoreDO;
    private static InputStream foto;
    private static Image image;
    private static ortuSiswa wali1;
    private static ortuSiswa wali2;
    
    
    public static void setSiswa(String nis){
        try{
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            String sql = "SELECT students.*, penilaian.skor AS skorDO,kategori_sikap.kategori AS nilaiSikap FROM students JOIN penilaian ON students.nis = penilaian.nis JOIN kategori_sikap ON kategori_sikap.id_kategori = penilaian.sikap WHERE students.nis ='"+nis+"'";

            Statement stmt = (Statement) koneksi.createStatement();

            ResultSet res = stmt.executeQuery(sql);
            ResultSet ortu1 = null;
            ResultSet ortu2 = null;
            
            if(res.next()){
                Siswa.nis = res.getString("nis");
                nama = res.getString("nama");
                jenisKelamin = res.getString("jk");
                tanggalLahir = res.getString("tgl_lahir");
                namaKelas = res.getString("nama_kelas");
                username = res.getString("username");
                email = res.getString("email");
                foto = res.getBinaryStream("foto");
                scoreDO = res.getInt("skorDO");
                nilaiSikap = res.getString("nilaiSikap");
                sql = "SELECT id_wali FROM wali WHERE id_wali = "+res.getString("wali_1");
                ortu1 = stmt.executeQuery(sql);
                sql = "SELECT id_wali FROM wali WHERE id_wali = "+res.getString("wali_2");
                ortu2 = stmt.executeQuery(sql);
            }
            
           if(ortu1.first()){
               wali1 = new ortuSiswa(ortu1.getString("id_wali"));
           }
           
           if (ortu2.first()) {
               wali2 = new ortuSiswa(ortu2.getString("id_wali"));
           }
            
            InputStream is = foto;
            OutputStream os = new FileOutputStream(new File("profile/profile.jpg"));
            byte[] content = new byte[1024];
            int size = 0;
            while((size = is.read(content)) != -1){
                os.write(content, 0, size);
            }
            os.close();
            is.close();
            
           image = new Image("file:profile/profile.jpg",100,150,true,true);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAtribute(String inip, String inama,String ijenisKelamin,String itanggalLahir,String ikelas,String iusername,String iemail,InputStream ifoto,int iscoreDO,String inilaiSikap){
              nis = inip;
              nama = inama;
              jenisKelamin = ijenisKelamin;
              tanggalLahir = itanggalLahir;
              namaKelas = ikelas;
              username = iusername;
              email = iemail;
              foto = ifoto;
              scoreDO = iscoreDO;
              nilaiSikap = inilaiSikap;
              
    }
    
    public static void setNis(String nis) {
        Siswa.nis = nis;
    }

    public static void setNama(String nama) {
        Siswa.nama = nama;
    }

    public static void setEmail(String email) {
        Siswa.email = email;
    }

    public static void setFoto(InputStream foto) {
        Siswa.foto = foto;
    }

    public static void setJenisKelamin(String jenisKelamin) {
        Siswa.jenisKelamin = jenisKelamin;
    }
    
    public static void setTanggalLahir(String tanggalLahir) {
        Siswa.tanggalLahir = tanggalLahir;
    }

    public static void setUsername(String username) {
        Siswa.username = username;
    }

    public static void setScoreDO(int scoreDO) {
        Siswa.scoreDO = scoreDO;
    }
    
    public static void setNilaiSikap(String nilaiSikap) {
        Siswa.nilaiSikap = nilaiSikap;
    }

    public static void setNamakelas(String namaKelas) {
        Siswa.namaKelas = namaKelas;
    }
    
    public static String getNis(){
        return nis;
    }
    
    public static String getNama(){
        return nama;
    }

    public static String getEmail() {
        return email;
    }

    public static Image getFoto() {
        if(foto == null){
            return new Image("profile/siswa.png");
        }
        return image;
    }

    public static String getTanggalLahir() {
        return tanggalLahir;
    }

    public static String getUsername() {
        return username;
    }

    public static String getJenisKelamin() {
        return jenisKelamin;
    }

    public static String getNamaKelas() {
        return namaKelas;
    }

    public static String getNilaiSikap() {
        return nilaiSikap;
    }

    public static int getScoreDO() {
        return scoreDO;
    }

    
    
    
}
