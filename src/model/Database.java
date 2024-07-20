/** 
 * @file Database.java
 * 
* La classe rappresenta la parte back-end dell'applicazione, qui si memorizzano e manipolano i dati immessi o richiesti dall'utente. 
* La struttura dati usata per memorizzare gli esami immessi nel programma è una ArrayList di lunghezza variabile, che utilizza numeri interi positivi come indice
* per identificarli.
*/

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.esami.Esame;

public class Database {
	private List<Esame> esamiList;
	private int lastId;
	
	/**
	 * Inizializza la struttura dati su cui si basa l'applicazione.
	 * Si crea una nuova lista di esami e si inizializza l'id a 0.
	 */
	public Database() {
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
		if(getEsameIndex(esame) != -1) {return false;}
		
		esame.setID(getLastId());
		esamiList.add(esame);
		incrementLastID();
		return true;
	}
	
	/**
	 * Metodo per l'eliminazione dell'esame dalla lista.
	 * Controlla ogni esame all'interno della lista e compara l'ID con quello preso in input.
	 * Non elimina utilizzando direttamento l'ID preso in input in quanto corrisponde ID dell'oggetto esame
	 * e non all'indice dell'elemento all'interno dell'ArrayList.
	 * 
	 * @param ID Id dell'esame da eliminare.
	 */
	public boolean removeFromList(int idEsame) {
		Esame e = findEsameById(idEsame);
		if(e == null) {return false;}
		
		esamiList.remove(e);
		return true;
	}
	
	/**
	 * Metodo per l'aggiornamento di un elemento appartenente alla lista.
	 * Identifica l'elemento da modificare con il nuovo esame.
	 * 
	 * @param ID id dell'esame da modificare.
	 * @param e Nuovo esame da inserire nella lista.
	 * @return true se l'operazione ha avuto successo, false altrimenti.
	 */
	public boolean editInList(int idEsame, Esame e) {
		Esame esameInList = findEsameById(idEsame);
		
		if(esameInList == null) {return false;}

		esameInList = e;
		return true;
	}
	
	/**
	 * Metodo per recuperare uno specifico esame dalla lista.
	 * 
	 * @param ID Identificatore dell'esame.
	 * @return Esame identificato da id.
	 */
	public Esame findEsameById(int idEsame) {
		for(Esame e : esamiList) {
			if(e.getID() == idEsame) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Salva l'attuale lista di esami (esamiList) nel file specificato.
	 *
	 * @param file Il file in cui verranno salvati gli esami
	 * @throws IOException
	 */
	public void saveOnFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Esame[] esamiSaved = esamiList.toArray(new Esame[esamiList.size()]);
		oos.writeObject(esamiSaved);
		
		oos.close();
		fos.close();
	}
	
	/**
	 * Carica una lista di esami dal file specificato e aggiorna l'attuale lista (esamiList).
	 *
	 * @param file Il file da cui verranno caricati gli esami
	 * @throws IOException
	 */
	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Esame[] esamiLoaded = (Esame[]) ois.readObject();
			
			esamiList.clear();
			esamiList.addAll(Arrays.asList(esamiLoaded));
			
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Controlla la presenza dell'esame all'interno della lista confrontandolo uno per uno con ogni elemento.
	 * 
	 * @param e Esame per cui si controlla la presenza all'interno della lista.
	 * @return Posizione dell'esame all'interno dell'ArrayList se presente, -1 altrimenti.
	 */
	private int getEsameIndex(Esame e) {
		for(int index = 0; index < esamiList.size(); index++) {
			if(esamiList.get(index).equals(e)) {return index;}
		}
		return -1;
	}
	
	public List<Esame> getEsamiList() {
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
