package model.commands;

public class LessP extends Command {
	public LessP() {
		super();
	}

	public double execute() {
		if (myParameters.get(0) < myParameters.get(1))
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "LessThan";
	}
}