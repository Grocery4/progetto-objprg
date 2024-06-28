/** @file Model.java */

package model;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.esami.*;
import view.errors.ExistingEntryException;

public class Model {
	private Map<Integer, Esame> mappaEsami;
	private int lastEsameID;
	private DefaultTableModel tableModel;
	
	public Model() {
		initializeModel();
	}
	
	public void initializeModel(){
		mappaEsami = new TreeMap<>();
		setLastEsameID(0);
		int numRows = 0;
		
		String[] columnHeadings = new String[] {"ID", "Matricola", "Nome", "Cognome", "Materia", "CFU", "No. Prove", "Voto Finale", "Lode"};
		
		/** inizializzo un nuovo modello per la JTable in cui le celle non sono modificabili. */
		tableModel = new DefaultTableModel(numRows, columnHeadings.length) {    
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tableModel.setColumnIdentifiers(columnHeadings);
	}
	
	public Esame getEsame(int ID) {
		return mappaEsami.get(ID);
		//TODO nei metodi implementare: if Obj instanceof EsameSemplice || Obj instanceof EsameComposto
		//TODO vedere se esistono altri modi di implementare il polimorfismo
	}
	
	public Map<Integer, Esame> getMappaEsami(){
		return mappaEsami;
	}
	public void setListaEsami(Map<Integer, Esame> listaEsami){
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
