/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.dao;
import java.sql.*;
/**
 *
 * @author FIOKLOU
 */
public class ConnectToDB {
    
    Connection con ;
    
    public void insertIntoDb(String table,String val) throws SQLException{
        con=DriverManager.getConnection("jdbc:mysql://localhost/gestion_de_notes","root","");
        PreparedStatement pst = con.prepareStatement("INSERT INTO " + table+ " () VALUES(?)");
        pst.setString(1, val);
        
    }
    public void selectFromDb(){
        
    }
    
    
}
