/** @file EsameComposto.java */

package model.esami;

public class EsameComposto extends Esame{
	private int numeroProveIntermedie;
	private Integer[] pesoProveIntermediePercentage;
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
	public EsameComposto(int matricola, String nome, String cognome, String nomeInsegnamento, int creditiInsegnamento, int numeroProveIntermedie, Integer[] pesoProveIntermediePercentage, Float[] votiProveIntermedie, boolean lode) {
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
     * Calcola il voto finale facendo la media ponderata delle prove intermedie.
     * @param numeroProveIntermedie Numero di prove intermedie.
     * @param pesoProveIntermediePercentage Percentuale di peso di ciascuna prova intermedia.
     * @param votiProveIntermedie Voti ottenuti nelle prove intermedie.
     * @return Il voto finale calcolato come media ponderata.
     */
	public static float calcolaVotoFinale(int numeroProveIntermedie, Integer[] pesoProveIntermediePercentage, Float[] votiProveIntermedie) {
		// mediaPonderata = (sum(votoIntermedio_i * pesoIntermedio_i))/sum(pesoIntermedio_i) da i = 1,...,n
		float risultatoFinale, votoPonderato, sommaPesi;
		risultatoFinale = votoPonderato = sommaPesi = 0;
		for(int indiceVoto = 0; indiceVoto < numeroProveIntermedie; indiceVoto++) {
			votoPonderato += votiProveIntermedie[indiceVoto]*pesoProveIntermediePercentage[indiceVoto];
			sommaPesi += pesoProveIntermediePercentage[indiceVoto];
		}
		risultatoFinale = votoPonderato / sommaPesi;
		return risultatoFinale;
	}
	
	public int getNumeroProveIntermedie() {
		return numeroProveIntermedie;
	}
	public void setNumeroProveIntermedie(int numeroProveIntermedie) {
		this.numeroProveIntermedie = numeroProveIntermedie;
	}
	
	public Integer[] getPesoProveIntermediePercentage() {
		return pesoProveIntermediePercentage;
	}
	public void setPesoProveIntermediePercentage(Integer[] pesoProveIntermediePercentage2) {
		this.pesoProveIntermediePercentage = pesoProveIntermediePercentage2;
	}

	public Float[] getVotiProveIntermedie() {
		return votiProveIntermedie;
	}
	public void setVotiProveIntermedie(Float[] votiProveIntermedie2) {
		this.votiProveIntermedie = votiProveIntermedie2;
	}
	
}
