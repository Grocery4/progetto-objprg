/** @file Model.java
* La classe rappresenta il back-end dell'applicazione, qui si memorizzano e manipolano i dati immessi o richiesti dall'utente. 
* Nello specifico, la classe modella i dati degli esami e gestisce la tabella da mostrare all'utente tramite l'interfaccia grafica.
* La struttura dati usata per memorizzare gli esami immessi nel programma è una mappa, che utilizza numeri interi positivi come chiave
* per identificarli.
*/
package model;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

import model.esami.*;

public class ModelINUSE {
	private Map<Integer, Esame> mappaEsami;
	private int lastEsameID;
	private DefaultTableModel tableModel;
	
	public ModelINUSE() {
		initializeModel();
	}
	
    /** Inizializza il modello da collegare all'interfaccia grafica. */
	public void initializeModel(){
		mappaEsami = new TreeMap<>();
		setLastEsameID(0);
		int numRows = 0;
		
		
		// inizializza un nuovo modello per la JTable, impedendo la modifica delle celle e inserendo i nomi di ogni colonna.
		String[] columnHeadings = new String[] {"ID", "Matricola", "Nome", "Cognome", "Materia", "CFU", "No. Prove", "Voto Finale", "Lode"};
		tableModel = new DefaultTableModel(numRows, columnHeadings.length) {    
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tableModel.setColumnIdentifiers(columnHeadings);
	}
    
	/** 
     * Restituisce l'esame associato all'ID specificato.
     * Il chiamante del metodo dovrà implementare un modo per distinguere tra EsameSemplice e EsameComposto, 
     * tramite l'utilizzo di if e instanceof.
     * 
     * @param ID L'ID dell'esame da recuperare.
     * @return L'oggetto Esame corrispondente all'ID.
     */
	public Esame getEsame(int ID) {
		//TODO nei metodi implementare: if Obj instanceof EsameSemplice || Obj instanceof EsameComposto 
		//TODO vedere se esistono altri modi di implementare il polimorfismo - se si riesce, utilizzare la classe Esame per implementare metodi astratti
		return mappaEsami.get(ID);
	}
	
	public Map<Integer, Esame> getMappaEsami(){
		return mappaEsami;
	}
	public void setMappaEsami(Map<Integer, Esame> listaEsami){
		this.mappaEsami = listaEsami;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public int getLastEsameID() {
		return lastEsameID;
	}
	public void setLastEsameID(int ID) {
		this.lastEsameID = ID;
	}
	public void incrementLastEsameID() {
		(this.lastEsameID)++;
	}
}
