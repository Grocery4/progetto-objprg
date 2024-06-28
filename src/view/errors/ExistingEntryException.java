package view.errors;

public class ExistingEntryException extends Exception{
	public ExistingEntryException(String errMessage) {
		super(errMessage);
	}
}
