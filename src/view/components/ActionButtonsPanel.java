/**
 * @file ActionButtonsPanel.java
 * 
 * "Sotto-pannello" riutilizzabile per aggiungere i pulsanti Conferma e Annulla ad un pannello, il cui scopo è
 * semplificare il codice e aumentarne la leggibilità. 
 */

package view.components;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionButtonsPanel extends JPanel {

	private ActionListener actionListener;
	private JButton btnConferma;
	private JButton btnAnnulla;
	
	public ActionButtonsPanel(ActionListener al) {
		setActionListener(al);
		btnConferma = new JButton("Conferma");
		btnAnnulla = new JButton("Annulla");
		
		btnConferma.addActionListener(actionListener);
		btnAnnulla.addActionListener(actionListener);
		
		add(btnConferma);
		add(btnAnnulla);
	}

	public JButton getBtnConferma() {
		return btnConferma;
	}
	public void setBtnConferma(JButton btnConferma) {
		this.btnConferma = btnConferma;
	}

	public JButton getBtnAnnulla() {
		return btnAnnulla;
	}
	public void setBtnAnnulla(JButton btnAnnulla) {
		this.btnAnnulla = btnAnnulla;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
}