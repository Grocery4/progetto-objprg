package controller;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;

import model.ModelNEW;
import model.esami.Esame;
import view.View;

public class ControllerNEW {
	private ModelNEW model;
	private View view;
	
	public void addEsame(Esame e) {
		model.addToList(e);
	}
	
	public void removeEsame(int ID) {
		model.removeFromList(ID);
	}
	
	public void editEsame(int ID, Esame e) {
		model.editInList(ID, e);
	}
	
	public void filtraTabella(String query, String filterType) {
		if (query.isBlank()) {return;} 

		int columnID = 0;
		if(filterType == "Matricola") {
			columnID = 1;
		} else if(filterType == "Materia") {
			columnID = 4;
		}
		view.getTable().getSorter().setRowFilter(RowFilter.regexFilter("(?i)^" + query + "$", columnID));
	}
	
	/** stampaTabella() utilizza il metodo JTable.print() per stampare la tabella, e
	 * attraverso un try/catch mostra il risultato dell'operazione sottoforma di finestra di dialogo.
	 */
	public void stampaTabella() {
		try {
            boolean status = view.getTable().print(JTable.PrintMode.FIT_WIDTH, null, null);
            if (status) {
                JOptionPane.showMessageDialog(null, "Stampa completata", "Risultato stampa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Stampa annullata", "Risultato stampa", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception pe) {
            System.err.println(pe.getMessage());
        }
	}
	
	public ModelNEW getModel() {
		return model;
	}
	public void setModel(ModelNEW model) {
		this.model = model;
	}
	
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
}
