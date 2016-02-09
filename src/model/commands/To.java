package model.commands;

import java.util.List;

import model.exceptions.CommandInputException;

public class To extends SpecialCommand {
	private UserCommand userCommand;

	public To() {
		super();
	}

	public void createUserDefinedCommand(String name) {
		userCommand = new UserCommand(name);
	}

	public UserCommand getUserCommand() {
		return userCommand;
	}

	@Override
	public void addVariable(String var) {
		myVariables.add(var);
		userCommand.addVariable(var);
	}

	@Override
	public void addListOfCommands(List<String> cList) {
		myCommandLists.add(cList);
		userCommand.addListOfCommands(cList);
	}

	public double execute() throws CommandInputException {
		userCommand.setParameterCode(userCommand.getVariableList().size());
		int numParams = userCommand.getNumberOfParameters();
		double[] params = new double[numParams];
		for (int i = 0; i < numParams; i++) {
			params[i] = 0.0;
		}
		runList = userCommand.assignValuesToCommandList(params);
		return -1;
	}

	@Override
	public String toString() {
		return "MakeUserInstruction";
	}
}