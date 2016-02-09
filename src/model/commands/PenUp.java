package model.commands;

import model.MultipleTurtCommand;

public class PenUp extends MultipleTurtCommand {
	public PenUp() {
		super();
	}

	public double execute() {
		pen.setPenDown(false);
		return 0;
	}
	
	@Override
	public String toString() {
		return "PenUp";
	}
}