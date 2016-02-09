package controller.toppane;

import model.commands.ReceiveFromFront;
import model.exceptions.CommandInputException;
import model.exceptions.MathException;
import ui.centerpane.DisplayTurtle;

public class UpdatePenProperties {
	
	private ReceiveFromFront rf;
	private DisplayTurtle displayTurt;
	public UpdatePenProperties(DisplayTurtle display, ReceiveFromFront receive) {
		displayTurt = display;
		rf = receive;
	}
	public void changePenUpDown(String text) {
		if (text.equals("Pen Up"))
			try {
				rf.receiveCommand("pu");
				displayTurt.update(); //update tool tip
			} catch (NumberFormatException | CommandInputException | MathException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				rf.receiveCommand("pd");
				displayTurt.update(); //update tool tip
			} catch (NumberFormatException | CommandInputException | MathException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void changeLineType(String text) {
		
	}
	
	public void changePenThickness(String text) {
		try {
			rf.receiveCommand("setps " + text);
		} catch (NumberFormatException | CommandInputException | MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
