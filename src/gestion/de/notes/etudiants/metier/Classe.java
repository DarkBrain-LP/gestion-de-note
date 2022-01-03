/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.metier;

/**
 *
 * @author M@nU_LP
 */
public class Classe {
    private int idCla;
    private String nomCla;
    
    public Classe(String nomCla) {
        this.nomCla = nomCla;
    }

    public Classe() {
    }

    public Classe(int idCla, String nomCla) {
        this.idCla = idCla;
        this.nomCla = nomCla;
    }

    public int getIdCla() {
        return idCla;
    }

    public void setIdCla(int idCla) {
        this.idCla = idCla;
    }

    public String getNomCla() {
        return nomCla;
    }

    public void setNomCla(String nomCla) {
        this.nomCla = nomCla;
    }
 
}
