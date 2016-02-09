package model.commands;

public class If extends SpecialCommand {
	public If() {
		super();
	}

	public double execute() {
		if (myParameters.get(0) != 0 && myCommandLists.get(0).size() != 0) {
			runList.addAll(myCommandLists.get(0));
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "If";
	}
}