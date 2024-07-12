package view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.esami.Esame;
import model.esami.EsameComposto;
import model.esami.EsameSemplice;

public class TableEditorButtonsPanel extends JPanel implements ActionListener {
	private Controller controller;
	private JTable table;
	private DefaultTableModel tableModel;
	
	private JButton rimuoviRigaButton;
	private JButton modificaRigaButton;
	
	private JLabel filterLabel;
	private JTextArea filterTextArea;
	private JComboBox<String> filterType;
	private JButton filtraBtn;
	private JButton resetFilterBtn;
		
	private JLabel votoMedioLabel;
	private JButton mostraStatisticheBtn;
	
	
	public TableEditorButtonsPanel(Controller controller, JTable table) {
		setController(controller);
		setTable(table);
		setTableModel((DefaultTableModel) table.getModel());
		
		initializePanel();
	}
	
	private void initializePanel() {
		rimuoviRigaButton = new JButton("Rimuovi");
		modificaRigaButton = new JButton("Modifica");
		filterLabel = new JLabel("Filtra per: ");
		filterTextArea = new JTextArea(1, 15);
		filterType = new JComboBox<>(new String[] {"Matricola", "Materia"});
		filtraBtn = new JButton("Cerca");
		resetFilterBtn = new JButton("X");
		
		votoMedioLabel = new JLabel("Voto Medio: ");
		mostraStatisticheBtn = new JButton("Stats");
		
		rimuoviRigaButton.addActionListener(this);
		modificaRigaButton.addActionListener(this);
		filtraBtn.addActionListener(this);
		resetFilterBtn.addActionListener(this);
		mostraStatisticheBtn.addActionListener(this);
		
		filterTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		resetFilterBtn.setEnabled(false);
		votoMedioLabel.setVisible(false);
		mostraStatisticheBtn.setVisible(false);
		
		add(rimuoviRigaButton);
		add(modificaRigaButton);
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
		if(buttonName == "Rimuovi") {
			controller.rimuoviRiga();
		} 
		else if (buttonName == "Modifica") {
			controller.modificaRiga();
		} 
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

	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}

	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
}
