/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pkg4labs.model;

/**
 *
 * @author Diazs
 */
public class PesanCurhat {
    private String idPesan;
    private String isiPesan;
    private String waktuKirim;
    private String idPengirim;

    
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

    public String getIdPesan() {
        return idPesan;
    }

    public String getIsiPesan() {
        return isiPesan;
    }

    public String getWaktuKirim() {
        return waktuKirim;
    }

    public String getIdPengirim() {
        return idPengirim;
    }
    
}
