package model.commands;

import model.MultipleTurtCommand;

public class HideTurtle extends MultipleTurtCommand {
	public HideTurtle() {
		super();
	}

	public double execute() {
		myTurtle.setVisible(false);
		return 0;
	}
	
	@Override
	public String toString() {
		return "HideTurtle";
	}
}