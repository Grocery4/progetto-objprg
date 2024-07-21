/**
 * @file ColumnHeaders.java
 * 
 * Enum utilizzato per risolvere il problema dei magic numbers quando si fa riferimento ad una colonna.
 * Utilizza il metodo ColumnHeaders.<NOMECOLONNA>.ordinal() per ottenere l'indice di colonna.
 */

package view.components;

public enum ColumnHeaders {
	ID,
	NOMECOMPLETO,
	MATERIA,
	CFU,
	NOPROVE,
	VOTOFINALE,
	LODE
}
