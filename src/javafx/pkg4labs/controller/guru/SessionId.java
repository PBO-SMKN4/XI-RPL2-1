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
    private static String idToWali;

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
    
