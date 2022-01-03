/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.metier;


/**
 *
 * @author FIOKLOU
 */
public class Matiere {
    private int idMat=1;
    private String nomMat;
    private int coefMat = 1;

    public Matiere(int idMat, String nomMat, int coefMat) {
        this.idMat = idMat;
        this.nomMat = nomMat;
        this.coefMat = coefMat;
    }
    
    // renvoie les attributs sous forme de tableau
    public String[] getElementsOnTable(){
        String[] tab = new String[2];
        tab[0] = this.getNomMat();
        tab[1] = String.valueOf(this.getCoefMat());
        
        return tab;
    }
    
    
    public int getIdMat() {
        return idMat;
    }

    public Matiere( String nomMat, int coefMat) {
        this.nomMat = nomMat;
        this.coefMat = coefMat;
    }

    public Matiere() {
    }

    public String getNomMat() {
        return nomMat;
    }

    public int getCoefMat() {
        return coefMat;
    }

    public void setIdMat(int idMat) {
        this.idMat = idMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    public void setCoefMat(int coefMat) {
        this.coefMat = coefMat;
    }
    
    
    
}
