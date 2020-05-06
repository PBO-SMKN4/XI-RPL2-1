/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.pkg4labs.controller.siswa.MyConnection;

/**
 *
 * @author Muhammad Fahru Rozi
 */
public class ListInfoTugasBerita {
    private ArrayList<InfoTugasBerita> infoTugasBerita;
    private Connection koneksi;
    private Statement stmt;
    private ResultSet res;
    private String sql;
    private InfoTugasBerita info;
    
    public ListInfoTugasBerita(){
        infoTugasBerita = new ArrayList<>();
        koneksi = MyConnection.getKoneksi("localhost", "3306", "root", "", "project_java");
    }
}
