/** @file Model.java
* La classe rappresenta la parte back-end dell'applicazione, qui si memorizzano e manipolano i dati immessi o richiesti dall'utente. 
* La struttura dati usata per memorizzare gli esami immessi nel programma è una ArrayList di lunghezza variabile, che utilizza numeri interi positivi come indice
* per identificarli.
*/

package model;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.esami.Esame;

//TODO add documentation
public class ModelNEW {
	//TODO move to whatever class handles the JTable, probably Controller.java
	private DefaultTableModel tableModel;
	
	private ArrayList<Esame> esamiList;
	private int lastId;
	
	public ModelNEW() {
		initEsamiList();
		
		int initialRows = 0;
		tableModel = new DefaultTableModel();
	}
	
	/**
	 * Inizializza la struttura dati su cui si basa l'applicazione.
	 * Si crea una nuova lista di esami e si inizializza l'id a 0.
	 */
	public void initEsamiList() {
		esamiList = new ArrayList<>();
		lastId = 0;
	}
	
	/**
	 * Aggiunge un esame all'interno della lista e incrementa di uno l'Id per le prossime entry.
	 * 
	 * @param esame L'esame da aggiungere all'interno della lista.
	 * @return true se l'esame viene aggiunto correttamente alla lista, false se l'esame inserito è già presente.
	 */
	public boolean addToList(Esame esame) {
		if(findEsameInList(esame) == -1) {return false;}
		
		esame.setID(getLastId());
		esamiList.add(lastId, esame);
		incrementLastID();
		return true;
	}
	
	public void removeFromList(int ID) {
		esamiList.remove(ID);
	}
	
	public void editInList(int ID, Esame e) {
		esamiList.add(ID, e);
	}
	
	public Esame getEsame(int ID) {
		return esamiList.get(ID);
	}

	/**
	 * Controlla la presenza dell'esame all'interno della lista confrontandolo uno per uno con ogni elemento.
	 * In caso di grandi quantità di dati, il metodo risulta non essere efficiente.
	 * 
	 * @param e Esame per cui si controlla la presenza all'interno della lista.
	 * @return indice dell'esame se presente, -1 altrimenti.
	 */
	private int findEsameInList(Esame e) {
		for(Esame e1 : esamiList) {
			if(e1.equals(e)) {return e1.getID();}
		}
		return -1;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public ArrayList<Esame> getEsamiList() {
		return esamiList;
	}
	public void setEsamiList(ArrayList<Esame> esamiList) {
		this.esamiList = esamiList;
	}

	public int getLastId() {
		return this.lastId;
	}
	public void setLastId(int id) {
		this.lastId = id;
	}
	private void incrementLastID() {
		(this.lastId)++;
	}
	
}
