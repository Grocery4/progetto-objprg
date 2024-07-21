/** @file Esame.java 
 * 
 *  Questa classe astratta rappresenta un esame e definisce i metodi e gli attributi comuni ai due tipi di esami.
 */
package model.esami;

import java.io.Serializable;

//esempio: ereditarietà
public abstract class Esame implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nomeStudente;
	private String nomeInsegnamento;
	private int creditiInsegnamento;
	private float votoFinale;
	private boolean lode;
	
	
    /**
     * Costruttore della classe Esame. 
     * Setta i valori degli attributi non specifici per il tipo di esame.
     * 
     * @param nomeStudente Nome e cognome dello studente.
     * @param nomeInsegnamento Nome dell'insegnamento.
     * @param creditiInsegnamento Crediti dell'insegnamento.
     */
	public Esame(String nomeStudente, String nomeInsegnamento, int creditiInsegnamento){
		setNomeStudente(nomeStudente);
		setNomeInsegnamento(nomeInsegnamento);
		setCreditiInsegnamento(creditiInsegnamento);		
	}
	
	public int getID() {
		return this.id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
	public String getNomeStudente() {
		return nomeStudente;
	}
	public void setNomeStudente(String nome) {
		this.nomeStudente = nome.toUpperCase().trim();
	}

	public String getNomeInsegnamento() {
		return nomeInsegnamento;
	}
	public void setNomeInsegnamento(String nomeInsegnamento) {
		this.nomeInsegnamento = nomeInsegnamento.toUpperCase().trim();
	}

	public float getVotoFinale() {
		return votoFinale;
	}
	public void setVotoFinale(float votoFinale) {
		this.votoFinale = votoFinale;
	}

	public boolean isLode() {
		return lode;
	}
	/** 
	 * Metodo che stabilisce se attribuire o meno la lode.
	 * La lode può essere attribuita solo se lode==true && votoFinale==30,
	 * è quindi necessario che la lode venga settata solo DOPO aver stabilito il valore di votoFinale
	 * 
	 * @param lode Indica se l'esame ha la lode.
	 */
	public void setLode(boolean lode) {
		if(lode == true && getVotoFinale() == 30) {
			this.lode = lode;			
		}
		else {
			this.lode = false;
		}
	}

	public int getCreditiInsegnamento() {
		return creditiInsegnamento;
	}
	public void setCreditiInsegnamento(int creditiInsegnamento) {
		this.creditiInsegnamento = creditiInsegnamento;
	}
}
