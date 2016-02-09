package controller.toppane;

import model.commands.ReceiveFromFront;
import model.exceptions.CommandInputException;
import model.exceptions.MathException;

public class UpdatePenColor {
	private ReceiveFromFront rf;
	public UpdatePenColor(ReceiveFromFront receive) {
		rf = receive;
	}
	
	
	public void changePenColorAction(String index) {
		try {
			rf.receiveCommand("setpc " + index);
		} catch (NumberFormatException | CommandInputException | MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
