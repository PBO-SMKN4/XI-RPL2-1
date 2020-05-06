/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

/**
 *
 * @author Diazs
 */
public class SessionSiswa {
    private static String session;

    public static void setSession(String session) {
        SessionSiswa.session = session;
    }

    public static String getSession(){
        return SessionSiswa.session;
    }
}