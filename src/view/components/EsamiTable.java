package view.components;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Model;
import model.esami.EsameComposto;

public class EsamiTable extends JTable{
	private Controller controller;
	private DefaultTableModel tableModel;
	
	public EsamiTable(Controller controller, DefaultTableModel tableModel) {
		super(tableModel);
		setController(controller);
		setTableModel(tableModel);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addMouseListener(new MouseAdapter() {
			//Doppio click per selezionare le celle
			public void mousePressed(MouseEvent mouseEvent) {
		        Point point = mouseEvent.getPoint();
		        int row = rowAtPoint(point);
		        int column = columnAtPoint(point);
		        
		        int noProveIntermedieHeaderID = 6; 

		        if (mouseEvent.getClickCount() != 2 || getSelectedRow() == -1 || column != noProveIntermedieHeaderID) {return;}
				
		        int noProveIntermedie = Integer.parseInt(String.valueOf(tableModel.getValueAt(row, column)));
				if(noProveIntermedie > 1) {showProveIntermedie(row, column);}
			}
		});
	}
	
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void showProveIntermedie(int row, int column) {
		int ID = Integer.parseInt(String.valueOf(tableModel.getValueAt(row, 0)));
		EsameComposto esame = (EsameComposto) controller.getModel().getEsame(ID);
		JOptionPane.showMessageDialog(null, new PopUpTablePanel(esame.getVotiProveIntermedie(), esame.getPesoProveIntermediePercentage()), "Prove intermedie", JOptionPane.INFORMATION_MESSAGE);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
}
