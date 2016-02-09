package model.newCommands;

import model.commands.Command;

public class PenColor extends Command {
	public PenColor() {
		super();
	}

	@Override
	public double execute() {
		return pen.getPenColorIndex();
	}

	@Override
	public String toString() {
		return "GetPenColor";
	}
}