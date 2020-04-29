/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.pkg4labs.controller.siswa.MyConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author Diazs
 */
public class PesanCurhat {
    private String idPesan;
    private String isiPesan;
    private String waktuKirim;
    private String idPengirim;
    private String dilihat;
    private String namaPengirim;

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }
    
    public void setIdPesan(String idPesan) {
        this.idPesan = idPesan;
    }
    
    public void setIdPengirim(String idPengirim) {
            this.idPengirim = idPengirim;
        }
    public void setIsiPesan(String isiPesan) {
        this.isiPesan = isiPesan;
    }

    public void setWaktuKirim(String waktuKirim) {
        this.waktuKirim = waktuKirim;
    }

    public void setDilihat(String dilihat) {
        this.dilihat = dilihat;
    }

    public String getIdPesan() {
        return idPesan;
    }

    public String getIsiPesan() {
        return isiPesan;
    }

    public String getWaktuKirim() {
        return waktuKirim;
    }

    public String getDilihat() {
        return dilihat;
    }

    public String getIdPengirim() {
        return idPengirim;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }
    
    
    public void setSudahDilihat(){
        Connection koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
        try {
            Statement stmt = koneksi.createStatement();
            String sql = "UPDATE curhat SET status_dilihat = 'sudah' WHERE id_curhat = '"+idPesan+"'";
            int berhasil = stmt.executeUpdate(idPesan);
            if (berhasil != 1) {
                JOptionPane.showMessageDialog(null, "Gagal Di Update");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesanCurhat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
