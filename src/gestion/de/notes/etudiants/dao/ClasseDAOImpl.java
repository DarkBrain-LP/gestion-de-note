
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.dao;

import gestion.de.notes.etudiants.metier.Classe;
import gestion.de.notes.etudiants.metier.Eleve;
import gestion.de.notes.etudiants.metier.Matiere;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M@Un_LP
 */
public class ClasseDAOImpl implements ClasseDAO{

    Connection conn;
    PreparedStatement pst;
    ResultSet rset;

    public ClasseDAOImpl() {
        this.conn = null;
        this.pst = null;
        this.rset = null;
    }
    
    @Override
    public boolean ajouterClasse(Classe classe) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            pst = conn.prepareStatement("INSERT INTO `classe`(nomCla) VALUES(?)");
            pst.setString(1, classe.getNomCla());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return done;
    }

    @Override
    public boolean suppClasse(Classe classe) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            pst = conn.prepareStatement("DELETE FROM `classe` WHERE idCla=?");
            pst.setInt(1, classe.getIdCla());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;
    }

    @Override
    public boolean modifierClasse(Classe classe1, Classe classe2) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("UPDATE `classe` SET nomCla=? WHERE idCla=?");
            pst.setString(1, classe1.getNomCla());
            pst.setInt(2, classe2.getIdCla());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;
    }

    @Override
    public Classe avoirParId(int id) {
        Classe cla = new Classe();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("SELECT * FROM `classe` WHERE idCla=?");
            pst.setInt(1, id);
            
            rset = pst.executeQuery();
            while(rset.next()){
                cla.setIdCla(rset.getInt("idCla"));
                cla.setNomCla(rset.getString("nomCla"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cla;        
    }

    @Override
    public boolean ajouterElevedansClasse(Eleve eleve, Classe classe) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("UPDATE `eleve` SET idCla=? WHERE idEl=?");
            pst.setInt(1, classe.getIdCla());
            pst.setInt(2, eleve.getIdEl());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;        
    }

    @Override
    public boolean ajouterMatiereDansClasse(Matiere mat, Classe classe) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("INSERT INTO `association3` VALUES(?, ?)");
            pst.setInt(1, classe.getIdCla());
            pst.setInt(2, mat.getIdMat());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;         
    }

    @Override
    public List<Classe> listeClasse() {
        ArrayList<Classe> ar = new ArrayList();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("SELECT * FROM `classe`");
            
            rset = pst.executeQuery();
            while(rset.next()){
                Classe cla = new Classe(rset.getInt("idCla"), rset.getString("nomCla"));
                ar.add(cla);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return ar;
    }
    
}
