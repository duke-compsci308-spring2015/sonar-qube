package model.commands;

public class NotEqualP extends Command {
	public NotEqualP() {
		super();
	}

	public double execute() {
		if (myParameters.get(0) != myParameters.get(0))
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "NotEqual";
	}
}