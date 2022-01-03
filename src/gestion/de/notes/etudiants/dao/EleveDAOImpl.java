/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.dao;

import gestion.de.notes.etudiants.metier.Classe;
import gestion.de.notes.etudiants.metier.Eleve;
import gestion.de.notes.etudiants.metier.Matiere;
import gestion.de.notes.etudiants.metier.TypeComposition;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M@nU_LP
 */
public class EleveDAOImpl implements EleveDAO {

    Connection conn;
    PreparedStatement pst;
    ResultSet rset;
    
    @Override
    public boolean ajouterEleve(Eleve eleve) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            pst = conn.prepareStatement("INSERT INTO `eleve`(nomEl, prenomEl, datNaissEl) VALUES(?,?,?)");
            pst.setString(1, eleve.getNomEl());
            pst.setString(2, eleve.getPrenomEl());
            pst.setDate(3, Date.valueOf(eleve.getDateNaissEl())); // Conversion de la date de type String en format de date SQL
            //pst.setDate(3, eleve.getDateNaissEl());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return done;
    }

    @Override
    public boolean suppEleve(Eleve eleve) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            pst = conn.prepareStatement("DELETE FROM `eleve` WHERE idEl=?");
            pst.setInt(1, eleve.getIdEl());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return done;
    }

    @Override
    public boolean modifierEleve(Eleve eleve1, Eleve eleve2) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("UPDATE `eleve` SET nomEl=?, prenomEl=?, datNaissEl=? WHERE idEl=?");
            pst.setString(1, eleve1.getNomEl());
            pst.setString(2, eleve1.getPrenomEl());
            pst.setString(3, eleve1.getDateNaissEl());
            pst.setInt(4, eleve2.getIdCla());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return done;
    }

    @Override
    public Eleve avoirAvecId(int id) {
        Eleve el = new Eleve();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("SELECT * FROM `eleve` WHERE idEl=?");
            pst.setInt(1, id);
            
            rset = pst.executeQuery();
            while(rset.next()){
                el.setIdEl(rset.getInt("idEl"));
                el.setNomEl(rset.getString("nomEl"));
                el.setPrenomEl(rset.getString("prenomEl"));
                el.setDateNaissEl(rset.getString("datNaissEl"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return el;        
    }

    @Override
    public boolean saisirNote(Eleve el, Matiere mat, float note, TypeComposition comp) {
        boolean done = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            pst = conn.prepareStatement("INSERT INTO `composition` VALUES(?,?,?,?)");
            
            pst.setInt(1, el.getIdEl());
            pst.setInt(2, mat.getIdMat());
            pst.setFloat(3, note);
            pst.setString(4, comp.name());
            
            pst.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return done;
    }

    @Override
    public int afficherRang(int idEleve) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float calculerMoy(Eleve el , Matiere mat) {
        float moyenne = 0;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("SELECT note FROM `composition` WHERE idEl=? AND idMat=?");
            pst.setInt(1, el.getIdEl());
            pst.setInt(2, mat.getIdMat());
            
            rset = pst.executeQuery();
            while(rset.next()){
                moyenne += rset.getDouble("note");
            }
            
            moyenne *= (mat.getCoefMat() / 2);
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return moyenne;
    }

    @Override
    public float calculerMoyGen(int idEleve) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Float> etatMoy(Eleve el) {
        String result = "";
        ArrayList<Matiere> matList = (ArrayList<Matiere>) new MatiereDAOImpl().listeMatiere();
        HashMap<String, Float> hm = new HashMap();
        
        matList.forEach((mat) -> {
            hm.put(mat.getNomMat(), this.calculerMoy(el, mat));
        });
        
        return hm;
    }

    @Override
    public List<Eleve> listeEleve() {
        ArrayList<Eleve> ar = new ArrayList();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole", "root", "");
            
            pst = conn.prepareStatement("SELECT * FROM `eleve`");
            
            rset = pst.executeQuery();
            while(rset.next()){
                Eleve el = new Eleve(rset.getInt("idEl"), rset.getInt("idCla"), rset.getString("nomEl"),
                                    rset.getString("prenomEl"), rset.getString("datNaissEl"));
                ar.add(el);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EleveDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return ar;
    }
    
}
