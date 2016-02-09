package model.commands;

public class ATan extends Command {
	public ATan() {
		super();
	}

	public double execute() {
		return Math.atan(Math.toRadians(myParameters.get(0)));
	}

	@Override
	public String toString() {
		return "ArcTangent";
	}
}