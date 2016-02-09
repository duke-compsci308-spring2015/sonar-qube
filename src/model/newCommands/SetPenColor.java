package model.newCommands;

import model.commands.Command;

public class SetPenColor extends Command {
	public SetPenColor() {
		super();
	}

	@Override
	public double execute() {
		pen.setPenColor(myParameters.get(0));
		return myParameters.get(3);
	}

	@Override
	public String toString() {
		return "SetPenColor";
	}
}