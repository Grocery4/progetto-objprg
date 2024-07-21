/**
 * @file InvalidLodeException.java
 * 
 * Erorre generato in caso di lode errata.
 */

package view.errors;

public class InvalidLodeException extends Exception {
	public InvalidLodeException(String errMessage) {
		super(errMessage);
	}
}
