package model.newCommands;

import model.commands.Command;

public class StampCommand extends Command {
	public StampCommand() {
		super();
	}

	@Override
	public double execute() {
		return pen.stamp(myTurtle.getX(), myTurtle.getY(), myTurtle.getAngle());
	}

	@Override
	public String toString() {
		return "Stamp";
	}
}