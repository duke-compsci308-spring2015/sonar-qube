package model.newCommands;

import model.commands.Command;
import model.data.Turtle;

public class Turtles extends Command {
	public Turtles() {
		super();
	}

	@Override
	public double execute() {
		return Turtle.getNumTurtles();
	}

	@Override
	public String toString() {
		return "Turtles";
	}
}