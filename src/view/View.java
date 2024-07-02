/**
 * @file View.java
 * 
 * Questa classe rappresenta la finestra principale dell'applicazione del sistema di gestione degli esami.
 * Si occupa della gestione del frame e dell'integrazione dei vari componenti dell'interfaccia utente, oltre
 * alla comunicazione con il controller.
 */

package view;

import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import view.components.*;
import view.errors.ExistingEntryException;
import model.esami.*;

//TODO debate whether to use getters or use attributes directly
//TODO aggiungere sysout quando si fa qualcosa in GUI
public class View extends JFrame {
	private Controller controller;
	private BorderLayout frameLayout;
	private SidePanel sidePanel;
	private TableEditorButtonsPanel editorPanel;
	private DefaultTableModel tableModel;
	
	private JScrollPane tableScrollPane;
	private EsamiTable table;
	
    /**
     * Costruttore per la classe View.
     *
     * @param controller L'oggetto che gestisce la logica dell'applicazione.
    */
	public View(Controller controller) {
		setController(controller);
		initializeFrameSettings();
		initializeFramePanels();
	}
	
	/**
	 * Inizializza il frame, layout, titolo, comportamento di chiusura e le dimensioni predefinite.
	*/
	private void initializeFrameSettings() {
		frameLayout = new BorderLayout();
		setLayout(frameLayout);
		setTitle("Gestore Esami");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setBounds(200,200, 1200, 700);
	}
	/**
	 * Inizializza i pannelli principali del frame.
	*/
	private void initializeFramePanels() {
		sidePanel = new SidePanel(this);
		add(sidePanel, BorderLayout.WEST);
		
		tableModel = getController().getModel().getTableModel();
		table = new EsamiTable(controller, tableModel);
		
		tableScrollPane = new JScrollPane(table);
		add(tableScrollPane, BorderLayout.CENTER);
		
		editorPanel = new TableEditorButtonsPanel(controller, table);
		add(editorPanel, BorderLayout.SOUTH);
	}
	
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public EsamiTable getTable() {
		return table;
	}
	public void setTable(EsamiTable table) {
		this.table = table;
	}
}
