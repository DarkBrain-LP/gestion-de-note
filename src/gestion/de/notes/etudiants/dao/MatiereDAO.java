/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.dao;

import gestion.de.notes.etudiants.metier.*;
import java.util.List;

/**
 *
 * @author M@nU_LP
 */
public interface MatiereDAO {
    public boolean ajouterMatiere(Matiere mat);
    public boolean suppMatiere(Matiere mat);
    public boolean modifierMatiere(Matiere mat1, Matiere mat2); // Remplace la matiere mat2 
    public Matiere avoirAvecId(int id);
    public List<Matiere> listeMatiere();
}
