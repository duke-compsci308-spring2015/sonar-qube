package model.commands;

import model.MultipleTurtCommand;

public class Forward extends MultipleTurtCommand {
	public Forward() {
		super();
	}

	public double execute() {
		double length = myParameters.get(0);
		double x0 = myTurtle.getX();
		double y0 = myTurtle.getY();
		double angle = myTurtle.getAngle();

		double x1 = x0 + length * Math.cos(Math.toRadians(angle));
		double y1 = y0 + length * Math.sin(Math.toRadians(angle));

		myTurtle.setX(x1);
		myTurtle.setY(y1);
		myTurtle.addLine(x0, y0, x1, y1);

		return length;
	}

	@Override
	public String toString() {
		return "Forward";
	}
}