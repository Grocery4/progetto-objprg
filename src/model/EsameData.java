/**
 * @file EsameData.java
 * 
 * La classe EsameData funge da wrapper per serializzare l'array di oggetti Esame e l'ultimo ID utilizzato.
 */

package model;

import java.io.Serializable;

import model.esami.Esame;

public class EsameData implements Serializable {
	private static final long serialVersionUID = 1L;
    private Esame[] esamiList;
    private int lastId;

    public EsameData(Esame[] esamiSaved, int lastId) {
        this.esamiList = esamiSaved;
        this.lastId = lastId;
    }

    public Esame[] getEsamiList() {
        return esamiList;
    }

    public int getLastId() {
        return lastId;
    }
}