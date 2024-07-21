package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.esami.Esame;
import model.esami.EsameComposto;
import model.esami.EsameSemplice;

public class EsamiTableModel extends AbstractTableModel {
	public final static String[] columnHeadings = new String[] {"ID", "Nome Completo", "Materia", "CFU", "No. Prove", "Voto Finale", "Lode"};
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
				return e.getNomeStudente();
			case 2:
				return e.getNomeInsegnamento();
			case 3:
				return e.getCreditiInsegnamento();
			case 4:
				return (e instanceof EsameSemplice ? 1 : ((EsameComposto) e).getNumeroProveIntermedie());
			case 5:
				return e.getVotoFinale();
			case 6:
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
