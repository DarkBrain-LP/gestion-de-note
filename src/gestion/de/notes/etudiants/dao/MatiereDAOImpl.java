/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.dao;

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
 * @author M@nU_LP
 */
public class MatiereDAOImpl implements MatiereDAO {
    Connection conn;
    PreparedStatement pst;
    ResultSet rset;
    @Override
    public boolean ajouterMatiere(Matiere mat) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            pst = conn.prepareStatement("INSERT INTO `matiere`(nomMat) VALUES(?)");
            pst.setString(1, mat.getNomMat());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return done;        
    }

    @Override
    public boolean suppMatiere(Matiere mat) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            pst = conn.prepareStatement("DELETE FROM `matiere` WHERE idMat=?");
            pst.setInt(1, mat.getIdMat());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return done;
    }

    @Override
    public boolean modifierMatiere(Matiere mat1, Matiere mat2) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("UPDATE `matiere` SET nomMat=?, coefMat=? WHERE idMat=?");
            pst.setString(1, mat1.getNomMat());
            pst.setInt(2, mat1.getCoefMat());
            pst.setInt(3, mat2.getIdMat());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return done;
    }

    @Override
    public Matiere avoirAvecId(int id) {
        Matiere mat = new Matiere();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("SELECT * FROM `matiere` WHERE idMat=?");
            pst.setInt(1, id);
            
            rset = pst.executeQuery();
            while(rset.next()){
                mat.setIdMat(rset.getInt("idMat"));
                mat.setNomMat(rset.getString("nomMat"));
                mat.setCoefMat(rset.getInt("coefMat"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mat;        
    }

    @Override
    public List<Matiere> listeMatiere() {
        ArrayList<Matiere> ar = new ArrayList();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("SELECT * FROM `matiere`");
            
            rset = pst.executeQuery();
            while(rset.next()){
                Matiere mat = new Matiere(rset.getInt("idMat"), rset.getString("nomMat"), rset.getInt("coefMat"));
                ar.add(mat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MatiereDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return ar;
    }
    
}
