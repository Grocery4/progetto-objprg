package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.esami.Esame;
import model.esami.EsameComposto;
import model.esami.EsameSemplice;

public class EsamiTableModel extends AbstractTableModel {
	public final static String[] columnHeadings = new String[] {"ID", "Matricola", "Nome", "Cognome", "Materia", "CFU", "No. Prove", "Voto Finale", "Lode"};
	private List<Esame> esamiList;
	
	public EsamiTableModel(List<Esame> esamiList) {
		setEsamiList(esamiList);
	}
	
	@Override
	public int getRowCount() {
		return esamiList.size();
	}

	@Override
	public int getColumnCount() {
		return columnHeadings.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Esame e = esamiList.get(rowIndex);
		
		switch(columnIndex) {
			case 0:
				return e.getID();
			case 1:
				return e.getMatricola();
			case 2:
				return e.getNome();
			case 3:
				return e.getCognome();
			case 4:
				return e.getNomeInsegnamento();
			case 5:
				return e.getCreditiInsegnamento();
			case 6:
				return (e instanceof EsameSemplice ? 1 : ((EsameComposto) e).getNumeroProveIntermedie());
			case 7:
				return e.getVotoFinale();
			case 8:
				return e.isLode();
			default:
				return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnHeadings[column];
	}

	public List<Esame> getEsamiList() {
		return esamiList;
	}
	public void setEsamiList(List<Esame> esamiList) {
		this.esamiList = esamiList;
	}
}
