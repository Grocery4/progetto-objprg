package view.components;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.EsamiTableModel;
import view.View;

public class TableEditorButtonsPanel extends JPanel implements ActionListener {
	private View view;
	private Controller controller;
	private EsamiTable table;
	private EsamiTableModel tableModel;
	
	private JFileChooser fileChooser;
	private JButton savetoFileBtn;
	private JButton loadFromFileBtn;
	private JButton printBtn;
	
	private JButton rimuoviRigaBtn;
	private JButton modificaRigaBtn;
	
	private JLabel filterLabel;
	private JTextArea filterTextArea;
	private JComboBox<String> filterType;
	private JButton filtraBtn;
	private JButton resetFilterBtn;
		
	private JLabel votoMedioLabel;
	private JButton mostraStatisticheBtn;
	
	
	public TableEditorButtonsPanel(View view, Controller controller, EsamiTable table) {
		setView(view);
		setController(controller);
		setTable(table);
		setTableModel((EsamiTableModel) table.getModel());
		
		initializePanel();
	}
	
	private void initializePanel() {
		fileChooser = new JFileChooser();
		savetoFileBtn = new JButton("Salva");
		loadFromFileBtn = new JButton("Carica");
		printBtn = new JButton("Stampa");
		rimuoviRigaBtn = new JButton("Rimuovi");
		modificaRigaBtn = new JButton("Modifica");
		filterLabel = new JLabel("Filtra per: ");
		filterTextArea = new JTextArea(1, 15);
		filterType = new JComboBox<>(new String[] {"Matricola", "Materia"});
		filtraBtn = new JButton("Cerca");
		resetFilterBtn = new JButton("X");
		
		votoMedioLabel = new JLabel("Voto Medio: ");
		mostraStatisticheBtn = new JButton("Stats");
		
		savetoFileBtn.addActionListener(this);
		loadFromFileBtn.addActionListener(this);
		printBtn.addActionListener(this);
		rimuoviRigaBtn.addActionListener(this);
		modificaRigaBtn.addActionListener(this);
		filtraBtn.addActionListener(this);
		resetFilterBtn.addActionListener(this);
		mostraStatisticheBtn.addActionListener(this);
		
		filterTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		resetFilterBtn.setEnabled(false);
		votoMedioLabel.setVisible(false);
		mostraStatisticheBtn.setVisible(false);
		fileChooser.addChoosableFileFilter(new FileFilterEsame());
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		add(savetoFileBtn);
		add(loadFromFileBtn);
		add(printBtn);
		add(rimuoviRigaBtn);
		add(modificaRigaBtn);
		add(filterLabel);
		add(filterTextArea);
		add(filterType);
		add(filtraBtn);
		add(resetFilterBtn);
		add(votoMedioLabel);
		add(mostraStatisticheBtn);
	}
	
	/**
	 * Il metodo actionPerformed permette di identificare il pulsante schiacciato
	 * e azionare il metodo associato.
	 * 
	 * Al momento viene utilizzato una catena di if per il processo di identificazione
	 * poiché gli switch-case non accettano Stringhe come valori.
	 * In futuro potrebbe essere implementato un meccanismo diverso tramite l'utilizzo
	 * di switch + enum. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
		if(buttonName == "Salva") {
			if(fileChooser.showSaveDialog(getView()) == JFileChooser.APPROVE_OPTION) {
				try {
					getController().saveOnFile(fileChooser.getSelectedFile());
					view.getTable().updateTable();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(getView(), "Impossibile salvare su file.", "Errore salvataggio", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(buttonName == "Carica") {
			if(fileChooser.showOpenDialog(getView()) == JFileChooser.APPROVE_OPTION) {
				try {
					getController().loadFromFile(fileChooser.getSelectedFile());
					view.getTable().updateTable();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(getView(), "Impossibile caricare da file.", "Errore caricamento", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(buttonName == "Stampa") {
			controller.stampaTabella();
		}
		else if(buttonName == "Rimuovi") {
			int ID = (int) view.getTableModel().getValueAt(view.getTable().getSelectedRow(), 0);
			controller.removeEsame(ID);
		} 
		//else if (buttonName == "Modifica") {
			//controller.modificaRiga();
		//} 
		else if (buttonName == "Cerca") {
			String query = filterTextArea.getText();
			String filterName = (String) filterType.getSelectedItem();
			
			controller.filtraTabella(query, filterName);
			
			// se la query viene eseguita, allora: rimuovi il testo dalla barra di ricerca,
			// mostra il tasto di reset, mostra il voto medio e il pulsante per le statistiche
			if(!query.isBlank()) {
				filterTextArea.setText("");
				resetFilterBtn.setEnabled(true);
				
				votoMedioLabel.setText("Voto Medio: " + String.format("%.2f", (controller.calcolaVotoMedio())));
				votoMedioLabel.setVisible(true);
				mostraStatisticheBtn.setVisible(true); 
			}
		} 
		else if (buttonName == "X") {
			controller.resetFilter();
			resetFilterBtn.setEnabled(false);
			votoMedioLabel.setVisible(false);
			mostraStatisticheBtn.setVisible(false);
		}
		
		else if (buttonName == "Stats") {
			if((String) filterType.getSelectedItem() == "Matricola") {
				controller.mostraStatistiche(ColumnHeaders.MATERIA.ordinal(), "Materia");
			} else if((String) filterType.getSelectedItem() == "Materia") {
				controller.mostraStatistiche(ColumnHeaders.MATRICOLA.ordinal(), "Matricola");
			}
		}
	}

	public EsamiTable getTable() {
		return table;
	}
	public void setTable(EsamiTable table) {
		this.table = table;
	}

	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}

	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public EsamiTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(EsamiTableModel tableModel) {
		this.tableModel = tableModel;
	}
}
