package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.View;
import javax.swing.BoxLayout;

public class SidePanel extends JPanel implements ActionListener{
	private View view;

	private BoxLayout layout;
	
	private EsamePickerPanel buttons;
	private CardPanel cardPanel;
	
	public SidePanel(View view) {
		setView(view);
		initializePanel();
	}
	
	public void initializePanel() {
		layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		
		buttons = new EsamePickerPanel(this);
		buttons.setAlignmentX(CENTER_ALIGNMENT);
		
		cardPanel = new CardPanel(view);
		cardPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		add(buttons);
		add(cardPanel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((((JButton) e.getSource()).getText().equals("Esame Semplice"))) {
			cardPanel.getCardLayout().show(cardPanel, "semplice");
		}
		else if ((((JButton) e.getSource()).getText().equals("Esame Composto")) ) {
			cardPanel.getCardLayout().show(cardPanel, "composto");
		}
	}
	
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
}
