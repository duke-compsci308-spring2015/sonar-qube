package model.commands;

import model.exceptions.MathException;

public class Tan extends Command {
	public Tan() {
		super();
	}

	public double execute() throws MathException {
		double angle = myParameters.get(0);
		if (angle % 180 == 90.0) {
			throw new MathException("tan(" + (int) angle + "\u00b0) is undefined!");
		}
		return Math.tan(Math.toRadians(myParameters.get(0)));
	}

	@Override
	public String toString() {
		return "Tangent";
	}
}