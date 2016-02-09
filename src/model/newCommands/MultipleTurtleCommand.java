package model.newCommands;

import java.util.List;

import model.commands.Command;
import model.commands.SpecialCommand;

public abstract class MultipleTurtleCommand extends SpecialCommand {
	public abstract List<Integer> getTurtleList();
}
