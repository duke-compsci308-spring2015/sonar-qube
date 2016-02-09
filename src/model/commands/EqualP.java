package model.commands;

public class EqualP extends Command {
	public EqualP() {
		super();
	}

	public double execute() {
		if (myParameters.get(0) == myParameters.get(1))
			return 1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return "Equal";
	}
}