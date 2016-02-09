package model.commands;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.CommandInputException;

public class UserCommand extends SpecialCommand {
	private String myName;
	private List<String> myDefinition;

	public UserCommand(String name) {
		myName = name;
		myDefinition = new ArrayList<String>();
	}
	
	public UserCommand(String name, String paramCode, ArrayList<String> definition, ArrayList<String> variables) {
		myName = name;
		myParameterCode = paramCode;
		myDefinition = definition;
		myVariables = variables;
	}

	public List<String> assignValuesToCommandList(double... params) throws CommandInputException {
		List<String> tempList = new ArrayList<String>();
		for (int i = 0; i < myDefinition.size(); i++) {
			String current = myDefinition.get(i);
			if (isVariable(current)) {
				if (myVariables.contains(current)) {
					int index = myVariables.indexOf(current);
					tempList.add(Double.toString(params[index]));
				} else if (variableMap.containsKey(current)) {
					tempList.add(current);
				} else {
					throw new CommandInputException(current);
				}
			} else {
				tempList.add(current);
			}
		}
		return tempList;
	}

	@Override
	public double execute() throws CommandInputException {
		double[] params = new double[getNumberOfParameters()];
		for (int i = 0; i < params.length; i++) {
			params[i] = myParameters.remove(0);
		}
		runList = assignValuesToCommandList(params);

		return -1;
	}

	public void setParameterCode(int numParams) {
		myParameterCode = "";
		for (int i = 0; i < numParams; i++) {
			myParameterCode += "e";
		}
	}

	@Override
	public String getParameterCode() {
		return myParameterCode;
	}

	public int getNumberOfParameters() {
		return myParameterCode.length();
	}

	public List<String> getDefinition() {
		return myDefinition;
	}

	@Override
	public void addListOfCommands(List<String> cList) {
		myDefinition = new ArrayList<String>(cList);
	}

	private boolean isVariable(String s) {
		return s.matches(":[a-zA-Z_]+");
	}

	public String getName() {
		return myName;
	}

	@Override
	public String toString() {
		return "UserCommand";
	}
}