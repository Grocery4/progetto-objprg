/** @file Controller.java 
*  Questa classe funge da intermediario tra la View e il Model, gestendo la logica dell'applicazione e coordinando le interazioni.
*/

package controller;

import java.awt.Dialog.ModalityType;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.ModelINUSE;
import model.esami.Esame;
import model.esami.EsameComposto;
import model.esami.EsameSemplice;
import view.View;
import view.components.ColumnHeaders;
import view.components.EsameCompostoPanel;
import view.components.EsameSemplicePopUpPanel;
import view.components.EsamiTable;
import view.errors.ExistingEntryException;


/**
 * La classe Controller è responsabile della comunicazione tra View e Model.
 */

//TODO vedere se in ogni metodo Map<I,E> mappaEsami può essere sostituito da un eventuale attributo di classe, piuttosto che ridichiararlo ogni volta
//TODO vedere se si può evitare di dichiarare in ogni metodo la variabile table
public class Controller {
	private ModelINUSE model;
	private View view;
	
	private EsamiTable table;
	private DefaultTableModel tableModel;
	
	//TODO sistemare dove posizionare i TRY/CATCH e valutare se mettere THROWS in Model.java
	public void aggiungiEsame(Esame esame) throws ExistingEntryException {
		if(esame instanceof EsameSemplice) {esame = (EsameSemplice) esame;}
		if(esame instanceof EsameComposto) {esame = (EsameComposto) esame;}
		
		Map<Integer, Esame> mappaEsami = model.getMappaEsami();
		int lastEsameID = model.getLastEsameID();
		
		CheckDuplicatesOnAdd(mappaEsami, esame);
			
		esame.setID(lastEsameID);
		tableModel.addRow(esame.toColumns());
		model.incrementLastEsameID();

		mappaEsami.put(esame.getID(), esame);
	}
	//TODO it is polymorphism, but is this the right way to apply it?
	public void modificaEsame(Esame esameModificato) throws ExistingEntryException {
		if(esameModificato instanceof EsameSemplice) {esameModificato = (EsameSemplice) esameModificato;}
		if(esameModificato instanceof EsameComposto) {esameModificato = (EsameComposto) esameModificato;}
		
		int ID = getView().getTable().getSelectedRow();
		Map<Integer, Esame> mappaEsami = model.getMappaEsami();
			
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
		 tableModel = model.getTableModel();
		
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
		
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();
		
		int ID = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
		Esame esame = getModel().getEsame(ID);

		JDialog dialog;
		if(esame instanceof EsameSemplice) {
			EsameSemplicePopUpPanel panel = new EsameSemplicePopUpPanel(getView());
			panel.getNomeTextArea().setText(esame.getNome());
			panel.getCognomeTextArea().setText(esame.getCognome());
			panel.getMatricolaTextArea().setText(String.valueOf(esame.getMatricola()));
			panel.getMateriaTextArea().setText(esame.getNomeInsegnamento());
			panel.getCfuTextArea().setText(String.valueOf(esame.getCreditiInsegnamento()));
			panel.getVotoTextArea().setText(String.valueOf(esame.getVotoFinale()));
			panel.getLodeCheckbox().setSelected(esame.isLode());
			
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

			dialog = new JDialog(getView(), "Modifica Esame");
			dialog.setContentPane(panel);
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.pack();
			dialog.setVisible(true);
			
		} else if (esame instanceof EsameComposto) {
			EsameCompostoPanel panel = new EsameCompostoPanel(getView());
		}
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
		int columnID = toEnum(filterType);
		
		if (query.isBlank()) {
            return;
        } else {
        	table.getSorter().setRowFilter(RowFilter.regexFilter("(?i)^" + query + "$", columnID));
        }
	}
	/**
	 * Rimuove il filtro applicato alla tabella.
	 */
	public void resetFilter() {
		table.getSorter().setRowFilter(null);
	}
	
	public float calcolaVotoMedio() {
		float sommaProdotti, cfuTotali;
		sommaProdotti = cfuTotali = 0;
		
		for(int i=0; i < tableModel.getRowCount(); i++) {
			if (table.getSorter().convertRowIndexToView(i) != -1) {
				//if visible
				float votoFinale, cfu;
				votoFinale = Float.parseFloat( (String) tableModel.getValueAt(i, ColumnHeaders.VOTOFINALE.ordinal()));
				cfu = Float.parseFloat( (String) tableModel.getValueAt(i, ColumnHeaders.CFU.ordinal()));
				
				sommaProdotti += votoFinale * cfu;
				cfuTotali += cfu;
			}
		}
		
		return sommaProdotti / cfuTotali;
	}
	
	public void mostraStatistiche(int col, String category) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(int row=0; row < tableModel.getRowCount(); row++) {
			if (table.getSorter().convertRowIndexToView(row) != -1) {
				float votoFinale = Float.parseFloat( (String) tableModel.getValueAt(row, ColumnHeaders.VOTOFINALE.ordinal()));
				String entryName = (String) tableModel.getValueAt(row, col);
				dataset.addValue(votoFinale, "Voto", entryName);
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
            boolean status = table.print(JTable.PrintMode.FIT_WIDTH, null, null);
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
	 * Restituisce al chiamante l'id della colonna in base al tipo di filtrazione necessario.
	 * 
	 * @return ColumnHeaders Id della colonna. 
	 */
	private int toEnum(String filterType) {
		if(filterType.toUpperCase().equalsIgnoreCase(ColumnHeaders.MATRICOLA.name())) {
			return ColumnHeaders.MATRICOLA.ordinal();
		}
		return ColumnHeaders.MATERIA.ordinal();
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
	
	public EsamiTable getTable() {
		return table;
	}
	public void setTable(EsamiTable table) {
		this.table = table;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	public ModelINUSE getModel() {
		return model;
	}
	public void setModel(ModelINUSE model) {
		this.model = model;
	}

	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
}
