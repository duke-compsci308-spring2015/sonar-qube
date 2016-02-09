package model.commands;

public class XCor extends Command {
	public XCor() {
		super();
	}

	public double execute() {
		return myTurtle.getX();
	}

	@Override
	public String toString() {
		return "XCoordinate";
	}
}