package model.commands;

public class Difference extends Command {
	public Difference() {
		super();
	}

	public double execute() {
		return myParameters.get(0) - myParameters.get(1);
	}

	@Override
	public String toString() {
		return "Difference";
	}
}