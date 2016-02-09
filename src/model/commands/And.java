package model.commands;

public class And extends Command {
	public And() {
		super();
	}

	public double execute() {
		if (myParameters.get(0) != 0 && myParameters.get(1) != 0)
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "And";
	}
}