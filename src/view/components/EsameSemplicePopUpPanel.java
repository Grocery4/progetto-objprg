package view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import model.esami.Esame;
import model.esami.EsameSemplice;
import view.View;
import view.errors.*;

public class EsameSemplicePopUpPanel extends JPanel implements ActionListener {

	private View view;
	
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel lblMatricola;
	private JLabel lblMateria;
	private JLabel lblCfu;
	private JLabel lblVoto;
	private JLabel lblLode;
	private JTextArea nomeTextArea;
	private JTextArea cognomeTextArea;
	private JTextArea matricolaTextArea;
	private JTextArea materiaTextArea;
	private JTextArea cfuTextArea;
	private JTextArea votoTextArea;
	private JCheckBox lodeCheckbox;
	private JButton btnConferma;

	
	public EsameSemplicePopUpPanel(View view) {
		setName("esameSemplicePanel");
		setView(view);
		
		initializePanel();
		
	}
	
	private void initializePanel() {
		gbc = new GridBagConstraints();
		layout = new GridBagLayout();
		setLayout(layout);
		
		lblNome = new JLabel("Nome:");
		nomeTextArea = new JTextArea(1, 15);
		lblCognome = new JLabel("Cognome:");
		cognomeTextArea = new JTextArea(1, 15);
		lblMatricola = new JLabel("Matricola:");
		matricolaTextArea = new JTextArea(1, 15);
		lblMateria = new JLabel("Materia:");
		materiaTextArea = new JTextArea(1, 15);
		lblCfu = new JLabel("CFU:");
		cfuTextArea = new JTextArea(1, 15);
		lblVoto = new JLabel("Voto:");
		votoTextArea = new JTextArea(1, 15);
		lblLode = new JLabel("Lode:");
		lodeCheckbox = new JCheckBox();
		
		btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(this);
		
		
		nomeTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		cognomeTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		materiaTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		matricolaTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		cfuTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		votoTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		
		gbc.insets = new Insets(15,0,15,0);
		gbc.ipadx = 15;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblNome, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(nomeTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0; 
		gbc.gridy = 1;
		add(lblCognome, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(cognomeTextArea, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblMatricola, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(matricolaTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(lblMateria, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(materiaTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(lblCfu, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(cfuTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(lblVoto, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(votoTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(lblLode, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(lodeCheckbox, gbc);
		
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 7;
		add(btnConferma, gbc);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		if (buttonName == "Conferma") {
			sendFormToView();
		}
	}
	
	public void sendFormToView() {
		String nome = null;
		String cognome = null;
		int matricola = 0;
		String materia = null;
		int cfu = 0;
		float voto = 0;
		boolean lode = false;
		
		try {
			checkForNullComponents();
			
			nome = nomeTextArea.getText();
			cognome = cognomeTextArea.getText();
			matricola = Integer.parseInt(matricolaTextArea.getText());
			materia = materiaTextArea.getText();
			cfu = Integer.parseInt(cfuTextArea.getText());
			voto = Float.parseFloat(votoTextArea.getText());
			lode = lodeCheckbox.isSelected();
			
			checkVotoValidity(voto, lode);
	
			Esame esame = new EsameSemplice(matricola, nome, cognome, materia, cfu, voto, lode);
			view.getController().modificaEsame(esame);
			
			//chiudi finestra alla fine
			JDialog frame = ((JDialog) SwingUtilities.getWindowAncestor(this));
			frame.dispose();
		} catch (Exception e) {
			System.err.println(e);
		} 
	}
	
	public void checkForNullComponents() throws NullInputsException {
		Component[] components = this.getComponents();
		for (Component component : components) {
			if (!(component.getClass().equals(JTextArea.class))) {continue;}
			if(((JTextArea) component).getText().isBlank()) {throw new NullInputsException("valori nulli inseriti");}
		}
	}
	
	public void checkVotoValidity(float voto, boolean lode) throws InvalidRangeException, InvalidLodeException {
		if(voto < 18 || voto > 30) {throw new InvalidRangeException("voto fuori dall'intervallo");}
		if(lode == true && voto != 30) {throw new InvalidLodeException("lode non applicabile");}
	}

	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}

	public JTextArea getNomeTextArea() {
		return nomeTextArea;
	}
	public void setNomeTextArea(JTextArea nomeTextArea) {
		this.nomeTextArea = nomeTextArea;
	}

	public JTextArea getCognomeTextArea() {
		return cognomeTextArea;
	}
	public void setCognomeTextArea(JTextArea cognomeTextArea) {
		this.cognomeTextArea = cognomeTextArea;
	}

	public JTextArea getMatricolaTextArea() {
		return matricolaTextArea;
	}
	public void setMatricolaTextArea(JTextArea matricolaTextArea) {
		this.matricolaTextArea = matricolaTextArea;
	}

	public JTextArea getMateriaTextArea() {
		return materiaTextArea;
	}
	public void setMateriaTextArea(JTextArea materiaTextArea) {
		this.materiaTextArea = materiaTextArea;
	}

	public JTextArea getCfuTextArea() {
		return cfuTextArea;
	}
	public void setCfuTextArea(JTextArea cfuTextArea) {
		this.cfuTextArea = cfuTextArea;
	}

	public JTextArea getVotoTextArea() {
		return votoTextArea;
	}
	public void setVotoTextArea(JTextArea votoTextArea) {
		this.votoTextArea = votoTextArea;
	}

	public JCheckBox getLodeCheckbox() {
		return lodeCheckbox;
	}
	public void setLodeCheckbox(JCheckBox lodeCheckbox) {
		this.lodeCheckbox = lodeCheckbox;
	}

}
