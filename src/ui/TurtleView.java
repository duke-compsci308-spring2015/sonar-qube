package ui;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.bottompane.BottomPane;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import model.commands.ReceiveFromFront;
import model.interfaces.FileInterface;
import model.interfaces.IPenUpDown;
import model.interfaces.ITurtleProperties;
import model.interfaces.PassToFrontInterface;
import model.interfaces.StampInterface;
import ui.bottompane.CommandPrompt;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IFront;
import ui.leftpane.LeftContent;
import ui.rightpane.CommandHistory;
import ui.toppane.MenuHandler;


public class TurtleView {
	private Scene myScene;
	private ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TurtleViewResource");
	private List<IFront> myFrontObjects;
	private PassToFrontInterface pf;
	List<ITurtleProperties> tp;
	IPenUpDown pUD;
	StampInterface si;
	ReceiveFromFront rf;
	FileInterface fi;
	
	public TurtleView(PassToFrontInterface pass, List<ITurtleProperties> turtleProps, IPenUpDown penUpDown, StampInterface stamp,
			ReceiveFromFront receive, FileInterface file) {
		this.pf = pass;
		this.tp = turtleProps;
		this.pUD = penUpDown;
		this.si = stamp;
		this.rf = receive;
		this.fi = file;
		
		
		myFrontObjects = new ArrayList<IFront>();
		BorderPane myPane = new BorderPane();
		LeftContent left = new LeftContent(pf);
		CommandHistory history = new CommandHistory(pf);
		DisplayTurtle turtleDisplay = new DisplayTurtle(tp, pUD, pf, si, rf);
		myFrontObjects.add(left);
		myFrontObjects.add(history);
		myFrontObjects.add(turtleDisplay);
		MenuHandler menu = new MenuHandler(turtleDisplay, rf, pf, fi);
		myFrontObjects.add(menu);

		CommandPrompt prompt = new CommandPrompt(myFrontObjects);
		BottomPane bottomController = new BottomPane(left, history, turtleDisplay);
		myPane.setCenter(turtleDisplay.getGroup());
		myPane.setLeft(left.makeListViews());
		myPane.setRight(history.makeListView(prompt.getField()));
		myPane.setTop(menu.makeMenuBar());
		myPane.setBottom(prompt.makeCommandPromptArea(bottomController, rf, pf));
		myScene = new Scene(myPane, Integer.parseInt(r.getString("sceneWidth")), Integer.parseInt(r.getString("sceneHeight")));
	}
	
	public Scene getScene() {
		return myScene;
	}
	
}