package ui.toppane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import controller.toppane.UpdatePenColor;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IMenu;

public class PenColorMenu extends AbstractMenu implements IMenu{
	ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TopResource");

	@Override
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf) {
		Menu penColor = new Menu(r.getString("penTitle"));
		Map<Double, Color> backgroundColorMap = pf.getPalette();
		Color[] colors = new Color[backgroundColorMap.keySet().size()];
		List<String> indices = new ArrayList<String>();
		UpdatePenColor updatePen = new UpdatePenColor(rf);
		for (int i = 0; i < backgroundColorMap.keySet().size(); i++) {
			indices.add(Double.toString(i));
			colors[i] = backgroundColorMap.get((double) i);
		}
		Rectangle[] penColors = makeColorNodes(colors);
		addMenuItem(penColor, indices, penColors);
		for (MenuItem m: penColor.getItems()) {
			m.setOnAction((event) -> updatePen.changePenColorAction(m.getText()));
		}
		return penColor;
	}

}
