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
public class Siswa {
    private static String nis;
    private static String nama;
    private static String jenisKelamin;
    private static String tanggalLahir;
    private static String namaKelas;
    private static String username;
    private static String agama;
    private static String alamat;
    private static String noHP;
    private static String email;
    private static String nilaiSikap;
    private static int scoreDO;
    private static InputStream foto;
    private static Image image;
    private static OrtuSiswa wali1;
    private static OrtuSiswa wali2;
    private static String catatan;
    private static Kehadiran kehadiran;
    private static String fileFoto;
    
    
    public static void setSiswa(String nis){
        try{
            Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
            String sql = "SELECT students.*, penilaian.skor AS skorDO,kategori_sikap.kategori AS nilaiSikap FROM students JOIN penilaian ON students.nis = penilaian.nis JOIN kategori_sikap ON kategori_sikap.id_kategori = penilaian.sikap WHERE students.nis ='"+nis+"'";

            Statement stmt;
            stmt = (Statement) koneksi.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            ResultSet ortu1 = null;
            ResultSet ortu2 = null;
            ResultSet kesalahan = null;
            
            if(res.next()){
                Siswa.nis = res.getString("nis");
                nama = res.getString("nama");
                jenisKelamin = res.getString("jk");
                tanggalLahir = res.getString("tgl_lahir");
                namaKelas = res.getString("nama_kelas");
                username = res.getString("username");
                agama = res.getString("agama");
                alamat = res.getString("alamat");
                noHP = res.getString("no_tlp");
                email = res.getString("email");
                foto = res.getBinaryStream("foto");
                scoreDO = res.getInt("skorDO");
                nilaiSikap = res.getString("nilaiSikap");
                fileFoto = res.getString("file");
                
                if (res.getString("wali_1")!=null) {
                    stmt = (Statement) koneksi.createStatement();
                    sql = "SELECT nik FROM wali WHERE nik = '"+res.getString("wali_1")+"'";
                    ortu1 = stmt.executeQuery(sql);
                }
                if (res.getString("wali_2")!=null) {
                    stmt = (Statement) koneksi.createStatement();
                    sql = "SELECT nik FROM wali WHERE nik = '"+res.getString("wali_2")+"'";
                    ortu2 = stmt.executeQuery(sql);
                }
                
                stmt = (Statement) koneksi.createStatement();
                sql = "SELECT * FROM peringatan WHERE nis = '"+res.getString("nis")+"'";
                kesalahan = stmt.executeQuery(sql);
                
            }
            if (ortu1!=null) {
                if(ortu1.first()){
                    wali1 = new OrtuSiswa(ortu1.getString("nik"));
                }
            }
            if (ortu2!=null) {
                if (ortu2.first()) {
                    wali2 = new OrtuSiswa(ortu2.getString("nik"));
                }
            }
           
            if (kesalahan!=null) {
                if (kesalahan.first()) {
                    catatan = kesalahan.getString("judul");
                }
            }
            
            if (foto != null) {
                InputStream is = foto;
                OutputStream os = new FileOutputStream(new File("src/profile/"+fileFoto+".jpg"));
                byte[] content = new byte[1024];
                int size = 0;
                while((size = is.read(content)) != -1){
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                
               image = new Image("file:src/profile/"+fileFoto+".jpg",140,160,false,true);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAtribute(String inip, String inama,String ijenisKelamin,String itanggalLahir,String ikelas,String iusername,String iemail,InputStream ifoto,int iscoreDO,String inilaiSikap,String iagama,String ialamat,String inohp){
              nis = inip;
              nama = inama;
              jenisKelamin = ijenisKelamin;
              tanggalLahir = itanggalLahir;
              namaKelas = ikelas;
              agama = iagama;
              alamat = ialamat;
              noHP = inohp;
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

    public static void setAgama(String agama) {
        Siswa.agama = agama;
    }

    public static void setAlamat(String alamat) {
        Siswa.alamat = alamat;
    }

    public static void setNoHP(String noHP) {
        Siswa.noHP = noHP;
    }
    
    public static String getNama(){
        return nama;
    }

    public static String getEmail() {
        return email;
    }

    public static String getAgama() {
        return agama;
    }

    public static String getAlamat() {
        return alamat;
    }
    
    public static InputStream getInputStreamFoto(){
        return foto;
    }
    
    public static Image getFoto() {
        if(foto == null){
            return new Image("file:src/profile/siswa.png");
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

    public static void setWali1(OrtuSiswa wali1) {
        Siswa.wali1 = wali1;
    }

    public static void setWali2(OrtuSiswa wali2) {
        Siswa.wali2 = wali2;
    }

    public static String getCatatan() {
        return catatan;
    }
    
    public String getNamaOrtu1(){
        return wali1.getNamaWali();
    }
    
    public String getNoWali1(){
        return wali1.getNoHP();
    }
    
    public String getNamaOrtu2(){
        return wali2.getNamaWali();
    }
    
    public String getNoWali2(){
        return wali2.getNoHP();
    }

    public static String getNoHP() {
        return noHP;
    }

    public static Kehadiran getKehadiran(String bulan,String tahun) {
        kehadiran = new Kehadiran(nis, bulan, tahun);
        return kehadiran;
    }

    public static String getFileFoto() {
        if (fileFoto==null||fileFoto.equals("")) {
            return "siswa";
        }
        return fileFoto;
    }
    
}
