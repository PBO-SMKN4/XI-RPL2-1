/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.controller.siswa;

/**
 *
 * @author Muhammad Fahru Rozi
 */
public class sessionId {
    private static String idToDetailJadwal;
    private static String idToPertanyaan;
    private static String idToDetailInfo;
    
    public static String getIdToDetailJadwal() {
        return idToDetailJadwal;
    }

    public static void setIdToDetailJadwal(String idToDetailJadwal) {
        sessionId.idToDetailJadwal = idToDetailJadwal;
    }
    
    public static String getIdToPertanyaan() {
        return idToPertanyaan;
    }

    public static void setIdToPertanyaan(String idToPertanyaan) {
        sessionId.idToPertanyaan = idToPertanyaan;
    }

    public static void setIdToDetailInfo(String idToDetailInfo) {
        sessionId.idToDetailInfo = idToDetailInfo;
    }

    public static String getIdToDetailInfo() {
        return idToDetailInfo;
    }
}