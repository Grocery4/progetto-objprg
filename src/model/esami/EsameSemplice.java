/** @file EsameSemplice.java
 * 
 * Tipologia di Esame, discendente dalla classe Esame.
 */
package model.esami;

public class EsameSemplice extends Esame {
    private static final long serialVersionUID = 1L;

	/**
     * Costruttore della classe EsameSemplice.
     * I valori immessi generici sono dati come input al costruttore della classe genitore.
     * I valori specifici alla classe EsameSemplice sono memorizzati tramite appositi setter.
     * 
     * @param nomeStudente Nome dello studente.
     * @param nomeInsegnamento Nome dell'insegnamento.
     * @param creditiInsegnamento Crediti dell'insegnamento.
     * @param votoProvaUnica Voto della prova unica.
     * @param lode Indica se l'esame ha la lode.
     */
	public EsameSemplice(String nomeStudente, String nomeInsegnamento, int creditiInsegnamento, float votoProvaUnica, boolean lode) {
		super(nomeStudente, nomeInsegnamento, creditiInsegnamento);
		setVotoFinale(votoProvaUnica);
		setLode(lode);
	}
}
