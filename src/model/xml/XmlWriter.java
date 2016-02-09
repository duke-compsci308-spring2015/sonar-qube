package model.xml;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.commands.UserCommand;
import model.data.Pen;
import model.data.Turtle;
import model.data.TurtleManager;
import model.data.UserDefinedCommands;
import model.data.UserDefinedVariables;

public class XmlWriter {

	private Map<String, UserCommand> userCommands;
	private Map<String, Double> userVariables;
	private List<Turtle> turtleList;
	private Pen pen;
	private String language = "English";

	public void writeLibraryFile(String path) {
		// Create the file
		Document doc = createDoc();

		// Root element of file
		Element rootElement = doc.createElement("UserLibrary");
		doc.appendChild(rootElement);

		// Create the UserDefinedVariables section
		addUserVariableSection(doc, rootElement);

		// Create the UserDefinedCommands section
		addUserCommandSection(doc, rootElement);

		// write the content into xml file
		writeFileContent(doc, path);

		System.out.println("Library file saved!");
	}

	public void writeWorkspaceFile(String path) {
		Document doc = createDoc();

		Element rootElement = doc.createElement("WorkspaceSettings");
		doc.appendChild(rootElement);

		//Default background
		addBackgroundColorSection(doc, rootElement);
		
		//Default language
		addLanguageSection(doc, rootElement);
		
		//Turtles
		addTurtleSection(doc, rootElement);
		
		//Starting pen color
		addPenColorSection(doc, rootElement);
		
		writeFileContent(doc, path);
		
		System.out.println("Workspace file saved!");
	}

	private Document createDoc() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// Root element of File
			Document doc = docBuilder.newDocument();
			return doc;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void addBackgroundColorSection(Document doc, Element rootElement) {
		Element backgroundColor = doc.createElement("BackgroundColor");
		rootElement.appendChild(backgroundColor);
		backgroundColor.appendChild(doc.createTextNode(Double.toString(pen.getBackgroundColorIndex())));
	}
	
	private void addLanguageSection(Document doc, Element rootElement) { 
		Element lang = doc.createElement("Language");
		rootElement.appendChild(lang);
		lang.appendChild(doc.createTextNode(language));
	}
	
	private void addTurtleSection(Document doc, Element rootElement) {
		Element turtles = doc.createElement("Turtles");
		rootElement.appendChild(turtles);
		
		//Populate the Turtles section with all the turtles
		for (Turtle t : turtleList) {
			
			Element turtle = doc.createElement("Turtle");
			turtles.appendChild(turtle);
			
			Element xCoord = doc.createElement("XCoord");
			xCoord.appendChild(doc.createTextNode(Double.toString(t.getX())));
			turtle.appendChild(xCoord);
			
			Element yCoord = doc.createElement("YCoord");
			yCoord.appendChild(doc.createTextNode(Double.toString(t.getY())));
			turtle.appendChild(yCoord);
			
			Element angle = doc.createElement("Angle");
			angle.appendChild(doc.createTextNode(Double.toString(t.getAngle())));
			turtle.appendChild(angle);
			
			Element id = doc.createElement("ID");
			id.appendChild(doc.createTextNode(Integer.toString(t.getID())));
			turtle.appendChild(id);
		}
	}
	
	private void addPenColorSection(Document doc, Element rootElement) {
		Element penColor = doc.createElement("PenColor");
		rootElement.appendChild(penColor);
		penColor.appendChild(doc.createTextNode(Double.toString(pen.getPenColorIndex())));
	}

	private void addUserVariableSection(Document doc, Element rootElement) {
		Element variables = doc.createElement("UserVariables");
		rootElement.appendChild(variables);

		// Populate the UserDefinedVariables section with all the variable entries
		for (String variableName : userVariables.keySet()) {

			Element variable = doc.createElement("Variable");
			variables.appendChild(variable);

			Element name = doc.createElement("VariableName");
			name.appendChild(doc.createTextNode(variableName));
			variable.appendChild(name);

			Element definition = doc.createElement("VariableDefinition");
			definition.appendChild(doc.createTextNode(userVariables.get(variableName).toString()));
			variable.appendChild(definition);
		}
	}

	private void addUserCommandSection(Document doc, Element rootElement) {
		Element commands = doc.createElement("UserCommands");
		rootElement.appendChild(commands);

		// Populate the UserDefinedCommands section with all command entries
		for (String commandName : userCommands.keySet()) {

			Element command = doc.createElement("Command");
			commands.appendChild(command);

			Element name = doc.createElement("CommandName");
			name.appendChild(doc.createTextNode(commandName));
			command.appendChild(name);

			Element paramCode = doc.createElement("CommandParamCode");
			String code = userCommands.get(commandName).getParameterCode();
			paramCode.appendChild(doc.createTextNode(code));
			command.appendChild(paramCode);

			Element definition = doc.createElement("CommandDefinition");
			List<String> unconvertedDefinition = userCommands.get(commandName).getDefinition();
			String convertedDefinition = listToString(unconvertedDefinition);
			definition.appendChild(doc.createTextNode(convertedDefinition));
			command.appendChild(definition);

			Element cVariables = doc.createElement("CommandVariables");
			List<String> unconvertedCVariables = userCommands.get(commandName).getVariableList();
			String convertedCVariables = listToString(unconvertedCVariables);
			cVariables.appendChild(doc.createTextNode(convertedCVariables));
			command.appendChild(cVariables);
		}
	}

	private void writeFileContent(Document doc, String path) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));

			transformer.transform(source, result);
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	private String listToString(List<String> definition) {
		StringBuilder sb = new StringBuilder();
		for (String d : definition) {
			sb.append(d);
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public void receiveVariablesAndCommands(UserDefinedVariables variables, UserDefinedCommands commands) {
		userCommands = commands.getCommandMap();
		userVariables = variables.getVariableMap();
	}
	
	public void receiveLanguage(String language) {
		this.language = language;
	}
	
	public void receiveWorkspaceInformation(TurtleManager turtleManager, Pen pen) {
		turtleList = turtleManager.getActiveList();
		this.pen = pen;
	}
}


