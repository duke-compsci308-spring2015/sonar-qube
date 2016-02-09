package model.commands;

public class Pi extends Command {
	public Pi() {
		super();
	}

	public double execute() {
		return Math.PI;
	}
	
	@Override
	public String toString() {
		return "Pi";
	}
}