package model.newCommands;

import model.commands.Command;

public class SetPenSize extends Command {
	public SetPenSize() {
		super();
	}

	@Override
	public double execute() {
		pen.setPenSize(myParameters.get(0));
		return myParameters.get(0);
	}

	@Override
	public String toString() {
		return "SetPenSize";
	}
}