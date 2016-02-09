package model.commands;

import model.MultipleTurtCommand;

public class SetXY extends MultipleTurtCommand {
	public SetXY() {
		super();
	}

	public double execute() {
		double x0 = myTurtle.getX();
		double y0 = myTurtle.getY();
		double x1 = myParameters.get(0);
		double y1 = myParameters.get(1);

		myTurtle.setX(x1);
		myTurtle.setY(y1);
		myTurtle.addLine(x0, y0, x1, y1);

		return Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
	}

	@Override
	public String toString() {
		return "SetPosition";
	}
}