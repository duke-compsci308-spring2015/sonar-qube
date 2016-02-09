package model.commands;

public class IfElse extends SpecialCommand {
	public IfElse() {
		super();
	}

	public double execute() {
		if (myCommandLists.get(0).size() != 0) {
			if (myParameters.get(0) != 0) {
				runList.addAll(myCommandLists.get(0));
				return -1;
			} else {
				runList.addAll(myCommandLists.get(1));
				return -1;
			}
		}
		return 0;
	}

	@Override
	public String toString() {
		return "IfElse";
	}
}