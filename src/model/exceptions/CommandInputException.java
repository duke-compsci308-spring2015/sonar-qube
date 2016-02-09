package model.exceptions;

public class CommandInputException extends Exception {
	private String myBadInput;

	public CommandInputException(String badInput) {
		myBadInput = badInput;
	}

	public String getBadInput() {
		return myBadInput;
	}
}