package model.commands;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.data.Pen;
import model.data.Turtle;
import model.data.UserDefinedCommands;
import model.data.UserDefinedVariables;
import model.exceptions.CommandInputException;
import model.exceptions.MathException;

public abstract class Command {
	protected Turtle myTurtle;
	protected String myParameterCode;
	protected List<Double> myParameters = new ArrayList<Double>();
	protected List<String> myVariables = new ArrayList<String>();
	protected List<List<String>> myCommandLists = new ArrayList<List<String>>();
	protected static UserDefinedCommands userDefinedCommands;
	protected static UserDefinedVariables variableMap;
	protected static Pen pen;
	protected final ResourceBundle ParameterCode = ResourceBundle.getBundle("resources/modelproperties/ParameterCode");

	public Command() {
		myParameterCode = ParameterCode.getString(toString());
	}

	public abstract String toString();

	public abstract double execute() throws CommandInputException, MathException;

	public String getParameterCode() {
		return myParameterCode;
	}

	public void addParameter(double param) {
		myParameters.add(param);
	}

	public void addVariable(String var) {
		myVariables.add(var);
	}

	public List<String> getVariableList() {
		return myVariables;
	}

	public void addListOfCommands(List<String> cList) {
		myCommandLists.add(cList);
	}

	public List<String> getListOfCommands() {
		return myCommandLists.get(0);
	}

	public void setTurtle(Turtle turtle) {
		myTurtle = turtle;
	}

	public double executeAndFormat() throws CommandInputException, MathException {
		DecimalFormat df = new DecimalFormat("#.#####");
		double value = Double.valueOf(df.format(execute()));
		try {
			if (Double.valueOf(df.format(value)) == 0.00000)
				return 0;
		} catch (NumberFormatException e) {
			throw new CommandInputException("(Number Formatting)");
		}
		return value;
	}
	
	public static void setMapsAndPen(UserDefinedCommands uMap, UserDefinedVariables vMap, Pen p) {
		userDefinedCommands = uMap;
		variableMap = vMap;
		pen = p;
	}
}