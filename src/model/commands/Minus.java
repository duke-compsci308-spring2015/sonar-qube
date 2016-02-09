package model.commands;

public class Minus extends Command {
	public Minus() {
		super();
	}

	public double execute() {
		return myParameters.get(0) * -1;
	}

	@Override
	public String toString() {
		return "Minus";
	}
}