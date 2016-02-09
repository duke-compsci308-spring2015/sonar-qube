package model.commands;

public class Make extends Command {
	public Make() {
		super();
	}

	public double execute() {
		variableMap.addVariable(myVariables.get(0), myParameters.get(0));
		return myParameters.get(0);
	}

	@Override
	public String toString() {
		return "MakeVariable";
	}
}