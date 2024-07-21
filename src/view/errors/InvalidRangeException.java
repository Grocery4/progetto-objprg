/**
 * @file InvalidRangeException.java
 * 
 * Erorre generato in caso di valore non rientrante nel range dato.
 */

package view.errors;

public class InvalidRangeException extends Exception {
	public InvalidRangeException(String errMessage) {
		super(errMessage);
	}
}
