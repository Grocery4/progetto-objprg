package view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import model.esami.EsameComposto;
import view.View;
import view.errors.*;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class EsameCompostoPanel extends JPanel implements FormActionsInterface {

	private View view;
	
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel lblMatricola;
	private JLabel lblMateria;
	private JLabel lblCfu;
	private JLabel lblNumProveIntermedie;
	private JTextArea nomeTextArea;
	private JTextArea cognomeTextArea;
	private JTextArea matricolaTextArea;
	private JTextArea materiaTextArea;
	private JTextArea cfuTextArea;
	private JTextArea numProveIntermedieTextArea;
	private ActionButtonsPanel actionButtonsPanel;
	
	private JLabel lblPeso;
	private JLabel lblVotoIntermedio;
	private JTextArea votoIntermedioTextArea;
	private JTextArea pesoIntermedioTextArea;
	JButton addButton;
	private JTextArea proveIntermedieTextArea;
	private JLabel votoFinaleLabel;
	private JTextArea votoFinaleTextArea;
	
	private JLabel lodeLabel;
	private JCheckBox lodeCheckBox;
	
	private JButton sendFormButton;
	
	private boolean formLockedStatus;
	
	
	private int arrayIndex;
	
	private String nome;
	private String cognome;
	private int matricola;
	private String materia;
	private int cfu;
	private int numProveIntermedie;
	
	private Float[] pesoArray;
	private Float[] votoArray;
	private boolean lode;
	private float votoFinale;
	
	private List<Float> votoIntermedioArray;
	private List<Float> pesoIntermedioArray;
	
	
	public EsameCompostoPanel(View view) {
		formLockedStatus = false;
		
		setName("esameCompostoPanel");
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
		
		proveIntermedieTextArea = new JTextArea(10, 15);
		proveIntermedieTextArea.setEditable(false);

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
		
		nomeTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		cognomeTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		matricolaTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		materiaTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		cfuTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		numProveIntermedieTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		votoIntermedioTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		pesoIntermedioTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		proveIntermedieTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		votoFinaleTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		
		gbc.insets = new Insets(15,0,15,0);
		gbc.ipadx = 15;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblNome, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(nomeTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblCognome, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(cognomeTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblMatricola, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(matricolaTextArea, gbc);
		
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(lblMateria, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(materiaTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(lblCfu, gbc);
		add(lblNumProveIntermedie, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(cfuTextArea, gbc);
		add(numProveIntermedieTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(lblNumProveIntermedie, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		add(numProveIntermedieTextArea, gbc);
		
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(actionButtonsPanel, gbc);
		
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		add(lblVotoIntermedio, gbc);
		gbc.gridx = 1;
		add(lblPeso, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		add(votoIntermedioTextArea, gbc);
		gbc.gridx = 1;
		add(pesoIntermedioTextArea, gbc);
		gbc.gridx = 2;
		add(addButton, gbc);
		
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(proveIntermedieTextArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(votoFinaleLabel, gbc);
		gbc.gridx = 1;
		add(votoFinaleTextArea, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 11;
		add(lodeLabel, gbc);
		gbc.gridx = 1;
		add(lodeCheckBox, gbc);
		
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 12;
		add(sendFormButton, gbc);
	}
	
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
		lockForm(isFormLockedStatus());
	}

	public void confirmFirstFormContent() {
		nome = null;
		cognome = null;
		matricola = 0;
		materia = null;
		cfu = 0;
		numProveIntermedie = 0;
		
		if(!isFormLockedStatus()) {			
			try {
				checkNullInputs();
				
				nome = nomeTextArea.getText();
				cognome = cognomeTextArea.getText();
				matricola = Integer.parseInt(matricolaTextArea.getText());
				materia = materiaTextArea.getText();
				cfu = Integer.parseInt(cfuTextArea.getText());
				numProveIntermedie = Integer.parseInt(numProveIntermedieTextArea.getText());
				
				if(numProveIntermedie < 1) {throw new InvalidRangeException("valore fuori dall'intervallo");}
				
				initializeArrays();
				setFormLockedStatus(true);
				lockForm(isFormLockedStatus());
			} catch (Exception e) {
				System.err.println(e);
			} 
		}
	}

	private void checkNullInputs() throws NullInputsException {
		if(nomeTextArea.getText().isBlank() || 
				cognomeTextArea.getText().isBlank() || 
				materiaTextArea.getText().isBlank() || 
				cfuTextArea.getText().isBlank() || 
				numProveIntermedieTextArea.getText().isBlank()
				) 
		{throw new NullInputsException("valori nulli inseriti");}
	}
	
	public void updateArrayIntermedio() {
		if(!isFormLockedStatus()) {
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
					
					proveIntermedieTextArea.setText("Voti: " + votoIntermedioArray.toString() + "\n" + "Pesi: " + pesoIntermedioArray.toString());
					proveIntermedieTextArea.setVisible(true);
					
					pesoArray = pesoIntermedioArray.toArray(new Float[numProveIntermedie]);
					votoArray = votoIntermedioArray.toArray(new Float[numProveIntermedie]);
					
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

	private void checkForExceptions() throws NullInputsException, InvalidRangeException {
		if(votoIntermedioTextArea.getText().isBlank() || pesoIntermedioTextArea.getText().isBlank()) {throw new NullInputsException("null values");}
		if(Float.parseFloat(votoIntermedioTextArea.getText()) < 18 || Float.parseFloat(votoIntermedioTextArea.getText()) > 30) {throw new InvalidRangeException("voto fuori dall'intervallo");}
		if(Float.parseFloat(pesoIntermedioTextArea.getText()) < 0 || Float.parseFloat(pesoIntermedioTextArea.getText()) > 100) {throw new InvalidRangeException("peso fuori dall'intervallo");}
	}

	private void addProvaIntermedia() {
		float votoIntermedio = Float.parseFloat(votoIntermedioTextArea.getText());
		float pesoIntermedioPercentage = Float.parseFloat(pesoIntermedioTextArea.getText());

		votoIntermedioArray.add(votoIntermedio);
		pesoIntermedioArray.add(pesoIntermedioPercentage);
		arrayIndex++;
		
		votoIntermedioTextArea.setText("");
		pesoIntermedioTextArea.setText("");
	}
	
	@Override
	public void sendForm() {
		lode = lodeCheckBox.isSelected();
		EsameComposto esame = new EsameComposto(matricola, nome, cognome, materia, cfu, numProveIntermedie, pesoArray, votoArray, lode);
		
		try {
			view.getController().addEsame(esame);
			resetFormContent();
		} catch (Exception e) {
			System.err.println(e);
		}

	}
	
	private void lockForm(boolean formStatus) {
		nomeTextArea.setEditable(!formStatus);
		cognomeTextArea.setEditable(!formStatus);
		matricolaTextArea.setEditable(!formStatus);
		materiaTextArea.setEditable(!formStatus);
		cfuTextArea.setEditable(!formStatus);
		numProveIntermedieTextArea.setEditable(!formStatus);
		
		addButton.setEnabled(formStatus);
		actionButtonsPanel.getBtnConferma().setEnabled(!formStatus);
	}

	//TODO find a way to get rid of votoIntermedioArray & pesoIntermedioArray in favor of using pesoArray & votoArray directly
	private void initializeArrays() {
		arrayIndex = 0;
		votoIntermedioArray = new ArrayList<Float>();
		pesoIntermedioArray = new ArrayList<Float>();
	}
	
	private void initializeForm() {
		nome = null;
		cognome = null;
		matricola = 0;
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
	
	public boolean isFormLockedStatus() {
		return formLockedStatus;
	}
	public void setFormLockedStatus(boolean formLockedStatus) {
		this.formLockedStatus = formLockedStatus;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
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
