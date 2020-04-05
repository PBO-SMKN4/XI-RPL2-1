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
public class sessionSiswa {
    private static String session;

    public sessionSiswa(String session) {
        sessionSiswa.session = session;
    }
    
    public String getSession(){
        return sessionSiswa.session;
    }
}
