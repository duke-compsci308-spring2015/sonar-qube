package model.newCommands;

import model.commands.Command;

public class Shape extends Command {
	public Shape() {
		super();
	}

	@Override
	public double execute() {
		return pen.getShape();
	}

	@Override
	public String toString() {
		return "GetShape";
	}
}