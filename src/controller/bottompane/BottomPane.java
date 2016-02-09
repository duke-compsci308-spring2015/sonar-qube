package controller.bottompane;

import java.util.*;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import model.commands.ReceiveFromFront;
import model.exceptions.CommandInputException;
import model.exceptions.MathException;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IFront;
import ui.leftpane.LeftContent;
import ui.rightpane.CommandHistory;

public class BottomPane {
	private List<String> commandHistory;

	public BottomPane(LeftContent leftCont, CommandHistory right, DisplayTurtle display) {
		this.commandHistory = new ArrayList<String>();
	}

	public void clearButtonAction(TextArea field) {
		field.clear();
	}

	public void handleKeyInput(KeyCode code, TextArea field) {
		if (code.equals(KeyCode.UP)) {
			// empty text area, which means should load last command
			if (field.getText().equals("")) {
				field.setText(commandHistory.get(commandHistory.size() - 1));
			} else if (commandHistory.contains(field.getText())) { // text area
																	// has
																	// command
																	// previously
																	// stored in
																	// list
				// first command, continue to show first command
				if (commandHistory.indexOf(field.getText()) == 0) {
					field.setText(commandHistory.get(0));
				}
				// command is somehwere else in list, show previous command
				String command = field.getText();
				int index = commandHistory.indexOf(command);
				if (index - 1 >= 0)
					field.setText(commandHistory.get(index - 1));

			}
		} else if (code.equals(KeyCode.DOWN)) {
			if (!field.getText().equals("")) {
				String command = field.getText();
				int index = commandHistory.indexOf(command);
				if (index + 1 < commandHistory.size())
					field.setText(commandHistory.get(index + 1));
				else
					field.clear();
			}
		}
	}

	public void runButtonAction(String command, List<IFront> list, ReceiveFromFront rs) throws CommandInputException, MathException {
		rs.receiveCommand(command);
		for (IFront pane: list) {
			pane.update();
		}
	}
}
