/** @file EsameComposto.java */

package model.esami;

import java.util.Arrays;

public class EsameComposto extends Esame{
	private int numeroProveIntermedie;
	private Float[] pesoProveIntermediePercentage;
	private Float[] votiProveIntermedie;
	
    /**
     * Costruttore della classe EsameSemplice.
     * I valori immessi generici sono dati come input al costruttore della classe genitore.
     * I valori specifici alla classe EsameComposto sono memorizzati tramite appositi setter.
     * 
     * @param matricola Numero di matricola dello studente.
     * @param nome Nome dello studente.
     * @param cognome Cognome dello studente.
     * @param nomeInsegnamento Nome dell'insegnamento.
     * @param creditiInsegnamento Crediti dell'insegnamento.
     * @param numeroProveIntermedie Numero di prove intermedie.
     * @param pesoProveIntermediePercentage Percentuale di peso di ciascuna prova intermedia.
     * @param votiProveIntermedie Voti ottenuti nelle prove intermedie.
     * @param lode Indica se l'esame ha la lode.
     */ 
	public EsameComposto(int matricola, String nome, String cognome, String nomeInsegnamento, int creditiInsegnamento, int numeroProveIntermedie, Float[] pesoProveIntermediePercentage, Float[] votiProveIntermedie, boolean lode) {
		super(matricola, nome, cognome, nomeInsegnamento, creditiInsegnamento);
		setNumeroProveIntermedie(numeroProveIntermedie);
		setPesoProveIntermediePercentage(pesoProveIntermediePercentage);
		setVotiProveIntermedie(votiProveIntermedie);
		setVotoFinale(calcolaVotoFinale(numeroProveIntermedie, pesoProveIntermediePercentage, votiProveIntermedie));
		setLode(lode);
	}
	
	/** Implementazione concreta del metodo astratto della classe Esame.
	 * 
	 * @return Un array di stringhe contenente i dati dell'esame.
	 */
	@Override
	public String[] toColumns() {
		return new String[] {String.valueOf(super.getID()), String.valueOf(super.getMatricola()), super.getNome(), super.getCognome(), super.getNomeInsegnamento(), String.valueOf(super.getCreditiInsegnamento()), String.valueOf(numeroProveIntermedie), String.valueOf(String.format("%.2f",super.getVotoFinale())), super.lodeToString()};
	}
	
    /**
     * Calcola il voto finale facendo la media ponderata delle prove intermedie dopo aver normalizzato i pesi.
     * @param numeroProveIntermedie Numero di prove intermedie.
     * @param pesoProveIntermediePercentage Percentuale di peso di ciascuna prova intermedia.
     * @param votiProveIntermedie Voti ottenuti nelle prove intermedie.
     * @return Il voto finale calcolato come media ponderata.
     */
	public static float calcolaVotoFinale(int numeroProveIntermedie, Float[] pesoProveIntermediePercentage, Float[] votiProveIntermedie) {
		float sommaPesi, mediaPonderata;
		sommaPesi = mediaPonderata = 0f;
		
		for (float peso : pesoProveIntermediePercentage) { sommaPesi += peso; }
		for (int i=0; i<numeroProveIntermedie; i++) {
			float pesoNormalizzato = pesoProveIntermediePercentage[i] / sommaPesi;
			mediaPonderata += votiProveIntermedie[i] * pesoNormalizzato;
		}
		
		return mediaPonderata;
		
	}
	
	public int getNumeroProveIntermedie() {
		return numeroProveIntermedie;
	}
	public void setNumeroProveIntermedie(int numeroProveIntermedie) {
		this.numeroProveIntermedie = numeroProveIntermedie;
	}
	
	public Float[] getPesoProveIntermediePercentage() {
		return pesoProveIntermediePercentage;
	}
	public void setPesoProveIntermediePercentage(Float[] pesoProveIntermediePercentage2) {
		this.pesoProveIntermediePercentage = pesoProveIntermediePercentage2;
	}

	public Float[] getVotiProveIntermedie() {
		return votiProveIntermedie;
	}
	public void setVotiProveIntermedie(Float[] votiProveIntermedie2) {
		this.votiProveIntermedie = votiProveIntermedie2;
	}
	
}
