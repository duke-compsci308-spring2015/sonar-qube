package controller.toppane;

import model.commands.ReceiveFromFront;
import model.exceptions.CommandInputException;
import model.exceptions.MathException;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;

public class UpdateBackgroundColor {
	private DisplayTurtle turtle;
	private ReceiveFromFront rf;
	private PassToFrontInterface pf;
	public UpdateBackgroundColor(DisplayTurtle t, ReceiveFromFront receive, PassToFrontInterface pass) {
		this.turtle = t;
		rf = receive;
		pf = pass;
	}


	public void changeBackgroundAction(String index) {
		try {
			rf.receiveCommand("setbg " + index);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommandInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		turtle.getGC().setFill(pf.getUpdatedBackgroundColor());
		turtle.getGC().fillRect(0, 0, 500, 500);
		turtle.update();

	}
}
