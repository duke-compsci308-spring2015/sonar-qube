package ui.rightpane;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.interfaces.PassToFrontInterface;
import ui.interfaces.IFront;
import ui.leftpane.ListViewHandler;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.ResourceBundle;

public class CommandHistory implements IFront {
	
	private ListViewHandler listViewHandler;
	private List<ListView<String>> commandHist;
	private PassToFrontInterface pInterface;
	private ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/RightResource");
	
	public CommandHistory(PassToFrontInterface pf) {
		listViewHandler = new ListViewHandler();
		this.pInterface = pf;
	}
	
	public Group makeListView(TextArea field) {
		Group root = new Group();
		Text title = new Text();
		title.setText("Command History");
		title.setTranslateX(Double.parseDouble(r.getString("translateX")));
		title.setTranslateY(Double.parseDouble(r.getString("translateY")));
		double[] prefWidth = {Double.parseDouble(r.getString("prefWidth"))};
		double[] prefHeight = {Double.parseDouble(r.getString("prefHeight"))};
		double[] translateY = {Double.parseDouble(r.getString("historyTranslateY"))};
		double[] translateX = {Double.parseDouble(r.getString("historyTranslateX"))};
		commandHist = listViewHandler.createListView(1, prefWidth, prefHeight, translateY, translateX);
		for (ListView<String> list: commandHist) {
			list.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> field.setText(newVal));
			root.getChildren().add(list);
		}
		root.getChildren().add(title);
		return root;
	}

	@Override
	public void update() {
		String lastCommand = pInterface.getLastCommand();
		for (int i = 0; i < listViewHandler.getObsList().size(); i++) {
			ObservableList<String> obs = listViewHandler.getObsList().get(i); 
			obs.add(lastCommand);
			commandHist.get(i).setItems(obs);
		}
	}

}
