/** @file EsameSemplice.java */

package model.esami;

/*
 * la classe EsameSemplice non dispone di un metodo per calcolare votoFinale, in quanto non necessario.
 */
public class EsameSemplice extends Esame {
	public EsameSemplice(int matricola, String nome, String cognome, String nomeInsegnamento, int creditiInsegnamento, float votoProvaUnica, boolean lode) {
		super(matricola, nome, cognome, nomeInsegnamento, creditiInsegnamento);
		setVotoFinale(votoProvaUnica);
		setLode(lode);
	}
	
	@Override
	public String[] toColumns() {
		return new String[] {String.valueOf(super.getID()), String.valueOf(super.getMatricola()), super.getNome(), super.getCognome(), super.getNomeInsegnamento(), String.valueOf(super.getCreditiInsegnamento()), "1", String.valueOf(String.format("%.2f",super.getVotoFinale())), super.lodeToString()};
	}
}
