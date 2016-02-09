package model.commands;

public class Log extends Command {
	public Log() {
		super();
	}

	public double execute() {
		return Math.log(myParameters.get(0));
	}

	@Override
	public String toString() {
		return "NaturalLog";
	}
}