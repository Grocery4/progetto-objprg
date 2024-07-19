package controller;

import java.awt.Dialog.ModalityType;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Database;
import model.esami.Esame;
import view.View;
import view.components.ColumnHeaders;
import view.errors.ExistingEntryException;

public class Controller {
	private Database database;
	private View view;
	
	public void addEsame(Esame e) {
		database.addToList(e);
		view.getTable().updateTable();
	}
	
	public void removeEsame(int ID) {
		database.removeFromList(ID);
		view.getTable().updateTable();
		JOptionPane.showMessageDialog(null, "Rimozione avvenuta con successo!");
	}
	
	//TODO FINISH AND TEST METHOD
	public void editEsame(Esame e) throws ExistingEntryException {
		int ID = (int) view.getTableModel().getValueAt(view.getTable().getSelectedRow(), ColumnHeaders.ID.ordinal());
		database.editInList(ID, e);
		view.getTable().updateTable();
	}
	
	/**
	 * Il seguente metodo filtra i dati nella tabella in base alla query e al tipo di filtro specificati.
	 *
	 * @param query      La stringa di ricerca utilizzata per filtrare i dati nella tabella. 
	 *                   Se la query è vuota o contiene solo spazi, il metodo non applica alcun filtro.
	 * @param filterType Il tipo di filtro utilizzato per determinare quale colonna della tabella deve essere filtrata.
	 *                   Questo parametro è convertito in un identificatore di colonna tramite il metodo toEnum.
	 */
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
	
	/**
	 * Rimuove il filtro applicato alla tabella.
	 */
	public void resetFilter() {
		view.getTable().getSorter().setRowFilter(null);
	}
	
	public float calcolaVotoMedio() {
		float sommaProdotti;
		int cfuTotali;
		sommaProdotti = cfuTotali = 0;
		
		for(int i=0; i < view.getTableModel().getRowCount(); i++) {
			if (view.getTable().getSorter().convertRowIndexToView(i) != -1) {
				//if visible
				float votoFinale; 
				int cfu;
				votoFinale = (Float) view.getTableModel().getValueAt(i, ColumnHeaders.VOTOFINALE.ordinal());
				cfu = (Integer) view.getTableModel().getValueAt(i, ColumnHeaders.CFU.ordinal());
				
				sommaProdotti += votoFinale * cfu;
				cfuTotali += cfu;
			}
		}
		
		return sommaProdotti / cfuTotali;
	}
	
	public void mostraStatistiche(int col, String category) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(int row=0; row < view.getTableModel().getRowCount(); row++) {
			if (view.getTable().getSorter().convertRowIndexToView(row) != -1) {
				float votoFinale = (Float) view.getTableModel().getValueAt(row, ColumnHeaders.VOTOFINALE.ordinal());
				Object entryName = view.getTableModel().getValueAt(row, col);
				if (entryName instanceof Integer) {dataset.addValue(votoFinale, "Voto", ((Integer) entryName).toString());}
				else if (entryName instanceof String) {dataset.addValue(votoFinale, "Voto", (String) entryName);}
			}
		}

		JFreeChart chart = ChartFactory.createBarChart(
				"Voti esami", 
				category, 
				"Voto", 
				dataset, 
				PlotOrientation.VERTICAL,
                false, true, false);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		
		JDialog dialog = new JDialog(getView(), "Modifica Esame");
		dialog.setContentPane(chartPanel);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.pack();
		dialog.setVisible(true);
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
	
	public List<Esame> getEsamiList(){
		return database.getEsamiList();
	}
	
	public void saveOnFile(File file) throws IOException {
		database.saveOnFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		database.loadFromFile(file);
	}
	
	public Esame getEsame(int ID) {
		return database.getEsame(ID);
	}
	
	public Database getDatabase() {
		return database;
	}
	public void setDatabase(Database database) {
		this.database = database;
	}
	
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
}
