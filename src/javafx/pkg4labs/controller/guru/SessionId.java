/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.guru;

/**
 *
 * @author Diazs
 */
public class SessionId {
    private static String id;
    private static int idInt;
    private static String idToWali;
    private static String idToDetPoster;

    public static String getIdToDetPoster() {
        return idToDetPoster;
    }

    public static void setIdToDetPoster(String idToDetPoster) {
        SessionId.idToDetPoster = idToDetPoster;
    }

    public static void setIdInt(int idInt) {
        SessionId.idInt = idInt;
    }

    public static int getIdInt() {
        return idInt;
    }
    
    public static void setId(String id) {
        SessionId.id = id;
    }

    public static String getId() {
        return id;
    }

    public static String getIdToWali() {
        return idToWali;
    }

    public static void setIdToWali(String idToWali) {
        SessionId.idToWali = idToWali;
    }


}
