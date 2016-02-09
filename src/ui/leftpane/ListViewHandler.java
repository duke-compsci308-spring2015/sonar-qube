package ui.leftpane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.util.*;

public class ListViewHandler {
	private List<ObservableList<String>> obsList;
	
	public ListViewHandler() {
		obsList = new ArrayList<ObservableList<String>>();
	}
	
	public List<ListView<String>> createListView(int n, double[] prefWidth, double[] prefHeight, double[] translateY, double[] translateX) {
		List<ListView<String>> myList = new ArrayList<ListView<String>>();
		
		for (int i = 0; i < n; i++) {
			ListView<String> list = new ListView<String>();
			ObservableList<String> observable = FXCollections.observableArrayList();
			list.setPrefSize(prefWidth[i], prefHeight[i]);
			list.setTranslateY(translateY[i]);
			list.setTranslateX(translateX[i]);
			obsList.add(observable);
			list.setItems(obsList.get(i));
			myList.add(list);
		}
		return myList;
	}
	
	public List<ObservableList<String>> getObsList() {
		return obsList;
	}
}
