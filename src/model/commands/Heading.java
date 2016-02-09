package model.commands;

public class Heading extends Command {
	public Heading() {
		super();
	}

	public double execute() {
		return ((450 - myTurtle.getAngle()) % 360);
	}
	
	@Override
	public String toString() {
		return "Heading";
	}
}