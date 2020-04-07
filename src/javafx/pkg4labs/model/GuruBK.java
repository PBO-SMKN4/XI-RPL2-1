/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class GuruBK {
    private static String nip;
    private static String nama;
    private static String jenisKelamin;
    private static String tanggalLahir;
    private static String noWa;
    private static String username;
    private static String email;
    private static String foto;
    
    public static void setGuruBK(String nip){
        try{
          Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
          String sql = "SELECT * FROM teachers WHERE nip ='"+nip+"'";

          Statement stmt = (Statement) koneksi.createStatement();

          ResultSet res = stmt.executeQuery(sql);
          
          if(res.next()){
              GuruBK.nip = res.getString("nip");
              nama = res.getString("nama");
              jenisKelamin = res.getString("jk");
              tanggalLahir = res.getString("tgl_lahir");
              noWa = res.getString("no_whatsapp");
              username = res.getString("username");
              email = res.getString("email");
              foto = res.getString("foto");
          }
          
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public static void setAtribute(String inip, String inama,String ijenisKelamin,String itanggalLahir,String iwa,String iusername,String iemail,String ifoto){
              nip = inip;
              nama = inama;
              jenisKelamin = ijenisKelamin;
              tanggalLahir = itanggalLahir;
              noWa = iwa;
              username = iusername;
              email = iemail;
              foto = ifoto;
    }
    
    public static void setNip(String nip) {
        GuruBK.nip = nip;
    }

    public static void setNama(String nama) {
        GuruBK.nama = nama;
    }

    public static void setEmail(String email) {
        GuruBK.email = email;
    }

    public static void setFoto(String foto) {
        GuruBK.foto = foto;
    }

    public static void setJenisKelamin(String jenisKelamin) {
        GuruBK.jenisKelamin = jenisKelamin;
    }

    public static void setNoWa(String noWa) {
        GuruBK.noWa = noWa;
    }

    public static void setTanggalLahir(String tanggalLahir) {
        GuruBK.tanggalLahir = tanggalLahir;
    }

    public static void setUsername(String username) {
        GuruBK.username = username;
    }
    
    public static String getNip(){
        return nip;
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

    public static String getNoWa() {
        return noWa;
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

}
