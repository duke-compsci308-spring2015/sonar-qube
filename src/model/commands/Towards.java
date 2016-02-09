package model.commands;

import model.MultipleTurtCommand;

public class Towards extends MultipleTurtCommand {
	public Towards() {
		super();
	}

	public double execute() {
		double startAngle = myTurtle.getAngle();

		double startX = myTurtle.getX();
		double startY = myTurtle.getY();
		double endX = myParameters.get(0);
		double endY = myParameters.get(1);

		if (startX == endX && startY == endY) {
			return 0;
		}

		double slope = (endY - startY) / (endX - startX);
		double endAngle = Math.toDegrees(Math.atan(slope));

		if (startX > endX) {
			endAngle += 180;
		}

		myTurtle.setAngle(endAngle);

		double difference = (360 + startAngle - endAngle) % 360;

		return difference;
	}

	@Override
	public String toString() {
		return "SetTowards";
	}
}