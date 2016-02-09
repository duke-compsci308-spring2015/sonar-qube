package model.commands;

public class Reset extends ClearScreen {
	public Reset() {
		super();
	}

	public double execute() {
		myTurtle.reset();
		return super.execute();
	}
	
	@Override
	public String toString() {
		return "Reset";
	}
}