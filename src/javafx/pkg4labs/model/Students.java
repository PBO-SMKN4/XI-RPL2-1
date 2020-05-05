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
public class Students {
    private String nis;
    private String nama;
    private String jenisKelamin;
    private String tanggalLahir;
    private String namaKelas;
    private String username;
    private String email;
    private String agama;
    private String alamat;
    private String noHP;
    private String nilaiSikap;
    private int scoreDO;
    private InputStream foto;
    private Image image;
    private OrtuSiswa wali1 = null;
    private OrtuSiswa wali2 = null;
    private String catatan = null;
    private Kehadiran kehadiran;
    private String fileFoto;
    
    
    public Students(String nis){
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
                this.nis = res.getString("nis");
                nama = res.getString("nama");
                jenisKelamin = res.getString("jk");
                tanggalLahir = res.getString("tgl_lahir");
                agama = res.getString("agama");
                alamat = res.getString("alamat");
                noHP = res.getString("no_tlp");
                namaKelas = res.getString("nama_kelas");
                username = res.getString("username");
                email = res.getString("email");
                foto = res.getBinaryStream("foto");
                scoreDO = res.getInt("skorDO");
                nilaiSikap = res.getString("nilaiSikap");
                fileFoto = res.getString("file");
                sql = "SELECT nik FROM wali WHERE nik = "+res.getString("wali_1");
                stmt = (Statement) koneksi.createStatement();
                ortu1 = stmt.executeQuery(sql);
                sql = "SELECT nik FROM wali WHERE nik = "+res.getString("wali_2");
                stmt = (Statement) koneksi.createStatement();
                ortu2 = stmt.executeQuery(sql);
                sql = "SELECT * FROM peringatan WHERE nis = '"+res.getString("nis")+"'";
                stmt = (Statement) koneksi.createStatement();
                kesalahan = stmt.executeQuery(sql);
            }
            
           if(ortu1!=null){
               if (ortu1.first()) {
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

               image = new Image("file:src/profile/"+fileFoto+".jpg",100,150,true,true);
            }
            sql = "SELECT * FROM nilai";
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAtribute(String inip, String inama,String ijenisKelamin,String itanggalLahir,String ikelas,String iusername,String iemail,InputStream ifoto,int iscoreDO,String inilaiSikap){
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
    
    public void setNis(String nis) {
        this.nis = nis;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
    
    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScoreDO(int scoreDO) {
        this.scoreDO = scoreDO;
    }
    
    public void setNilaiSikap(String nilaiSikap) {
        this.nilaiSikap = nilaiSikap;
    }

    public void setNamakelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public void setWali1(OrtuSiswa wali1) {
        this.wali1 = wali1;
    }

    public void setWali2(OrtuSiswa wali2) {
        this.wali2 = wali2;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }
    
    public String getNis(){
        return nis;
    }
    
    public String getNama(){
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public Image getFoto() {
        if(foto == null){
            return new Image("file:src/profile/siswa.png");
        }
        return image;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getUsername() {
        return username;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public String getNilaiSikap() {
        return nilaiSikap;
    }

    public int getScoreDO() {
        return scoreDO;
    }

    public String getAgama() {
        return agama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoHP() {
        return noHP;
    }

    public OrtuSiswa getWali1() {
        return wali1;
    }

    public OrtuSiswa getWali2() {
        return wali2;
    }

    public String getCatatan() {
        return catatan;
    }
    
    public String getNamaOrtu1(){
        if (wali1!=null) {
            return wali1.getNamaWali();
        }
        return "";
    }
    
    public String getNoWali1(){
        if (wali1!=null) {
            return wali1.getNoHP();
        }
        return "";
    }
    
    public String getNamaOrtu2(){
        if (wali2!=null) {
            return wali2.getNamaWali();
        }
        return "";
    }
    
    public String getNoWali2(){
        if (wali2!=null) {
            return wali2.getNoHP();
        }
        return "";
    }

    public Kehadiran getKehadiran(String bulan,String tahun) {
        kehadiran = new Kehadiran(nis, bulan, tahun);
        return kehadiran;
    }

    public String getFileFoto() {
         if (fileFoto==null||fileFoto.equals("")) {
            return "siswa";
        }
        return fileFoto;
    }
    
}