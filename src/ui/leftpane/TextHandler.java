package ui.leftpane;

import javafx.scene.text.Text;

public class TextHandler {
	public Text[] createTextObjects(int n, String[] titles, double[] yCor, double[] xCor) {
		Text[] myTextArr = new Text[n];
		for (int i = 0; i < n; i++) {
			Text textObj = new Text();
			textObj.setText(titles[i]);
			textObj.setY(yCor[i]);
			textObj.setX(xCor[i]);
			myTextArr[i] = textObj;
		}
		return myTextArr;
	}
}
