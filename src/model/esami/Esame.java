/** @file Esame.java */

package model.esami;

/* 
 * L'idea è quella di creare una GUI in modo tale da costringere il programma
 * ad avere sempre il numero giusto di parametri, per esempio presentando un errore
 * in caso di valori nulli, così da non dover passare valori "vuoti" all'oggetto.
*/
public abstract class Esame{
	/** Attributi di un singolo esame */
	//TODO aggiungere data
	private int ID;
	private int matricola;
	private String nome;
	private String cognome;
	private String nomeInsegnamento;
	private int creditiInsegnamento;
	private float votoFinale;
	private boolean lode;
	
	
	// Inizializzazione con i valori 'standard'
	Esame(int matricola, String nome, String cognome, String nomeInsegnamento, int creditiInsegnamento){
		setMatricola(matricola);
		setNome(nome);
		setCognome(cognome);
		setNomeInsegnamento(nomeInsegnamento);
		setCreditiInsegnamento(creditiInsegnamento);		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase().trim();
	}

	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome.toUpperCase().trim();
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

	/** 
	 * La lode può essere attribuita solo se lode==true && votoFinale==30,
	 * è quindi necessario che la lode venga settata solo DOPO aver stabilito il valore di votoFinale
	*/
	public boolean isLode() {
		return lode;
	}
	public void setLode(boolean lode) {
		if(lode == true && getVotoFinale() == 30) {
			this.lode = lode;			
		}
		else {
			this.lode = false;
		}
	}
	
	public String lodeToString() {
		if (isLode() == true) {
			return "SI";
		} else {
			return "NO";
		}
	}
	
	public int getCreditiInsegnamento() {
		return creditiInsegnamento;
	}
	public void setCreditiInsegnamento(int creditiInsegnamento) {
		this.creditiInsegnamento = creditiInsegnamento;
	}

	public boolean equals(Esame e) {
		// compara matricola e materia di ogni oggetto Esame
		if(matricola == e.getMatricola() && nomeInsegnamento.equals(e.getNomeInsegnamento())) {
			return true;
		}
		return false;
	}
	
	/** A seconda del tipo di Esame, il metodo toColumns restituirà un array di String contenenti
	 * i dati inerenti ad un esame semplice o composto. */
	public abstract String[] toColumns();

}
