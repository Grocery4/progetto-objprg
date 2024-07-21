/**
 * @file EsameModifierPanel.java
 * 
 * Sotto-pannello utilizzato per la modifica di esami già esistenti all'interno della tabella.
 */

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
import model.esami.EsameComposto;
import view.View;
import view.errors.*;

public class EsameModifierPanel extends JPanel implements ActionListener {
	private int idEsame;
	private Esame esame;
	
	private View view;
	
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	
	private JLabel lblNomeStudente;
	private JLabel lblMateria;
	private JLabel lblCfu;
	private JLabel lblVoto;
	private JLabel lblLode;
	private JTextArea nomeStudenteTextArea;
	private JTextArea materiaTextArea;
	private JTextArea cfuTextArea;
	private JTextArea votoTextArea;
	private JCheckBox lodeCheckbox;
	private JButton confermaBtn;
	
	private JTextArea[] votiIntermediTextAreaArray;
	private JTextArea[] pesiIntermediTextAreaArray;
	private JButton aggiornaBtn;
	
	private Float[] pesiProveIntermediePercentage; 
	private Float[] votiProveIntermedie;

	
	public EsameModifierPanel(View view, int idEsame) {
		setView(view);
		setIdEsame(idEsame);
		setEsame(getView().getController().findEsameById(idEsame));
		setName("esameSemplicePanel");
		
		initializePanel();
	}
	
