package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.esami.Esame;
import model.esami.EsameComposto;
import model.esami.EsameSemplice;

public class TableEditorButtonsPanel extends JPanel implements ActionListener {
	private JButton rimuoviRigaButton;
	private JButton modificaRigaButton;
	
	private Controller controller;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public TableEditorButtonsPanel(Controller controller, JTable table) {
		setController(controller);
		setTable(table);
		setTableModel((DefaultTableModel) table.getModel());
		
		initializePanel();
	}
	
	private void initializePanel() {
		rimuoviRigaButton = new JButton("Rimuovi");
		rimuoviRigaButton.addActionListener(this);
		
		modificaRigaButton = new JButton("Modifica");
		modificaRigaButton.addActionListener(this);
		
		add(rimuoviRigaButton);
		add(modificaRigaButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		if(buttonName == "Rimuovi") {
			rimuoviRiga();
		} 
		else if (buttonName == "Modifica") {
			modificaRiga();
		}
	}
	
	//TODO spostare metodi rimuoviRiga e modificaRiga in Model.java
	private void rimuoviRiga() {
		if(table.getSelectedRow() != -1) 
        {
			
			//elimina esame dalla lista
			int column = 0; //ID column
			int row = table.getSelectedRow();
			int ID = Integer.parseInt(tableModel.getValueAt(row, column).toString());
			controller.eliminaEsame(ID);
			
			//elimina esame dal JTable
			tableModel.removeRow(table.getSelectedRow());
           JOptionPane.showMessageDialog(null, "Rimozione avvenuta con successo!");
        }
	}
	
	private void modificaRiga() {
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();
		
		int ID = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
		Esame esame = controller.getModel().getEsame(ID);

		JDialog dialog;
		if(esame instanceof EsameSemplice) {
			EsameSemplicePopUpPanel panel = new EsameSemplicePopUpPanel(controller.getView());
			panel.getNomeTextArea().setText(esame.getNome());
			panel.getCognomeTextArea().setText(esame.getCognome());
			panel.getMatricolaTextArea().setText(String.valueOf(esame.getMatricola()));
			panel.getMateriaTextArea().setText(esame.getNomeInsegnamento());
			panel.getCfuTextArea().setText(String.valueOf(esame.getCreditiInsegnamento()));
			panel.getVotoTextArea().setText(String.valueOf(esame.getVotoFinale()));
			panel.getLodeCheckbox().setSelected(esame.isLode());
			
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

			dialog = new JDialog(controller.getView(), "Modifica Esame");
			dialog.setContentPane(panel);
			dialog.setModal(true);
			dialog.pack();
			dialog.setVisible(true);
			
		} else if (esame instanceof EsameComposto) {
			EsameCompostoPanel panel = new EsameCompostoPanel(controller.getView());
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
