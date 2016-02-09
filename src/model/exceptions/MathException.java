package model.exceptions;

public class MathException extends Exception {
	private String myErrorMessage;

	public MathException(String errorMessage) {
		myErrorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return myErrorMessage;
	}
}