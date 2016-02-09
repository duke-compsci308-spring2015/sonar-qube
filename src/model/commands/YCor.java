package model.commands;

public class YCor extends Command {
	public YCor() {
		super();
	}

	public double execute() {
		return myTurtle.getY();
	}

	@Override
	public String toString() {
		return "YCoordinate";
	}
}