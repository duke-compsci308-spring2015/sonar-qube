package model.commands;

public class ShowingP extends Command {
	public ShowingP() {
		super();
	}

	public double execute() {
		if (myTurtle.isVisible())
			return 1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return "IsShowing";
	}
}