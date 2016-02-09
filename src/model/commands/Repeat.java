package model.commands;

public class Repeat extends SpecialCommand {
	public Repeat() {
		super();
	}

	public double execute() {
		for (int i = 0; i < myParameters.get(0); i++) {
			runList.addAll(myCommandLists.get(0));
		}
		return -1;
	}

	@Override
	public String toString() {
		return "Repeat";
	}
}