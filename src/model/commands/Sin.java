package model.commands;

public class Sin extends Command {
	public Sin() {
		super();
	}

	public double execute() {
		return Math.sin(Math.toRadians(myParameters.get(0)));
	}

	@Override
	public String toString() {
		return "Sine";
	}
}