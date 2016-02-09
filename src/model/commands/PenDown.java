package model.commands;

import model.MultipleTurtCommand;

public class PenDown extends MultipleTurtCommand {
	public PenDown() {
		super();
	}

	public double execute() {
		pen.setPenDown(true);
		return 1;
	}
	
	@Override
	public String toString() {
		return "PenDown";
	}
}