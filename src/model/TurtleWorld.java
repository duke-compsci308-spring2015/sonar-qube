package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import model.commands.Command;
import model.commands.ReceiveFromFront;
import model.commands.UserCommand;
import model.data.Pen;
import model.data.Stamp;
import model.data.Turtle;
import model.data.TurtleManager;
import model.data.UserDefinedCommands;
import model.data.UserDefinedVariables;
import model.exceptions.CommandInputException;
import model.exceptions.MathException;
import model.interfaces.FileInterface;
import model.interfaces.IPenUpDown;
import model.interfaces.ITurtleProperties;
import model.interfaces.PassToFrontInterface;
import model.interfaces.StampInterface;
import model.parser.Parser;
import model.xml.XmlReader;
import model.xml.XmlWriter;

public class TurtleWorld implements ReceiveFromFront, PassToFrontInterface, FileInterface, StampInterface, IPenUpDown {
	private UserDefinedCommands userDefinedCommands;
	private UserDefinedVariables variables;
	private Parser parser;
	private TurtleManager turtleManager;
	private XmlWriter xmlWriter;
	private XmlReader xmlReader;
	private String myInput;
	private String myLanguage;
	private Pen myPen;

	public TurtleWorld() {
		turtleManager = new TurtleManager();
		userDefinedCommands = new UserDefinedCommands();
		variables = new UserDefinedVariables();
		parser = new Parser(userDefinedCommands, variables, turtleManager);
		myPen = new Pen();
		Turtle.setPen(myPen);
		Command.setMapsAndPen(userDefinedCommands, variables, myPen);
		xmlWriter = new XmlWriter();
		xmlReader = new XmlReader();
		myInput = "";
		myLanguage = "English";
	}

	public void interpretInput(List<String> inputList) throws CommandInputException, MathException {
		Command.setMapsAndPen(userDefinedCommands, variables, myPen);
		parser.processInput(inputList);
	}

	private List<String> removeCommentsAndWhitespace(String input) {
		String processedInput = "";
		for (int i = 0; i + 1 <= input.length(); i++) {
			if (input.substring(i, i + 1).equals("#")) {
				i++;
				while (i + 1 < input.length() && !input.substring(i, i + 1).equals("\n")) {
					i++;
				}
			} else {
				processedInput += input.substring(i, i + 1);
			}
		}
		String[] inputArray = processedInput.trim().split("\\s+");
		List<String> inputList = new ArrayList<String>(Arrays.asList(inputArray));
		return inputList;
	}

	public Turtle getTurtle() {
		return turtleManager.firstTurtle();
	}

	@Override
	public void receiveCommand(String input) throws CommandInputException, MathException {
		myInput = input;
		interpretInput(removeCommentsAndWhitespace(input));
	}

	@Override
	public void receiveLanguage(String language) {
		myLanguage = language;
		parser.processLanguage(language);
	}

	@Override
	public Map<String, Double> getVariableMap() {
		return variables.getVariableMap();
	}

	@Override
	public String getLastCommand() {
		return myInput;
	}

	@Override
	public Map<String, UserCommand> getUserDefinedCommands() {
		return userDefinedCommands.getCommandMap();
	}

	@Override
	public Color getUpdatedBackgroundColor() {
		return myPen.getBackgroundColor();
	}

	@Override
	public Map<Double, Color> getPalette() {
		return myPen.getPalette();
	}

	public void readLibraryXmlFile(String path) {
		xmlReader.receiveVariablesAndCommandsAccess(variables, userDefinedCommands);
		xmlReader.readLibraryFile(path);
	}

	public void writeLibraryXmlFile(String path) {
		xmlWriter.receiveVariablesAndCommands(variables, userDefinedCommands);
		xmlWriter.writeLibraryFile(path);
	}

	public void readWorkspaceXmlFile(String path) {
		xmlReader.receiveWorkspaceAccess(turtleManager, myPen);
		xmlReader.readWorkspaceFile(path);
		receiveLanguage(xmlReader.getLanguageRead());
	}

	public void writeWorkspaceXmlFile(String path) {
		xmlWriter.receiveLanguage(myLanguage);
		xmlWriter.receiveWorkspaceInformation(turtleManager, myPen);
		xmlWriter.writeWorkspaceFile(path);
	}

	@Override
	public List<Stamp> getStampList() {
		return myPen.getStampList();
	}

	@Override
	public List<ITurtleProperties> getTurtleList() {
		return turtleManager.getTurtleList();
	}

	@Override
	public boolean isPenDown() {
		return myPen.isPenDown();
	}


	@Override
	public void receiveImageString(String s) {
		myPen.setCurrentImage(s);
	}
}