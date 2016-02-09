package model.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import model.commands.UserCommand;
import model.data.Pen;
import model.data.Turtle;
import model.data.TurtleManager;
import model.data.UserDefinedCommands;
import model.data.UserDefinedVariables;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class XmlReader {
	private UserDefinedVariables userDefinedVariables;
	private UserDefinedCommands userDefinedCommands;
	private TurtleManager turtleManager;
	private Pen pen;
	private String language;

	public void readLibraryFile(String path) {
		// Initial steps to read in XML file
		Document doc = generateDocument(path);

		// Read the variables and add them to the variable map
		readUserDefinedVariables(doc);

		// Read the commands and add them to the command map
		readUserDefinedCommands(doc);
		
	}
	
	public void readWorkspaceFile(String path) {
		Document doc = generateDocument(path);
		
		readBackgroundColor(doc);
		
		readLanguage(doc);
		
		readTurtles(doc);
		
		readPenColor(doc);
	}

	private Document generateDocument(String path) {
		try {
			File libraryFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(libraryFile);
			doc.getDocumentElement().normalize();
			return doc;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getElementFromParent(Element parent, String name) {
		return parent.getElementsByTagName(name).item(0).getTextContent();
	}
	
	private void readBackgroundColor(Document doc) {
		NodeList backgroundList = doc.getElementsByTagName("BackgroundColor");
		String unconvertedBackground = backgroundList.item(0).getTextContent();
		double backgroundColorIndex = Double.parseDouble(unconvertedBackground);
		pen.setBackgroundColor(backgroundColorIndex);
	}
	
	private void readLanguage(Document doc) {
		NodeList languageList = doc.getElementsByTagName("Language");
		language = languageList.item(0).getTextContent();
	}
	

	
	private void readUserDefinedVariables(Document doc) {
		NodeList variableList = doc.getElementsByTagName("Variable");
		for (int i = 0; i < variableList.getLength(); i++) {
			Node variable = variableList.item(i);

			if (variable.getNodeType() == Node.ELEMENT_NODE) {

				Element eVariable = (Element) variable;
				String variableName = getElementFromParent(eVariable, "VariableName");
				double variableDefinition = Double.parseDouble(getElementFromParent(eVariable, "VariableDefinition"));

				userDefinedVariables.addVariable(variableName, variableDefinition);
			}

		}
	}
	
	private void readTurtles(Document doc) {
		// Reset all data structures keeping track of turtles
		Turtle.resetTurtleCount();
		turtleManager.resetTurtleMap();
		turtleManager.resetActiveList();
		
		NodeList turtleList = doc.getElementsByTagName("Turtle");
		for (int i = 0; i < turtleList.getLength(); i++) {
			Node turtle = turtleList.item(i);
			
			if (turtle.getNodeType() == Node.ELEMENT_NODE) {
				Element eTurtle = (Element) turtle;
				
				String unconvertedXCoord = getElementFromParent(eTurtle, "XCoord");
				double xCoord = Double.parseDouble(unconvertedXCoord);
				
				String unconvertedYCoord = getElementFromParent(eTurtle, "YCoord");
				double yCoord = Double.parseDouble(unconvertedYCoord);
				
				String unconvertedAngle = getElementFromParent(eTurtle, "Angle");
				double angle = Double.parseDouble(unconvertedAngle);
				
				String unconvertedId = getElementFromParent(eTurtle, "ID");
				int id = Integer.parseInt(unconvertedId);
				
				Turtle turtleObject = new Turtle(id);
				turtleObject.setX(xCoord);
				turtleObject.setY(yCoord);
				turtleObject.setAngle(angle);
				
				turtleManager.addToTurtleMap(turtleObject);
				turtleManager.addToActiveList(turtleObject);
			}
		}
	}
	
	private void readPenColor(Document doc) {
		NodeList penColorList = doc.getElementsByTagName("PenColor");
		String unconvertedPenColor = penColorList.item(0).getTextContent();
		double penColorIndex = Double.parseDouble(unconvertedPenColor);
		pen.setPenColor(penColorIndex);
	}
	
	private void readUserDefinedCommands (Document doc) {
		NodeList commandList = doc.getElementsByTagName("Command");

		for (int i = 0; i < commandList.getLength(); i++) {
			Node command = commandList.item(i);

			if (command.getNodeType() == Node.ELEMENT_NODE) {
				Element eCommand = (Element) command;
				String commandName = getElementFromParent(eCommand, "CommandName");

				String commandParamCode = getElementFromParent(eCommand, "CommandParamCode");

				String unconvertedDefinition = getElementFromParent(eCommand, "CommandDefinition");
				ArrayList<String> commandDefinition = new ArrayList<String>(Arrays.asList(unconvertedDefinition.split(" ")));

				String unconvertedVariables = getElementFromParent(eCommand, "CommandVariables");
				ArrayList<String> commandVariables = new ArrayList<String>(Arrays.asList(unconvertedVariables.split(" ")));

				UserCommand commandObject = new UserCommand(commandName, commandParamCode, commandDefinition, commandVariables);
				userDefinedCommands.addCommand(commandObject);
			}
		}
	}
	
	public String getLanguageRead() {
		return language;
	}
	
	public void receiveVariablesAndCommandsAccess(UserDefinedVariables variables, UserDefinedCommands commands) {
		userDefinedVariables = variables;
		userDefinedCommands = commands;
	}
	
	public void receiveWorkspaceAccess(TurtleManager turtleManager, Pen pen) {
		this.turtleManager = turtleManager;
		this.pen = pen;
	}
	
}
