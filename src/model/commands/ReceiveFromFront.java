package model.commands;

import model.exceptions.CommandInputException;
import model.exceptions.MathException;

public interface ReceiveFromFront {
	public void receiveCommand(String s) throws CommandInputException, NumberFormatException, MathException;

	public void receiveLanguage(String s);

	public void receiveImageString(String s);
}