package ui.bottompane;

import javafx.scene.control.Alert;

public class Custom_Alert extends Alert {

	public Custom_Alert (Alert.AlertType type, String title, String headerText) {
		super(type);
		this.setTitle(title);
		this.setHeaderText(headerText);
		
	}

	
}
