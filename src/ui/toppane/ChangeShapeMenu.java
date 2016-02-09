package ui.toppane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IMenu;

public class ChangeShapeMenu extends AbstractMenu implements IMenu {

	ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TopResource");

	@Override
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf) {
		Menu changeShape = new Menu(r.getString("changeShape"));
		List<String> shapeItems = new ArrayList<String>();
		shapeItems.add(r.getString("circle"));
		List<String> shapeImages = new ArrayList<String>();
		shapeImages.add(r.getString("circleImage"));
		Rectangle[] shapeRects = makeImageNodes(shapeImages, Integer.parseInt(r.getString("shapeImageWidth")), Integer.parseInt(r.getString("shapeImageHeight")));
		addMenuItem(changeShape, shapeItems, shapeRects);
		return changeShape;
	}

	
}
