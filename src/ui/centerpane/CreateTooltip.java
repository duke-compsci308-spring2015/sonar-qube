package ui.centerpane;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import model.interfaces.IPenUpDown;
import model.interfaces.ITurtleProperties;

public class CreateTooltip {

	private ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/centerResource");

	public void update(ITurtleProperties tp, IPenUpDown pi, Node node) {
		String penStatus;
		if (pi.isPenDown())
			penStatus = r.getString("penDown");
		else
			penStatus = r.getString("penUp");
		Tooltip tip = new Tooltip();
		tip.setText("Turtle X Coordinate: " + tp.getX() + "\n" + "Turtle Y Coordinate: " + tp.getY() + "\n" + 
				"Heading: " + tp.absoluteAngleFrontend() + "\n" + "Pen: " + penStatus + "\n" + "Active: " + tp.isActive()
				+ "\n" + "ID: " + tp.getID());
		Tooltip.install(node, tip);
	}
}


