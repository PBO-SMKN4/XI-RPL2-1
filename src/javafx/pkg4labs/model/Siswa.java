/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.pkg4labs.controller.siswa.MyConnection;
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
    private static String foto;
    private static String nilaiSikap;
    private static int scoreDO;
    
    
    public static void setSiswa(String nis){
        try{
          Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
          String sql = "SELECT students.*, penilaian.skor AS skorDO,kategori_sikap.kategori AS nilaiSikap FROM students JOIN penilaian ON students.nis = penilaian.nis JOIN kategori_sikap ON kategori_sikap.id_kategori = penilaian.sikap WHERE students.nis ='"+nis+"'";

          Statement stmt = (Statement) koneksi.createStatement();

          ResultSet res = stmt.executeQuery(sql);
          
          if(res.next()){
              Siswa.nis = res.getString("nis");
              nama = res.getString("nama");
              jenisKelamin = res.getString("jk");
              tanggalLahir = res.getString("tgl_lahir");
              namaKelas = res.getString("nama_kelas");
              username = res.getString("username");
              email = res.getString("email");
              foto = res.getString("foto");
              scoreDO = res.getInt("skorDO");
              nilaiSikap = res.getString("nilaiSikap");
          }
          
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public static void setAtribute(String inip, String inama,String ijenisKelamin,String itanggalLahir,String ikelas,String iusername,String iemail,String ifoto,int iscoreDO,String inilaiSikap){
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

    public static void setFoto(String foto) {
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

    public static String getFoto() {
        return foto;
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
