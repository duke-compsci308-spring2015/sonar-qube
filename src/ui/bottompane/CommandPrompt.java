package ui.bottompane;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import model.commands.ReceiveFromFront;
import model.exceptions.CommandInputException;
import model.exceptions.MathException;
import model.interfaces.PassToFrontInterface;
import ui.interfaces.IFront;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.bottompane.BottomPane;

public class CommandPrompt implements IFront {
	private TextArea field;

	private ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/bottomResource");
	private List<IFront> frontObjects;
	public CommandPrompt(List<IFront> front) {
		field = new TextArea();
		frontObjects = front;
	}

	
	public TextArea getField() {
		return field;
	}
	
	public Group makeCommandPromptArea(BottomPane bottomController, ReceiveFromFront rs, PassToFrontInterface pf) {
		Group root = new Group();

		ButtonHandler buttonHandler = new ButtonHandler();
		Button[] buttonArr;
		field.setPrefSize(Double.parseDouble(r.getString("inputBoxWidth")), Double.parseDouble(r.getString("inputBoxHeight")));
		String[] titles = {r.getString("runTitle"), r.getString("clearTitle")};
		double[] translateX = {Double.parseDouble(r.getString("runTranslateX")), Double.parseDouble(r.getString("clearTranslateX"))};
		double[] translateY = {Double.parseDouble(r.getString("runTranslateY")), Double.parseDouble(r.getString("clearTranslateY"))};
		buttonArr = buttonHandler.makeButtons(2, titles, translateX, translateY);
		Button clear = buttonArr[Integer.parseInt(r.getString("clearButtonIndex"))];
		Button run = buttonArr[Integer.parseInt(r.getString("runButtonIndex"))];

		field.setOnKeyPressed(event -> bottomController.handleKeyInput(event.getCode(), field));
		
		clear.setOnAction((event) -> {
			bottomController.clearButtonAction(field);
		}); 
		List<Node> nodeList = new ArrayList<Node>();
		nodeList.add(field);
		for (Button button: buttonArr)
			nodeList.add(button);
		root.getChildren().addAll(nodeList);
		run.setOnAction((event) -> {
			try {
				bottomController.runButtonAction(field.getText(), frontObjects, rs); 
				this.update();
			}
				//bottomController.runButtonAction(field, rs);
			 catch (CommandInputException e) {
				Custom_Alert alert = new Custom_Alert(AlertType.WARNING, r.getString("errorString"), r.getString("invalid"));
				if (e.getBadInput().isEmpty()) {
					alert.setContentText(r.getString("parameters"));
				} else {
					alert.setContentText("Please check your spelling of \"" + e.getBadInput() + "\".");
				}
				alert.showAndWait();
			}
			catch (MathException e) {
				Custom_Alert alert = new Custom_Alert(AlertType.WARNING, r.getString("errorString"), r.getString("math"));
				alert.setContentText(e.getErrorMessage()); 
				alert.showAndWait();
			}
			
			
		});
		return root;
	}
	@Override
	public void update() {
		field.clear();		
	}
	
}
