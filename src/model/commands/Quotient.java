package model.commands;

import model.exceptions.MathException;

public class Quotient extends Command {
	public Quotient() {
		super();
	}

	public double execute() throws MathException {
		if (myParameters.get(1) == 0) {
			throw new MathException("Cannot divide by 0!\n" + myParameters.get(0) + " / " + myParameters.get(1));
		}
		return (myParameters.get(0) / myParameters.get(1));
	}

	@Override
	public String toString() {
		return "Quotient";
	}
}