package model.commands;

public class Or extends Command {
	public Or() {
		super();
	}

	public double execute() {
		if (myParameters.get(0) != 0 || myParameters.get(1) != 0)
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "Or";
	}
}