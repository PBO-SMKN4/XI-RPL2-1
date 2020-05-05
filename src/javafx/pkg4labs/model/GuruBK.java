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
    private static String fileFoto;
    private static InputStream foto;
    private static Image image;
    
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
                foto = res.getBinaryStream("foto");
                fileFoto = res.getString("file");
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
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    public static void setAtribute(String inip, String inama,String ijenisKelamin,String itanggalLahir,String iwa,String iusername,String iemail,InputStream ifoto){
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

    public static void setFoto(InputStream foto) {
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

    public static Image getFoto() {
        if(foto == null){
            return new Image("file:src/profile/guruBK.png");
        }
        return image;
    }
    
    public static InputStream getInputStreamFoto(){
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

    public static String getFileFoto() {
         if (fileFoto==null||fileFoto.equals("")) {
            return "guruBK";
        }
        return fileFoto;
    }

}