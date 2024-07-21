/**
 * @file EsameCompostoPanel.java
 * 
 * Sotto-pannello utilizzato per la compilazione del form inerente ad un esame composto.
 */

package view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import model.esami.EsameComposto;
import view.View;
import view.errors.*;
import javax.swing.SwingConstants;

public class EsameCompostoPanel extends JPanel implements FormActionsInterface {

	private View view;
	
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	
	private JLabel lblNomeStudente;
	private JLabel lblMateria;
	private JLabel lblCfu;
	private JLabel lblNumProveIntermedie;
	private JTextArea nomeStudenteTextArea;
	private JTextArea materiaTextArea;
	private JTextArea cfuTextArea;
	private JTextArea numProveIntermedieTextArea;
	private ActionButtonsPanel actionButtonsPanel;
	
	private JLabel lblPeso;
	private JLabel lblVotoIntermedio;
	private JTextArea votoIntermedioTextArea;
	private JTextArea pesoIntermedioTextArea;
	private JButton addButton;
	
	private JLabel votoFinaleLabel;
	private JTextArea votoFinaleTextArea;
	
	private JLabel lodeLabel;
	private JCheckBox lodeCheckBox;
	
	private JButton sendFormButton;
	
	private boolean formLockedStatus;
	
	
	private int arrayIndex;
	
	private String nomeStudente;
	private String materia;
	private int cfu;
	private int numProveIntermedie;
	
	private Float[] pesoArray;
	private Float[] votoArray;
	private boolean lode;
	private float votoFinale;
	
	public EsameCompostoPanel(View view) {
		formLockedStatus = false;
		
		setName("esameCompostoPanel");
		setView(view);
		
		initializePanel();
		
	}
	
	/**
	 * Metodo per l'inizializzazione dei componenti del pannello.
	 */
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

		lblNumProveIntermedie = new JLabel("Qtà. prove:");
		numProveIntermedieTextArea = new JTextArea(1, 15);
		
		actionButtonsPanel = new ActionButtonsPanel(this);
		
		lblVotoIntermedio = new JLabel("Voto:");
		lblVotoIntermedio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeso = new JLabel("Peso:");
		lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
		
		votoIntermedioTextArea = new JTextArea(1, 3);
		pesoIntermedioTextArea = new JTextArea(1, 3);

		addButton = new JButton("+");
		addButton.setEnabled(false);
		addButton.addActionListener(this);
		
		votoFinaleLabel = new JLabel("Voto finale: ");
		votoFinaleTextArea = new JTextArea(1, 3);
		votoFinaleTextArea.setEditable(false);
		
		lodeLabel = new JLabel("Lode: ");
		lodeCheckBox = new JCheckBox();
		lodeLabel.setVisible(false);
		lodeCheckBox.setVisible(false);
		
		sendFormButton = new JButton("Invia");
		sendFormButton.setVisible(false);
		sendFormButton.addActionListener(this);
		
		nomeStudenteTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		materiaTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		cfuTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		numProveIntermedieTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		votoIntermedioTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		pesoIntermedioTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		votoFinaleTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		
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
		add(lblNumProveIntermedie, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(cfuTextArea, gbc);
		add(numProveIntermedieTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(lblNumProveIntermedie, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(numProveIntermedieTextArea, gbc);
		
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(actionButtonsPanel, gbc);
		
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(lblVotoIntermedio, gbc);
		gbc.gridx = 1;
		add(lblPeso, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(votoIntermedioTextArea, gbc);
		gbc.gridx = 1;
		add(pesoIntermedioTextArea, gbc);
		gbc.gridx = 2;
		add(addButton, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 7;
		add(votoFinaleLabel, gbc);
		gbc.gridx = 1;
		add(votoFinaleTextArea, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		add(lodeLabel, gbc);
		gbc.gridx = 1;
		add(lodeCheckBox, gbc);
		
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(sendFormButton, gbc);
	}
	
	/**
	 * Metodo per reindirizzare il click di un pulsante al metodo adeguato.
	 * 
	 * @param e Evento generato dal pulsante.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((JButton) e.getSource()).getText();
		
		switch(buttonName) {
			case "Annulla":
				resetFormContent();
				break;
			case "Conferma":
				confirmFirstFormContent();
				break;
			case "+":
				updateArrayIntermedio();
				break;
			case "Invia":
				sendForm();
				break;
		}
	}
	
	/**
	 * Metodo per l'eliminazione dei dati compilati nel form in caso di errore.
	 */
	@Override
	public void resetFormContent() {
		Component[] components = this.getComponents();
		for (Component component : components) {
			if (component.getClass().equals(JTextArea.class))
				((JTextArea) component).setText("");
		}
		
		lodeCheckBox.setSelected(false);
		
		lodeLabel.setVisible(false);
		lodeCheckBox.setVisible(false);
		sendFormButton.setVisible(false);
		
		initializeArrays();
		initializeForm();
		setFormLockedStatus(false);
		lockForm(isFormLocked());
	}

	/**
	 * Metodo per la conferma della prima parte del form riguardante ai dati generali.
	 */
	public void confirmFirstFormContent() {
		nomeStudente = null;
		materia = null;
		cfu = 0;
		numProveIntermedie = 0;
		
		if(isFormLocked() == false) {			
			try {
				checkNullInputs();
				
				nomeStudente = nomeStudenteTextArea.getText();
				materia = materiaTextArea.getText();
				cfu = Integer.parseInt(cfuTextArea.getText());
				numProveIntermedie = Integer.parseInt(numProveIntermedieTextArea.getText());
				
				if(numProveIntermedie <= 1) {throw new InvalidRangeException("valore fuori dall'intervallo");}
				
				initializeArrays();
				setFormLockedStatus(true);
				lockForm(isFormLocked());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} 
		}
	}

	/**
	 * Metodo per il controllo di valori nulli.
	 * 
	 * @throws NullInputsException
	 */
	private void checkNullInputs() throws NullInputsException {
		if(nomeStudenteTextArea.getText().isBlank() || 
				materiaTextArea.getText().isBlank() || 
				cfuTextArea.getText().isBlank() || 
				numProveIntermedieTextArea.getText().isBlank()
				) 
		{throw new NullInputsException("valori nulli inseriti");}
	}
	
	/**
	 * Metodo per l'aggiornamento della seconda parte del form inerente alle prove intermedie.
	 * 
	 */
	public void updateArrayIntermedio() {
		if(isFormLocked() == false) {
			System.err.println("form non ancora compilato");
		} else {			
			try {
				checkForExceptions();
				
				if(arrayIndex < numProveIntermedie) {
					addProvaIntermedia();
				} else {
					System.err.println("numero prove attuale maggiore del numero di prove previste");
				}
				
				if(arrayIndex == numProveIntermedie) {
					addButton.setEnabled(false);
					
					votoFinale = EsameComposto.calcolaVotoFinale(numProveIntermedie, pesoArray, votoArray);
					
					//votoFinale viene usato solo per mostrarne il valore nella GUI
					//non viene inviato al View
					votoFinaleTextArea.setText(String.format("%.1f", votoFinale));
					votoFinaleTextArea.setVisible(true);
					
					if(votoFinale == 30f) {
						lodeLabel.setVisible(true);
						lodeCheckBox.setVisible(true);
					}
					
					sendFormButton.setVisible(true);
				}
				
				
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	/**
	 * Metodo per il controllo di valori nulli o fuori dal range durante l'inserimento delle prove intermedie.
	 * @throws NullInputsException
	 * @throws InvalidRangeException
	 */
	private void checkForExceptions() throws NullInputsException, InvalidRangeException {
		if(votoIntermedioTextArea.getText().isBlank() || pesoIntermedioTextArea.getText().isBlank()) {throw new NullInputsException("null values");}
		if(Float.parseFloat(votoIntermedioTextArea.getText()) < 18 || Float.parseFloat(votoIntermedioTextArea.getText()) > 30) {throw new InvalidRangeException("voto fuori dall'intervallo");}
		if(Float.parseFloat(pesoIntermedioTextArea.getText()) < 0 || Float.parseFloat(pesoIntermedioTextArea.getText()) > 100) {throw new InvalidRangeException("peso fuori dall'intervallo");}
	}
	
	/**
	 * Metodo per l'inserimento di voto e peso di una prova intermedia.
	 */
	private void addProvaIntermedia() {
		float votoIntermedio = Float.parseFloat(votoIntermedioTextArea.getText());
		float pesoIntermedioPercentage = Float.parseFloat(pesoIntermedioTextArea.getText());

		votoArray[arrayIndex] = votoIntermedio;
		pesoArray[arrayIndex] = pesoIntermedioPercentage;
		
		arrayIndex++;
		
		votoIntermedioTextArea.setText("");
		pesoIntermedioTextArea.setText("");
	}
	
	/**
	 * Metodo per l'aggiungimento dell'esame composto all'interno del database, passando prima per il View e successivamente il controller.
	 */
	@Override
	public void sendForm() {
		lode = lodeCheckBox.isSelected();
		EsameComposto esame = new EsameComposto(nomeStudente, materia, cfu, numProveIntermedie, pesoArray, votoArray, lode);
		
		try {
			view.getController().addEsame(esame);
			resetFormContent();
		} catch (Exception e) {
			System.err.println(e);
		}

	}
	
	/**
	 * Metodo che blocca la prima parte del form durante la compilazione del secondo form inerente alle prove intermedie.
	 * 
	 * @param formStatus Stato del primo form.
	 */
	private void lockForm(boolean formStatus) {
		nomeStudenteTextArea.setEditable(!formStatus);
		materiaTextArea.setEditable(!formStatus);
		cfuTextArea.setEditable(!formStatus);
		numProveIntermedieTextArea.setEditable(!formStatus);
		
		addButton.setEnabled(formStatus);
		actionButtonsPanel.getBtnConferma().setEnabled(!formStatus);
	}

	/**
	 * Metodo per l'inizializzazione degli array contenenti i dati delle prove intermedie.
	 */
	private void initializeArrays() {
		arrayIndex = 0;
		pesoArray = new Float[numProveIntermedie];
		votoArray = new Float[numProveIntermedie];
	}
	
	/**
	 * Metodo per l'inizializzazione dei dati della prima parte del form.
	 */
	private void initializeForm() {
		nomeStudente = null;
		materia = null;
		cfu = 0;
		numProveIntermedie = 0;
	}
	
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
	
	public boolean isFormLocked() {
		return formLockedStatus;
	}
	public void setFormLockedStatus(boolean formLockedStatus) {
		this.formLockedStatus = formLockedStatus;
	}

	public String getNome() {
		return nomeStudente;
	}
	public void setNome(String nome) {
		this.nomeStudente = nome;
	}

	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}

	public int getCfu() {
		return cfu;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public int getNumProveIntermedie() {
		return numProveIntermedie;
	}
	public void setNumProveIntermedie(int numProveIntermedie) {
		this.numProveIntermedie = numProveIntermedie;
	}
}
