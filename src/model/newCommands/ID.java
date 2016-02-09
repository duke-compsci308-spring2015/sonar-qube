package model.newCommands;

import model.commands.Command;

public class ID extends Command {
	public ID() {
		super();
	}

	@Override
	public double execute() {
		return myTurtle.getID();
	}

	@Override
	public String toString() {
		return "ID";
	}
}