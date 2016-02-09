package ui.leftpane;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.text.Text;
import model.commands.UserCommand;
import model.interfaces.PassToFrontInterface;
import ui.interfaces.IFront;

import java.util.*;

public class LeftContent implements IFront {
	private ListViewHandler myListViewHandler;
	private List<ObservableList<String>> myListViewObservable;
	private ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/LeftResource");
	private List<ListView<String>> myListViewObjects;
	private PassToFrontInterface pInterface;
	
	public LeftContent(PassToFrontInterface pf) {
		this.pInterface = pf;
	}
	
	public Group makeListViews() {
		myListViewHandler = new ListViewHandler();
		TextHandler myTextHandler = new TextHandler();
		double[] prefWidth = {Double.parseDouble(r.getString("prefTopWidth")), Double.parseDouble(r.getString("prefBottomWidth")), Double.parseDouble(r.getString("prefBottomWidth"))};
		double[] prefHeight = {Double.parseDouble(r.getString("prefHeight")), Double.parseDouble(r.getString("prefHeight")), Double.parseDouble(r.getString("prefHeight"))};
		double[] translateYPane = {Double.parseDouble(r.getString("translateTopY")), Double.parseDouble(r.getString("translateBottomY")), Double.parseDouble(r.getString("translateBottomY"))};
		double[] translateXPane = {Double.parseDouble(r.getString("translateTopX")), Double.parseDouble(r.getString("translateBottomLeftX")), Double.parseDouble(r.getString("translateBottomRightX"))};
		myListViewObjects = myListViewHandler.createListView(3, prefWidth, prefHeight, translateYPane, translateXPane);
		myListViewObservable = myListViewHandler.getObsList();
		ListView<String> variableValueList = myListViewObjects.get(2);
		variableValueList.setEditable(true);
		variableValueList.setCellFactory(TextFieldListCell.forListView());

		String[] titles = {r.getString("topTitle"), r.getString("bottomTitle")};
		double[] yCor = {Double.parseDouble(r.getString("yTopCor")), Double.parseDouble(r.getString("yBottomCor"))};
		double[] xCor = {Double.parseDouble(r.getString("xTopCor")), Double.parseDouble(r.getString("xBottomCor"))};
		Text[] textArr = myTextHandler.createTextObjects(2, titles, yCor, xCor);

		Group root = new Group();
		addToRoot(myListViewObjects, textArr, root);

		return root;
	}
	private ObservableList<String> getListViewObservable(int index) {
		return myListViewObservable.get(index);
	}
	private ListView<String> getListView(int index) {
		return myListViewObjects.get(index);
	}
	
	private void addToRoot(List<ListView<String>> listView, Text[] textArr, Group root) {
		for (ListView<String> list: listView) {
			root.getChildren().add(list);
		}
		for (Text text: textArr) {
			root.getChildren().add(text);
		}
	}

	@Override
	public void update() {
		ListView<String> variableNames = this.getListView(1);
		ObservableList<String> varNames = this.getListViewObservable(1);
		ListView<String> variableVals = this.getListView(2); 
		ObservableList<String> varObs = this.getListViewObservable(2);
		Map<String, Double> updatedMap = pInterface.getVariableMap();
		varObs.clear();
		varNames.clear();
		for (String s : updatedMap.keySet()) {
			varObs.add(updatedMap.get(s).toString());
			varNames.add(s);
		}
		variableVals.setItems(varObs);
		variableNames.setItems(varNames);
		
		
		ListView<String> userDefinedNames = this.getListView(0); 
		ObservableList<String> userDefined = this.getListViewObservable(0);
		Map<String, UserCommand> userCommands = pInterface.getUserDefinedCommands();
		userDefined.clear();
		for (String s: userCommands.keySet()) {
			userDefined.add(s);
		}
		userDefinedNames.setItems(userDefined); 
	}
}
