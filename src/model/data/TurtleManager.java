package model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javafx.scene.shape.Line;
import model.interfaces.ITurtleProperties;

public class TurtleManager {
	private Map<Integer, Turtle> myTurtleMap;
	private List<Integer> myActiveList;
	private List<Integer> myTemporaryList;
	private List<Line> myLineList;
	private Stack<List<Integer>> temporaryListStack;

	public TurtleManager() {
		Turtle turtle = new Turtle(1);
		myTurtleMap = new HashMap<Integer, Turtle>();
		myTurtleMap.put(turtle.getID(), turtle);
		myActiveList = new ArrayList<Integer>();
		myActiveList.add(1);
		myTemporaryList = new ArrayList<Integer>();
		temporaryListStack = new Stack<List<Integer>>();
	}

	public Turtle firstTurtle() {
		return myTurtleMap.get(1);
	}
	
	public void addToTurtleMap(Turtle t) {
		myTurtleMap.put(t.getID(), t);
	}
	
	public void addToActiveList(Turtle t) {
		myActiveList.add(t.getID());
	}
	
	public void resetTurtleMap() {
		myTurtleMap = new HashMap<Integer, Turtle>();
	}
	
	public void resetActiveList() {
		myActiveList = new ArrayList<Integer>();
	}

	public void setActiveList(List<Integer> activeList) {
		myActiveList.clear();
		setAllTurtlesInactive();
		for (int i = 0; i < activeList.size(); i++) {
			if (!myTurtleMap.containsKey(activeList.get(i))) {
				Turtle turtle = new Turtle(activeList.get(i));
				myTurtleMap.put(turtle.getID(), turtle);
			}
			myTurtleMap.get(activeList.get(i)).setActive(true);
		}
		myActiveList = new ArrayList<Integer>(activeList);
	}
	
	public void setAllTurtlesInactive() {
		for (Integer key : myTurtleMap.keySet()) {
			Turtle turtle = myTurtleMap.get(key);
			turtle.setActive(false);
		}
	}

	public void setTemporaryList(List<Integer> temporaryList) {
		for (int i = 0; i < temporaryList.size(); i++) {
			if (!myTurtleMap.containsKey(temporaryList.get(i))) {
				Turtle turtle = new Turtle(temporaryList.get(i));
				myTurtleMap.put(turtle.getID(), turtle);
			}
		}
		if (temporaryListStack.isEmpty()) {
			myTemporaryList = new ArrayList<Integer>(temporaryList);
		} else {
			temporaryListStack.push(new ArrayList<Integer>(myTemporaryList));
			myTemporaryList = new ArrayList<Integer>(temporaryList);
		}
	}

	public List<Line> getLineList() {
		myLineList.clear();
		for (Integer key : myTurtleMap.keySet()) {
			myLineList.addAll(myTurtleMap.get(key).getLineList());
		}
		return myLineList;
	}

	public List<Turtle> getActiveList() {
		List<Turtle> turtleList = new ArrayList<Turtle>();
		for (Integer num : myActiveList) {
			turtleList.add(myTurtleMap.get(num));
		}
		return turtleList;
	}

	public List<Turtle> getTemporaryList() {
		List<Turtle> turtleList = new ArrayList<Turtle>();
		for (Integer num : myTemporaryList) {
			turtleList.add(myTurtleMap.get(num));
		}
		myTemporaryList.clear();
		return turtleList;
	}
	
	public List<ITurtleProperties> getTurtleList() {
		List<ITurtleProperties> turtleList = new ArrayList<ITurtleProperties>();
		for (Integer key : myTurtleMap.keySet()) {
			Turtle turtle = myTurtleMap.get(key);
			turtleList.add(turtle);
		}
		return turtleList;
	}

	public void reinitializeTurtles() {
		Turtle.resetTurtleCount();
		myTurtleMap.clear();
		myActiveList.clear();
		myTemporaryList.clear();
	}
	
	public boolean tempListIsEmpty() {
		return myTemporaryList.isEmpty();
	}
}