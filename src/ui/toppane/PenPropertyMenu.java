package ui.toppane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.toppane.UpdatePenProperties;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IMenu;

public class PenPropertyMenu extends AbstractMenu implements IMenu {

	ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TopResource");

	@Override
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf) {
		Menu penProperties = new Menu(r.getString("penPropertyTitle"));
		Menu penUpDown = new Menu(r.getString("penUpDown"));
		List<String> penUpDownOptions = new ArrayList<String>();
		penUpDownOptions.add(r.getString("penUp"));
		penUpDownOptions.add(r.getString("penDown"));

		List<String> arrowOptions = new ArrayList<String>();
		arrowOptions.add(r.getString("penUpImage"));
		arrowOptions.add( r.getString("penDownImage"));
		UpdatePenProperties updatePenProp = new UpdatePenProperties(display, rf);
		Rectangle[] arrowRects = makeImageNodes(arrowOptions, Integer.parseInt(r.getString("penStatusImageWidth")),Integer.parseInt(r.getString("penStatusImageHeight")));
		addMenuItem(penUpDown, penUpDownOptions, arrowRects);
		for (MenuItem m : penUpDown.getItems()) {
			m.setOnAction((event) -> updatePenProp.changePenUpDown(m.getText()));
		}
		
		
		Menu penSize = new Menu(r.getString("penSize"));
		List<String> penThicknessOptions = new ArrayList<String>();
		for (int i = 1; i <= 4; i++) {
			penThicknessOptions.add(r.getString("thickness" + Integer.toString(i)));
		}
		List<String> penThicknessImage = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			penThicknessImage.add(r.getString("thicknessImage"));
		}
		Rectangle[] penThicknessRects = makeImageNodes(penThicknessImage, Integer.parseInt(r.getString("sizeImageWidth")), Integer.parseInt(r.getString("sizeImageHeight")));
		addMenuItem(penSize, penThicknessOptions, penThicknessRects);
		for (MenuItem m : penSize.getItems()) {
			m.setOnAction((event) -> updatePenProp.changePenThickness(m.getText()));
		}
		
		
		
		Menu penType = new Menu(r.getString("penLineType"));
		List<String> lineTypes = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			lineTypes.add(r.getString("lineType" + Integer.toString(i)));
		}
		List<String> lineTypeImage = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			lineTypeImage.add(r.getString("lineImage" + Integer.toString(i)));
		}
		Rectangle[] lineRects = makeImageNodes(lineTypeImage, Integer.parseInt(r.getString("lineImageWidth")), Integer.parseInt(r.getString("lineImageHeight")));
		addMenuItem(penType, lineTypes, lineRects);
		for (MenuItem m : penType.getItems()) {
			updatePenProp.changeLineType(m.getText());
		}
		
		penProperties.getItems().addAll(penUpDown, penSize, penType);
		return penProperties;
	}

}