	private void initializePanel() {
		gbc = new GridBagConstraints();
		layout = new GridBagLayout();
		setLayout(layout);
		
		lblNomeStudente = new JLabel("Nome Completo:");
		nomeStudenteTextArea = new JTextArea(1, 15);
		lblMateria = new JLabel("Materia:");
		materiaTextArea = new JTextArea(1, 15);
		lblCfu = new JLabel("CFU:");
		cfuTextArea = new JTextArea(1, 15);
		lblVoto = new JLabel("Voto:");
		votoTextArea = new JTextArea(1, 15);
		lblLode = new JLabel("Lode:");
		lodeCheckbox = new JCheckBox();
		
		confermaBtn = new JButton("Conferma");
		confermaBtn.addActionListener(this);
		
		nomeStudenteTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		materiaTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		cfuTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		votoTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		if(esame instanceof EsameComposto) {votoTextArea.setEditable(false); confermaBtn.setEnabled(false);}
		
		
		nomeStudenteTextArea.setText(esame.getNomeStudente());
		materiaTextArea.setText(esame.getNomeInsegnamento());
		cfuTextArea.setText(String.valueOf(esame.getCreditiInsegnamento()));
		votoTextArea.setText(String.valueOf(esame.getVotoFinale()));
		lodeCheckbox.setSelected(esame.isLode());
		
		gbc.insets = new Insets(15,0,15,0);
		gbc.ipadx = 15;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblNomeStudente, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(nomeStudenteTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblMateria, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(materiaTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblCfu, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(cfuTextArea, gbc);
		
		int lastYValue = initializeIntermediateValues(3);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = lastYValue;
		add(lblVoto, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(votoTextArea, gbc);
		lastYValue++;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = lastYValue;
		add(lblLode, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(lodeCheckbox, gbc);
		lastYValue++;
		
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = lastYValue;
		add(confermaBtn, gbc);
		
	}
	
	/**
	 * Metodo che genera le caselle di testo per la modifica delle prove intermedie.
	 * @param yValue Valore y della riga da cui far partire la generazione delle caselle di testo.
	 * @return Valore y della riga successiva da creare.
	 */
	private int initializeIntermediateValues(int yValue) {
		int numeroProve = (esame instanceof EsameSemplice ? 1 : ((EsameComposto) esame).getNumeroProveIntermedie());
		
		if(esame instanceof EsameComposto) {
			pesiProveIntermediePercentage = new Float[numeroProve]; 
			votiProveIntermedie = new Float[numeroProve];
			
			votiIntermediTextAreaArray = new JTextArea[numeroProve];
			pesiIntermediTextAreaArray = new JTextArea[numeroProve];
			
			gbc.insets = new Insets(15,0,15,0);
			for(int noRigheAggiunte = 0; noRigheAggiunte < numeroProve; noRigheAggiunte++) {
				gbc.gridwidth = 1;
				gbc.gridx = 0;
				gbc.gridy = yValue++;
				JLabel votoLabel = new JLabel("Voto " + (noRigheAggiunte + 1));
				add(votoLabel, gbc);
				
				gbc.gridwidth = 2;
				gbc.gridx = 1;
				JTextArea votoTextArea = new JTextArea(1, 15);
				votoTextArea.setBorder(new LineBorder(Color.BLACK, 1));
				votoTextArea.setText(String.valueOf(((EsameComposto) esame).getVotiProveIntermedie()[noRigheAggiunte]));
				votiIntermediTextAreaArray[noRigheAggiunte] = votoTextArea;
				add(votoTextArea, gbc);
				
				
				gbc.gridwidth = 1;
				gbc.gridx = 0;
				gbc.gridy = yValue++;
				JLabel pesoLabel = new JLabel("Peso " + (noRigheAggiunte + 1));
				add(pesoLabel, gbc);
				
				gbc.gridwidth = 2;
				gbc.gridx = 1;
				JTextArea pesoTextArea = new JTextArea(1, 15);
				pesoTextArea.setBorder(new LineBorder(Color.BLACK, 1));
				pesoTextArea.setText(String.valueOf(((EsameComposto) esame).getPesoProveIntermediePercentage()[noRigheAggiunte]));
				pesiIntermediTextAreaArray[noRigheAggiunte] = pesoTextArea;
				add(pesoTextArea, gbc);
			}
			
			gbc.gridwidth = 3;
			gbc.gridx = 0;
			gbc.gridy = yValue++;
			aggiornaBtn = new JButton("Aggiorna Voto");
			aggiornaBtn.addActionListener(this);
			add(aggiornaBtn, gbc);
		}
		
		return yValue;
	}
	
	/**
	 * Metodo per reindirizzare il click di un pulsante al metodo adeguato.
	 * 
	 * @param e Evento generato dal pulsante.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		if (buttonName == "Conferma") {
			sendFormToView();
		}
		else if(buttonName == "Aggiorna Voto") {
			try {
				aggiornaVoto();
			} catch (NullInputsException | InvalidRangeException err) {
				System.err.print(err.getMessage());
			}
		}
	}
	
	/**
	 * Metodo per la spedizione dell'esame modificato all'interno del database, passando prima per il View e successivamente il controller.
	 */
	public void sendFormToView() {
		String nome = null;
		String materia = null;
		int cfu = 0;
		float voto = 0;
		boolean lode = false;
		
		try {
			checkForNullComponents();
			
			nome = nomeStudenteTextArea.getText();
			materia = materiaTextArea.getText();
			cfu = Integer.parseInt(cfuTextArea.getText());
			voto = Float.parseFloat(votoTextArea.getText());
			lode = lodeCheckbox.isSelected();
			
			checkVotoValidity(voto, lode);
			
			// esempio: polimorfismo
			Esame esameAggiornato = null;
			if(esame instanceof EsameSemplice) {
				 esameAggiornato = new EsameSemplice(nome, materia, cfu, voto, lode);
			}
			else if(esame instanceof EsameComposto) {
				int numeroProve = ((EsameComposto) esame).getNumeroProveIntermedie();
				esameAggiornato = new EsameComposto(nome, materia, cfu, numeroProve, pesiProveIntermediePercentage, votiProveIntermedie, lode);
			}
			getView().getController().editEsame(idEsame, esameAggiornato);
			
			//chiudi finestra alla fine
			JDialog frame = ((JDialog) SwingUtilities.getWindowAncestor(this));
			frame.dispose();
		} catch (Exception e) {
			System.err.println(e);
		} 
	}
	
	/**
	 * Metodo per l'aggiornamento della casella di testo contenente il voto finale calcolato
	 * facendo la media ponderata dei valori intermedi.
	 * 
	 * @throws NullInputsException
	 * @throws InvalidRangeException
	 */
	private void aggiornaVoto() throws NullInputsException, InvalidRangeException {
		int numeroProve = ((EsameComposto) esame).getNumeroProveIntermedie();
		
		try {
			for(int i = 0; i < numeroProve; i++) {
				checkForNullComponents();
				
				float votoIntermedio = Float.parseFloat(votiIntermediTextAreaArray[i].getText());
				float pesoIntermedio = Float.parseFloat(pesiIntermediTextAreaArray[i].getText());
				
				checkRangeValidity(votoIntermedio, pesoIntermedio);
				
				votiProveIntermedie[i] = votoIntermedio;
				pesiProveIntermediePercentage[i] = pesoIntermedio;
			}
			
			float votoFinaleAggiornato = EsameComposto.calcolaVotoFinale(numeroProve, pesiProveIntermediePercentage, votiProveIntermedie);
			votoTextArea.setText(String.valueOf(votoFinaleAggiornato));
				
			if (votoFinaleAggiornato < 30) {
				lodeCheckbox.setSelected(false);
				lodeCheckbox.setEnabled(false);
			}
			
			confermaBtn.setEnabled(true);
		} catch (Exception err) {
			System.err.println(err);
		}
	}
	
	/**
	 * Metodo per il controllo di valori nulli durante la spedizione dell'esame modificato al View.
	 * 
	 * @throws NullInputsException
	 */
	private void checkForNullComponents() throws NullInputsException {
		Component[] components = this.getComponents();
		for (Component component : components) {
			if (!(component.getClass().equals(JTextArea.class))) {continue;}
			if(((JTextArea) component).getText().isBlank()) {throw new NullInputsException("valori nulli inseriti");}
		}
	}
	
	/**
	 * Metodo per il controllo della corretta assegnazione della lode.
	 * 
	 * @param voto Voto finale dell'esame.
	 * @param lode Valore della lode.
	 * 
	 * @throws InvalidRangeException
	 * @throws InvalidLodeException
	 */
	private void checkVotoValidity(float voto, boolean lode) throws InvalidRangeException, InvalidLodeException {
		if(voto < 18 || voto > 30) {throw new InvalidRangeException("voto fuori dall'intervallo");}
		if(lode == true && voto != 30) {throw new InvalidLodeException("lode non applicabile");}
	}
	
	/**
	 * Metodo per il controllo del range del voto intermedio e del peso inserito in caso di esame composto.
	 * 
	 * @param votoIntermedio Voto della prova intermedia.
	 * @param pesoIntermedio Peso della prova intermedia.
	 * 
	 * @throws InvalidRangeException
	 */
	private void checkRangeValidity(float votoIntermedio, float pesoIntermedio) throws InvalidRangeException {
		if(votoIntermedio < 18 || votoIntermedio > 30) {throw new InvalidRangeException("voto fuori dall'intervallo");}
		if(pesoIntermedio < 0 || pesoIntermedio > 100) {throw new InvalidRangeException("peso fuori dall'intervallo");}
	}
	
	public Esame getEsame() {
		return esame;
	}
	public void setEsame(Esame esame) {
		this.esame = esame;
	}

	public int getIdEsame() {
		return idEsame;
	}
	public void setIdEsame(int noProveIntermedie) {
		this.idEsame = noProveIntermedie;
	}

	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}

	public JTextArea getNomeTextArea() {
		return nomeStudenteTextArea;
	}
	public void setNomeTextArea(JTextArea nomeTextArea) {
		this.nomeStudenteTextArea = nomeTextArea;
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
