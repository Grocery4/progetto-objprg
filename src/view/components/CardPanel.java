/**
 * @file CardPanel.java
 * 
 * Pannello destinato al contenimento dei due form EsameSemplicePanel e EsameCompostoPanel.
 * Attraverso l'utilizzo di un CardLayout, è possibile mostrare uno dei due form, nascondendo allo stesso tempo l'altro.
 */


package view.components;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.View;

import java.awt.CardLayout;

public class CardPanel extends JPanel{
	private View view;
	
	private CardLayout cardLayout;
	
	private EsameSemplicePanel esameSemplicePanel;
	private EsameCompostoPanel esameCompostoPanel;
	
	public CardPanel(View view) {
		setView(view);
		initializePanel();
	}
	
	private void initializePanel() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		
		esameSemplicePanel = new EsameSemplicePanel(view);
		esameCompostoPanel = new EsameCompostoPanel(view);
		
		add(esameSemplicePanel, "semplice");
		add(esameCompostoPanel, "composto");
	}

	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
	
}
