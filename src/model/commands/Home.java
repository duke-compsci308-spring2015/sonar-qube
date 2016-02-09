package model.commands;

import model.MultipleTurtCommand;

public class Home extends MultipleTurtCommand {
	public Home() {
		super();
	}

	public double execute() {
		double x0 = myTurtle.getX();
		double y0 = myTurtle.getY();

		myTurtle.setX(0);
		myTurtle.setY(0);

		double x1 = myTurtle.getX();
		double y1 = myTurtle.getY();
		myTurtle.addLine(x0, y0, x1, y1);

		return Math.sqrt(x0 * x0 + y0 * y0);
	}
	
	@Override
	public String toString() {
		return "Home";
	}
}