/** @file Controller.java */

package controller;

import java.util.List;
import java.util.Map;

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

//TODO vedere se i metodi in Model devono essere spostati in Controller
//TODO vedere se in ogni metodo Map<I,E> mappaEsami può essere sostituito da un eventuale attributo di classe, piuttosto che ridichiararlo ogni volta
public class Controller {
	private Model model;
	private View view;
	
	//TODO sistemare dove posizionare i TRY/CATCH e valutare se mettere THROWS in Model.java
	public void aggiungiEsame(Esame esame) throws ExistingEntryException{
		if(esame instanceof EsameSemplice) {esame = (EsameSemplice) esame;}
		if(esame instanceof EsameComposto) {esame = (EsameComposto) esame;}
		
		Map<Integer, Esame> mappaEsami = model.getMappaEsami();
		DefaultTableModel tableModel = model.getTableModel();
		int lastEsameID = model.getLastEsameID();
		
		//qua non c'è bisogno di controllare se la chiame immessa (ID) è già presente, perché viene incrementata automaticamente 
		for(int key : mappaEsami.keySet()) {
			if (mappaEsami.get(key).equals(esame)) {throw new ExistingEntryException("lo studente ha già sostenuto l'esame!");}
		}
			
		esame.setID(lastEsameID);
		tableModel.addRow(esame.toColumns());
		model.incrementLastEsameID();

		mappaEsami.put(esame.getID(), esame);
	}
	//TODO implementare metodo per modificare un esame con un certo ID
	//TODO accettare sia EsameSemplice che EsameComposto
	public void modificaEsame(Esame esameModificato) throws ExistingEntryException {
		if(esameModificato instanceof EsameSemplice) {esameModificato = (EsameSemplice) esameModificato;}
		if(esameModificato instanceof EsameComposto) {esameModificato = (EsameComposto) esameModificato;}
		
		int ID = getView().getTable().getSelectedRow();
		Map<Integer, Esame> mappaEsami = model.getMappaEsami();
		DefaultTableModel tableModel = model.getTableModel();
		
			for(int key : mappaEsami.keySet()) {
				//se la chiave valutata è diversa dalla chiave dell'esame da modificare E l'esame in mappaEsami ha stessa matricola e materia
				//allora l'esame è già stato sostenuto
				if((!(key == esameModificato.getID())) && mappaEsami.get(key).equals(esameModificato)){
					{throw new ExistingEntryException("lo studente ha già sostenuto l'esame!");}
				}
			}
			mappaEsami.put(ID, esameModificato);
			tableModel.setValueAt(esameModificato.getMatricola(), ID, 1);
			tableModel.setValueAt(esameModificato.getNome(), ID, 2);
			tableModel.setValueAt(esameModificato.getCognome(), ID, 3);
			tableModel.setValueAt(esameModificato.getNomeInsegnamento(), ID, 4);
			tableModel.setValueAt(esameModificato.getCreditiInsegnamento(), ID, 5);
			tableModel.setValueAt(esameModificato.getVotoFinale(), ID, 7);
			tableModel.setValueAt(esameModificato.lodeToString(), ID, 8);
	}
	//eliminare un esame non implica lo scalo di lastEsameID degli altri esami successivi, né un decremento di lastEsameID 
	public void eliminaEsame(int ID) {
		Map<Integer, Esame> mappaEsami = model.getMappaEsami();
		mappaEsami.remove(ID);
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
