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
public interface ClasseDAO {
    public boolean ajouterClasse(Classe classe);
    public boolean suppClasse(Classe classe);
    public boolean modifierClasse(Classe classe1, Classe classe2);
    public Classe avoirParId(int id);
    public boolean ajouterElevedansClasse(Eleve eleve, Classe classe);
    public boolean ajouterMatiereDansClasse(Matiere mat, Classe classe);
    public List<Classe> listeClasse();
}
