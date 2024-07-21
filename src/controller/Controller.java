/**
 * @file Controller.java
 * 
 * La classe Controller gestisce l'interazione tra la vista e il modello nell'applicazione
 * tramite un approccio MVC (Model-View-Controller) per separare le responsabilità tra le varie parti del programma.
 * 
 * Questa classe gestisce le operazioni di aggiunta, rimozione, modifica e filtraggio degli esami.
 * Contiene metodi per aggiornare la vista in base ai dati nel modello, gestire le eccezioni e 
 * visualizzare grafici e risultati di stampa.
 */

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

public class Controller {
	private Database database;
	private View view;
	
	/**
	 * Metodo per l'aggiunta di un esame al database e conseguente aggiornamento sulla tabella.
	 * 
	 * @param e Esame da aggiungere
	 */
	public void addEsame(Esame e) {
		database.addToList(e);
		view.getTable().updateTable();
	}
	
	/**
	 * Metodo per la rimozione di un esame dal database e conseguente aggiornamento della tabella.
	 * 
	 * @param ID Id dell'esame da rimuovere
	 */
	public void removeEsame(int ID) {
		database.removeFromList(ID);
		view.getTable().updateTable();
		JOptionPane.showMessageDialog(null, "Rimozione avvenuta con successo!");
	}
	
	/**
	 * Metodo per la modifica dell'esame all'interno del database.
	 * Trova l'esame esistente con l'ID specificato e lo sostituisce con l'esame aggiornato.
	 * Successivamente, aggiorna la tabella nella vista per riflettere le modifiche.
	 * 
	 * @param idEsame Id dell'esame da modificare.
	 * @param esameAggiornato L'oggetto Esame contenente i dati aggiornati.
	 */
	public void editEsame(int idEsame, Esame esameAggiornato) {
		database.editInList(idEsame, esameAggiornato);
		view.getTable().updateTable();
	}
	
	/**
	 * Metodo per la filtrazione dei dati nella tabella in base alla query e al tipo di filtro specificati.
	 *
	 * @param query      La stringa di ricerca utilizzata per filtrare i dati nella tabella. 
	 *                   Se la query è vuota o contiene solo spazi, il metodo non applica alcun filtro.
	 * @param filterType Il tipo di filtro utilizzato per determinare quale colonna della tabella deve essere filtrata.
	 *                   Questo parametro è convertito in un identificatore di colonna tramite il metodo toEnum.
	 */
	public void filtraTabella(String query, String filterType) {
		if (query.isBlank()) {return;} 

		int columnID = 0;
		if(filterType == "Nome Completo") {
			columnID = ColumnHeaders.NOMECOMPLETO.ordinal();
		} else if(filterType == "Materia") {
			columnID = ColumnHeaders.MATERIA.ordinal();
		}
		
		//?: 0 o 1 caratteri, i: case insensitivity, ^: inizio stringa, $: fine stringa 
		view.getTable().getSorter().setRowFilter(RowFilter.regexFilter("(?i)^" + query + "$", columnID));
	}
	
	/**
	 * Metodo per la rimozione del filtro applicato alla tabella.
	 */
	public void resetFilter() {
		view.getTable().getSorter().setRowFilter(null);
	}
	
	/**
	 * Metodo per il calcolo del voto medio sui valori restituiti dal filtro.
	 * 
	 * @return Voto medio dato dalla media ponderata basata sui crediti di ogni esame
	 */
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
	
	/**
	 * Metodo che mostra un grafico a barre delle statistiche dei voti finali degli esami.
	 * Crea un dataset con i voti finali degli esami e lo utilizza per generare un grafico a barre
	 * che viene visualizzato in una finestra di dialogo modale.
	 * 
	 * @param col La colonna della tabella che contiene le categorie da confrontare nel grafico.
	 * @param category La descrizione della categoria utilizzata come etichetta sull'asse delle ascisse del grafico.
	 */
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
	
	/**
	 * Il metodo stampaTabella() sfrutta il metodo JTable.print() per stampare la tabella, e
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
	
	/**
	 * Metodo per recuperare uno specifico esame dalla lista.
	 * 
	 * @param ID Identificatore dell'esame.
	 * @return Esame identificato da id.
	 */
	public Esame findEsameById (int idEsame) {
		return database.findEsameById(idEsame);
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
		return database.findEsameById(ID);
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
