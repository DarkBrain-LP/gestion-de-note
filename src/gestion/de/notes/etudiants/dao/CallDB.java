/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FIOKLOU
 */
public class CallDB {
    private Connection con;
    
    public boolean insertIntoDb(String query, String[] queryParameters) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/gestion_note_ecole","root","");
            //String query = "INSERT INTO `matiere`(nomMat, coefMat) VALUES(?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            
            for(int i = 0; i < queryParameters.length; i ++)
                pst.setString(i + 1, queryParameters[i]);
            
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
        //    Logger.getLogger(BDMatiere.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 

    public String selectFromDb(String table, String[] forPrint){
        try {
            Statement st = con.createStatement();
            ResultSet rs =  st.executeQuery("SELECT * FROM " + table);
            String result = "";
            System.out.println("******************************************************");
            while(rs.next()){
                for(String s : forPrint){
                    result += "| " + rs.getString(s) + "\t";
                    System.out.print("| " + rs.getString(s) + "\t");
                }
                result += "\n";
                System.out.println("\n******************************************************");
                //System.out.println(rs.getString("idMat")+ "-" + rs.getString("nomMat"));
            }
        return result;
        } catch (SQLException ex) {
        //    Logger.getLogger(BDMatiere.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de connexion à laybase de données");
            return "";
        }
    }

    
}
