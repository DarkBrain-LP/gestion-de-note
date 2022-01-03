/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants.metier;

import java.util.Date;

/**
 *
 * @author M@nU_LP
 */
public class Composition {
    private Date dataComp;
    private int idEl;
    private int idMat;
    private TypeComposition type;
    private float note;

    public Composition(Date dataComp, TypeComposition type, float note) {
        this.dataComp = dataComp;
        this.type = type;
        this.note = note;
    }

    public int getIdEl() {
        return idEl;
    }

    public void setIdEl(int idEl) {
        this.idEl = idEl;
    }

    public int getIdMat() {
        return idMat;
    }

    public void setIdMat(int idMat) {
        this.idMat = idMat;
    }

    public Date getDataComp() {
        return dataComp;
    }

    public void setDataComp(Date dataComp) {
        this.dataComp = dataComp;
    }

    public TypeComposition getType() {
        return type;
    }

    public void setType(TypeComposition type) {
        this.type = type;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
    
    
}
