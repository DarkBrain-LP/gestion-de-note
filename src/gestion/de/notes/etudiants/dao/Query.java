 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package gestion.de.notes.etudiants.dao;

/**
 *
 * @author M@nU_LP
 */
public interface Query {
    public String selectQuery();
    public String insertQuery();
    public String[] getElementsOnTable();
}
