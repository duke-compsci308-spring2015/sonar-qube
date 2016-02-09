package model.newCommands;

import model.commands.Command;

public class SetBackground extends Command {
	public SetBackground() {
		super();
	}

	@Override
	public double execute() {
		pen.setBackgroundColor(myParameters.get(0));
		return myParameters.get(0);
	}

	@Override
	public String toString() {
		return "SetBackground";
	}
}