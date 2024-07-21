/**
 * @file NullInputsException.java
 * 
 * Errore generato in caso di input nulli in una JTextArea.
 *
 */

package view.errors;

public class NullInputsException extends Exception {
	public NullInputsException(String errMessage) {
		super(errMessage);
	}
}