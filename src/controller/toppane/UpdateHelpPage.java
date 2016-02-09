package controller.toppane;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateHelpPage {
	WebView browser = new WebView();
	WebEngine webEngine = browser.getEngine();
	
	public void openPage(String webPage) {
		createPopup(browser);
		webEngine.load(webPage);
	}
	
	private void createPopup(WebView w) {
		FlowPane pane = new FlowPane();
		pane.getChildren().add(w);
		Scene s = new Scene(pane, 800, 600);
		Stage newStage = new Stage();
		newStage.setScene(s);
		newStage.initModality(Modality.NONE);
		newStage.show();
	}
}
