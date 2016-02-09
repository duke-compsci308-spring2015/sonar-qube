package ui.centerpane;

import java.util.ResourceBundle;

import javafx.scene.Group;
import java.util.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.interfaces.IPenUpDown;
import model.interfaces.ITurtleProperties;

public class InitialTurtle {
	
	private ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/centerResource");
	
	public Group makeTurtle(List<ITurtleProperties> tp, IPenUpDown pi, List<Rectangle> rect, CreateTooltip tip, Canvas myCanvas, GraphicsContext gc) {
		Group root = new Group();
		Image image = changeImage(r.getString("image"));
		root.getChildren().add(myCanvas);
		for (int i = 0; i < tp.size(); i++) {
			Rectangle rectangle = rect.get(i);
			rectangle.setFill(new ImagePattern(image));
			tip.update(tp.get(i), pi, rectangle);
			double width = image.getWidth();
			double height = image.getHeight();
			gc = myCanvas.getGraphicsContext2D();
			gc.setFill(Color.GREEN);
			gc.fillRect(0, 0, Double.parseDouble(r.getString("canvasWidth")), Double.parseDouble(r.getString("canvasHeight")));
			double xpos = Double.parseDouble(r.getString("xPos")) + Double.parseDouble(r.getString("coordinateOffset")) - width / 2;
			double ypos = Double.parseDouble(r.getString("yPos")) + Double.parseDouble(r.getString("coordinateOffset")) - height / 2;
			rectangle.setX(xpos);
			rectangle.setY(ypos);
			root.getChildren().add(rectangle);
		}
		return root;
		
	}
	
	private Image changeImage(String s) {
		return new Image(getClass().getClassLoader().getResourceAsStream(s), 40, 40, false, false);
	}
}
