package model.newCommands;

import model.commands.Command;

public class SetPalette extends Command {
	public SetPalette() {
		super();
	}

	@Override
	public double execute() {
		double param0 = myParameters.get(0);
		double param1 = myParameters.get(1);
		double param2 = myParameters.get(2);
		double param3 = myParameters.get(3);
		pen.updatePalette(param0, (int) param1, (int) param2, (int) param3);
		return param0;
	}

	@Override
	public String toString() {
		return "SetPalette";
	}
}