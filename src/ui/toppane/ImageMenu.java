package ui.toppane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.toppane.UpdateImage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IMenu;

public class ImageMenu extends AbstractMenu implements IMenu{

	ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TopResource");

	@Override
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf) {
		Menu image = new Menu(r.getString("imageTitle"));
		List<String> imageOptions = new ArrayList<String>();
		imageOptions.add(r.getString("imageItem1"));
		imageOptions.add(r.getString("imageItem2"));
		imageOptions.add(r.getString("imageItem3"));
		UpdateImage imageChanger = new UpdateImage(display);
		Rectangle[] imageRects = makeImageNodes(imageOptions, Integer.parseInt(r.getString("imageImageHeight")), Integer.parseInt(r.getString("imageImageWidth")));
		addMenuItem(image, imageOptions, imageRects);
		for (MenuItem m : image.getItems()) {
			m.setOnAction((event) -> imageChanger.refreshImage(m.getText()));
		}
		return image;
	}

}
