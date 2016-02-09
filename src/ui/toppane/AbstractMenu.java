package ui.toppane;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class AbstractMenu {

	ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TopResource");

	protected Rectangle[] makeColorNodes(Color[] options) {
		Rectangle[] rects = new Rectangle[options.length];
		for (int i = 0; i < rects.length; i++) {
			Rectangle rect = new Rectangle(Integer.parseInt(r.getString("ColorRectWidth")),Integer.parseInt(r.getString("ColorRectHeight")));
			rect.setFill(options[i]);
			//System.out.println(rect.getFill().);
			rects[i] = rect;
		}
		return rects;
	}

	protected Rectangle[] makeImageNodes(List<String> options, int width, int length) {
		Rectangle[] rects = new Rectangle[options.size()];
		for (int i = 0; i < rects.length; i++) {
			Rectangle rect = new Rectangle(width,length);
			rect.setFill(new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream(options.get(i)))));
			rects[i] = rect;
		}
		return rects;
	}
	
	protected void addMenuItem(Menu menu, List<String> options, Node[] graphics) {
		for (int i = 0; i < options.size(); i++) {
			MenuItem m = new MenuItem(options.get(i), graphics[i]);
			menu.getItems().add(m);
		}
	}
	
}
