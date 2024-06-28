/** @file View.java */

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
	
	
	public View(Controller controller) {
		setController(controller);
		initializeView();
	}
	
	private void initializeView(){
		frameLayout = new BorderLayout();
		setLayout(frameLayout);
		setTitle("Gestore Esami");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setBounds(200,200, 1200, 700);
		
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
