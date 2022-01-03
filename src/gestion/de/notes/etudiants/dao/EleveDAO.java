/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.dao;

import gestion.de.notes.etudiants.metier.*;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author M@nU_LP
 */
public interface EleveDAO {
    public boolean ajouterEleve(Eleve eleve);
    public boolean suppEleve(Eleve eleve);
    public boolean modifierEleve(Eleve eleve1, Eleve eleve2);
    public Eleve avoirAvecId(int id);
    public boolean saisirNote(Eleve el, Matiere mat, float note, TypeComposition comp);
    public int afficherRang(int idEleve);
    public float calculerMoy(Eleve el, Matiere mat);
    public float calculerMoyGen(int idEleve);
    public HashMap<String, Float> etatMoy(Eleve el);
    public List<Eleve> listeEleve();
}
