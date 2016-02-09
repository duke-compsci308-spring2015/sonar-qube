package model.newCommands;

import model.commands.Command;

public class ClearStamps extends Command {
	public ClearStamps() {
		super();
	}

	@Override
	public double execute() {
		return pen.clearStampList();
	}

	@Override
	public String toString() {
		return "ClearStamps";
	}
}