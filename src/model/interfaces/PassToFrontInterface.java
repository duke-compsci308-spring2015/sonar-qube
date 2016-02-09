package model.interfaces;

import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import model.commands.UserCommand;

public interface PassToFrontInterface {
	public Map<String, Double> getVariableMap();

	public Map<String, UserCommand> getUserDefinedCommands();

	public String getLastCommand();
	
	public Color getUpdatedBackgroundColor();
	
	public Map<Double, Color> getPalette();
	
	public List<ITurtleProperties> getTurtleList();
}