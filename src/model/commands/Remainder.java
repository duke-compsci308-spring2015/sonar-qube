package model.commands;

public class Remainder extends Command {
	public Remainder() {
		super();
	}

	public double execute() {
		if (myParameters.get(0) < myParameters.get(1))
			return myParameters.get(1);
		else {
			return myParameters.get(0) % myParameters.get(1);
		}
	}

	@Override
	public String toString() {
		return "Remainder";
	}
}