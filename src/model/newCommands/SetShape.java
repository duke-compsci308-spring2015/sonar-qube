package model.newCommands;

import model.commands.Command;

public class SetShape extends Command {
	public SetShape() {
		super();
	}

	@Override
	public double execute() {
		pen.setShape(myParameters.get(0));
		return myParameters.get(0);
	}

	@Override
	public String toString() {
		return "SetShape";
	}
}