package model.commands;

public class PenDownP extends Command {
	public PenDownP() {
		super();
	}

	public double execute() {
		if (pen.isPenDown())
			return 1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return "IsPenDown";
	}
}