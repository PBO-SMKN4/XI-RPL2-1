/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guest;

/**
 *
 * @author Diazs
 */
public class SessionGuest {
    private static String email;
    private static String idToDetPoster;

    public static void setEmail(String email) {
        SessionGuest.email = email;
    }

    public static String getIdToDetPoster() {
        return idToDetPoster;
    }

    public static void setIdToDetPoster(String idToDetPoster) {
        SessionGuest.idToDetPoster = idToDetPoster;
    }

    public static String getEmail() {
        return email;
    }
    
}
