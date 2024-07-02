/** @file EsameSemplice.java
*  Questa classe rappresenta un esame semplice, estendendo la classe astratta Esame.
*/
package model.esami;

public class EsameSemplice extends Esame {
    /**
     * Costruttore della classe EsameSemplice.
     * I valori immessi generici sono dati come input al costruttore della classe genitore.
     * I valori specifici alla classe EsameSemplice sono memorizzati tramite appositi setter.
     * 
     * @param matricola Numero di matricola dello studente.
     * @param nome Nome dello studente.
     * @param cognome Cognome dello studente.
     * @param nomeInsegnamento Nome dell'insegnamento.
     * @param creditiInsegnamento Crediti dell'insegnamento.
     * @param votoProvaUnica Voto della prova unica.
     * @param lode Indica se l'esame ha la lode.
     */
	public EsameSemplice(int matricola, String nome, String cognome, String nomeInsegnamento, int creditiInsegnamento, float votoProvaUnica, boolean lode) {
		super(matricola, nome, cognome, nomeInsegnamento, creditiInsegnamento);
		setVotoFinale(votoProvaUnica);
		setLode(lode);
	}
	
	/** Implementazione concreta del metodo astratto della classe Esame.
	 * 
	 * @return Un array di stringhe contenente i dati dell'esame.
	 */
	@Override
	public String[] toColumns() {
		return new String[] {String.valueOf(super.getID()), String.valueOf(super.getMatricola()), super.getNome(), super.getCognome(), super.getNomeInsegnamento(), String.valueOf(super.getCreditiInsegnamento()), "1", String.valueOf(String.format("%.2f",super.getVotoFinale())), super.lodeToString()};
	}
}
