/** @file EsameComposto.java */

package model.esami;

import java.util.Arrays;

/*
 * la classe EsameComposto dispone di un metodo a sé per calcolare votoFinale.
*/
public class EsameComposto extends Esame{
	private int numeroProveIntermedie;
	private Integer[] pesoProveIntermediePercentage;
	private Float[] votiProveIntermedie;
	
	public EsameComposto(int matricola, String nome, String cognome, String nomeInsegnamento, int creditiInsegnamento, int numeroProveIntermedie, Integer[] pesoProveIntermediePercentage, Float[] votiProveIntermedie, boolean lode) {
		super(matricola, nome, cognome, nomeInsegnamento, creditiInsegnamento);
		setNumeroProveIntermedie(numeroProveIntermedie);
		setPesoProveIntermediePercentage(pesoProveIntermediePercentage);
		setVotiProveIntermedie(votiProveIntermedie);
		setVotoFinale(calcolaVotoFinale(numeroProveIntermedie, pesoProveIntermediePercentage, votiProveIntermedie));
		setLode(lode);
	}
	
	@Override
	public String[] toColumns() {
		return new String[] {String.valueOf(super.getID()), String.valueOf(super.getMatricola()), super.getNome(), super.getCognome(), super.getNomeInsegnamento(), String.valueOf(super.getCreditiInsegnamento()), String.valueOf(numeroProveIntermedie), String.valueOf(String.format("%.2f",super.getVotoFinale())), super.lodeToString()};
	}
	
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
		//in questo modo si ritorna una copia dell'array e non il riferimento all'array reale
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
