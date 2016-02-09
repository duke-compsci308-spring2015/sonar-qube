package model.commands;

import model.MultipleTurtCommand;

public class Left extends MultipleTurtCommand {
	public Left() {
		super();
	}

	public double execute() {
		double startAngle = myTurtle.getAngle();
		double endAngle = startAngle + myParameters.get(0);
		myTurtle.setAngle(endAngle % 360);
		return myParameters.get(0);
	}

	@Override
	public String toString() {
		return "Left";
	}
}