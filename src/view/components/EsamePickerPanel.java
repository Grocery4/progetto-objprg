/**
 * @file EsamePickerPanel.java
 * 
 * Sotto-pannello composto da due pulsanti destinato alla selezione del tipo di esame.
 * A seconda della scelta, verrà mostrato il form da compilare adatto.
 */

package view.components;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EsamePickerPanel extends JPanel {
	
	private JButton esameSempliceBtn;
	private JButton esameCompostoBtn;
	
	public EsamePickerPanel(SidePanel sidePanel) {
		esameSempliceBtn = new JButton("Esame Semplice");
		esameCompostoBtn = new JButton("Esame Composto");
		
		esameSempliceBtn.addActionListener(sidePanel);
		esameCompostoBtn.addActionListener(sidePanel);
		
		add(esameSempliceBtn);
		add(esameCompostoBtn);
	}
}
