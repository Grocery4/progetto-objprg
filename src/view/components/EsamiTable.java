package view.components;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import controller.ControllerNEW;
import model.EsamiTableModel;
import model.ModelINUSE;
import model.esami.EsameComposto;

public class EsamiTable extends JTable{
	private ControllerNEW controller;
	private EsamiTableModel tableModel;
	private TableRowSorter<EsamiTableModel> sorter;
	
	public EsamiTable(ControllerNEW controller, EsamiTableModel tableModel) {
		super(tableModel);
		setTableModel(tableModel);
		setController(controller);
		
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/** Il mouseListener attende che il mouse clicchi su qualche cella.
		 * Al doppio click, apre un pop-up che mostra i voti intermedi solo se
		 * la quantità di prove associata alla riga è più di due.
		 */
		addMouseListener(new MouseAdapter() {
			//Doppio click per selezionare le celle
			public void mousePressed(MouseEvent mouseEvent) {
		        if (mouseEvent.getClickCount() != 2 || getSelectedRow() == -1) {return;}
		        int noProveIntermedie = Integer.parseInt(String.valueOf(tableModel.getValueAt(getSelectedRow(), ColumnHeaders.NOPROVE.ordinal())));
				if(noProveIntermedie > 1) {showProveIntermedie(getSelectedRow());}
			}
		});
		
		sorter = new TableRowSorter<>(tableModel);
		setRowSorter(sorter);
	}
	
	public ControllerNEW getController() {
		return controller;
	}
	public void setController(ControllerNEW controller) {
		this.controller = controller;
	}

	public void showProveIntermedie(int row) {
		int ID = Integer.parseInt(String.valueOf(tableModel.getValueAt(row, ColumnHeaders.ID.ordinal())));
		EsameComposto esame = (EsameComposto) controller.getEsame(ID);
		JOptionPane.showMessageDialog(null, new PopUpTablePanel(esame.getVotiProveIntermedie(), esame.getPesoProveIntermediePercentage()), "Prove intermedie", JOptionPane.INFORMATION_MESSAGE);
	}

	public EsamiTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(EsamiTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public TableRowSorter<EsamiTableModel> getSorter() {
		return sorter;
	}
	public void setSorter(TableRowSorter<EsamiTableModel> sorter) {
		this.sorter = sorter;
	}
}
