package model.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class SetMultipleTurtlesCommand extends Command {
	protected List<Integer> turtleList = new ArrayList<Integer>();
	
	public abstract double execute();
	
	public abstract String toString();
	
	public void addTurtle(Integer i) {
		turtleList.add(i);
	}
	
	public List<Integer> getTurtleList() {
		return turtleList;
	}
}