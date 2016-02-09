package model.commands;

public class Sum extends Command {
	public Sum() {
		super();
	}

	public double execute() {
		return myParameters.get(0) + myParameters.get(1);
	}

	@Override
	public String toString() {
		return "Sum";
	}
}