package view.components;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PopUpTablePanel extends JPanel{
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table;
	
	public PopUpTablePanel(Float[] votoArray, Integer[] pesoArray) {
		int initialNumRows = 0;
		String[] columnHeadings = {"Voto", "Peso"};
		tableModel = new DefaultTableModel(initialNumRows, columnHeadings.length) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		tableModel.setColumnIdentifiers(columnHeadings);
		
		int totalNumRows = votoArray.length;
		for(int i=0; i<totalNumRows; i++) {
			String[] row = {votoArray[i].toString(), pesoArray[i].toString()};
			tableModel.addRow(row);
		}
		
		table = new JTable(tableModel);
		
		scrollPane = new JScrollPane(table);
		add(scrollPane);
		
	}
}
