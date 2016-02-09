package ui.bottompane;

import javafx.scene.control.Button;

public class ButtonHandler {

	public Button[] makeButtons(int n, String[] titles, double[] translateX, double[] translateY) {
		Button[] arrButtons = new Button[n];
		for (int i = 0; i < n; i++) {
			Button button = new Button();
			button.setText(titles[i]);
			button.setTranslateX(translateX[i]);
			button.setTranslateY(translateY[i]);
			arrButtons[i] = button;
		}
		return arrButtons;
	}
}
