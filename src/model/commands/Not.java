package model.commands;

public class Not extends Command {
	public Not() {
		super();
	}

	public double execute() {
		if (myParameters.get(0) == 0)
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "Not";
	}
}