package model.commands;

public class Pow extends Command {
	public Pow() {
		super();
	}

	public double execute() {
		return Math.pow(myParameters.get(0), myParameters.get(1));
	}

	@Override
	public String toString() {
		return "Power";
	}
}