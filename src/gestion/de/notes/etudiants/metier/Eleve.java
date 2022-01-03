 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.metier;

import java.sql.Date;

/**
 *
 * @author M@nU_LP
 */
public class Eleve {
    private int idCla;
    private int idEl;
    private String nomEl;
    private String prenomEl;
    private /*Date*/ String dateNaissEl;
    
    public Eleve() {
    }

    public Eleve(int idEl, int idCla, String nomEl, String prenomEl, /*Date*/String dateNaissEl) {
        this.idEl = idEl;
        this.idCla = idCla;
        this.nomEl = nomEl;
        this.prenomEl = prenomEl;
        this.dateNaissEl = dateNaissEl;
    }
    
    public int getIdCla() {
        return idCla;
    }

    public void setIdCla(int idCla) {
        this.idCla = idCla;
    }

    public int getIdEl() {
        return idEl;
    }

    public void setIdEl(int idEl) {
        this.idEl = idEl;
    }

    public String getNomEl() {
        return nomEl;
    }

    public void setNomEl(String nomEl) {
        this.nomEl = nomEl;
    }

    public String getPrenomEl() {
        return prenomEl;
    }

    public void setPrenomEl(String prenomEl) {
        this.prenomEl = prenomEl;
    }

    public /*Date*/ String getDateNaissEl() {
        return dateNaissEl;
    }

    public void setDateNaissEl(/*Date*/String dateNaissEl) {
        this.dateNaissEl = dateNaissEl;
    }
    
}
