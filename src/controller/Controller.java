/** @file Controller.java 
*  Questa classe funge da intermediario tra la View e il Model, gestendo la logica dell'applicazione e coordinando le interazioni.
*/

package controller;

import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Model;
import model.esami.Esame;
import model.esami.EsameComposto;
import model.esami.EsameSemplice;
import view.View;
import view.components.EsamiTable;
import view.errors.ExistingEntryException;


/**
 * La classe Controller è responsabile della comunicazione tra View e Model.
 */

//TODO vedere se in ogni metodo Map<I,E> mappaEsami può essere sostituito da un eventuale attributo di classe, piuttosto che ridichiararlo ogni volta
public class Controller {
	private Model model;
	private View view;
	
	//TODO sistemare dove posizionare i TRY/CATCH e valutare se mettere THROWS in Model.java
	public void aggiungiEsame(Esame esame) throws ExistingEntryException {
		if(esame instanceof EsameSemplice) {esame = (EsameSemplice) esame;}
		if(esame instanceof EsameComposto) {esame = (EsameComposto) esame;}
		
		Map<Integer, Esame> mappaEsami = model.getMappaEsami();
		DefaultTableModel tableModel = model.getTableModel();
		int lastEsameID = model.getLastEsameID();
		
		CheckDuplicatesOnAdd(mappaEsami, esame);
			
		esame.setID(lastEsameID);
		tableModel.addRow(esame.toColumns());
		model.incrementLastEsameID();

		mappaEsami.put(esame.getID(), esame);
	}
	public void modificaEsame(Esame esameModificato) throws ExistingEntryException {
		if(esameModificato instanceof EsameSemplice) {esameModificato = (EsameSemplice) esameModificato;}
		if(esameModificato instanceof EsameComposto) {esameModificato = (EsameComposto) esameModificato;}
		
		int ID = getView().getTable().getSelectedRow();
		Map<Integer, Esame> mappaEsami = model.getMappaEsami();
		DefaultTableModel tableModel = model.getTableModel();
			
		checkDuplicatesOnUpdate(mappaEsami, esameModificato);
		mappaEsami.put(ID, esameModificato);
		aggiornaTabella(ID, tableModel, esameModificato);
	}
	/** L'eliminazione di un esame tramite il metodo eliminaEsame() non implica lo scalo di 
	* lastEsameID degli altri esami successivi, né un decremento di lastEsameID. 
	*/ 
	public void eliminaEsame(int ID) {
		Map<Integer, Esame> mappaEsami = model.getMappaEsami();
		mappaEsami.remove(ID);
	}
	
	public void rimuoviRiga() {
		EsamiTable table = view.getTable();
		DefaultTableModel tableModel = model.getTableModel();
		
		if(table.getSelectedRow() != -1) {
			
			//elimina esame dalla lista
			int column = 0; //ID column
			int row = table.getSelectedRow();
			int ID = Integer.parseInt(tableModel.getValueAt(row, column).toString());
			eliminaEsame(ID);
			
			//elimina esame dal JTable
			tableModel.removeRow(table.getSelectedRow());
           JOptionPane.showMessageDialog(null, "Rimozione avvenuta con successo!");
        }
	}
	public void modificaRiga() {
		
	}
	public void filtraTabella() {
		
	}
	
	/** Aggiorna i dati stampati sulla tabella con le nuove informazioni ottenute. */
	private void aggiornaTabella(int ID, DefaultTableModel tableModel, Esame esameModificato){
		tableModel.setValueAt(esameModificato.getMatricola(), ID, 1);
		tableModel.setValueAt(esameModificato.getNome(), ID, 2);
		tableModel.setValueAt(esameModificato.getCognome(), ID, 3);
		tableModel.setValueAt(esameModificato.getNomeInsegnamento(), ID, 4);
		tableModel.setValueAt(esameModificato.getCreditiInsegnamento(), ID, 5);
		tableModel.setValueAt(esameModificato.getVotoFinale(), ID, 7);
		tableModel.setValueAt(esameModificato.lodeToString(), ID, 8);
	}
	/** Controlla che non si stiano inserendo dei dati duplicati. 
	* Se la chiave valutata è diversa dalla chiave dell'esame da modificare E l'esame in mappaEsami ha stessa matricola e materia,
	* allora l'esame è già stato sostenuto.
	* 
	* @exception ExistingEntryException Lancia un errore in caso l'esame sia già presente.
	*/
	private void checkDuplicatesOnUpdate(Map<Integer, Esame> mappaEsami, Esame esameModificato) throws ExistingEntryException {
		for(int key : mappaEsami.keySet()) {
			if((!(key == esameModificato.getID())) && mappaEsami.get(key).equals(esameModificato)){
				{throw new ExistingEntryException("lo studente ha già sostenuto l'esame!");}
			}
		}
	}
	/** Controlla che non si stiano inserendo dei dati duplicati.
	* Al contrario di checkDuplicatesOnUpdate, in questo metodo non c'è bisogno di controllare se la chiave immessa (ID) è già presente, 
	* perché prima di assegnarla all'esame da aggiungere, viene incrementata automaticamente. 
	* 
	* @exception ExistingEntryException Lancia un errore in caso l'esame sia già presente.
	*/
	private void CheckDuplicatesOnAdd(Map<Integer, Esame> mappaEsami, Esame esame) throws ExistingEntryException {
		for(int key : mappaEsami.keySet()) {
			if (mappaEsami.get(key).equals(esame)) {throw new ExistingEntryException("lo studente ha già sostenuto l'esame!");}
		}
	}
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}

	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
}
