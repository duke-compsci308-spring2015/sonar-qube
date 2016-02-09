package ui.centerpane;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import model.commands.ReceiveFromFront;
import model.data.Stamp;
import model.interfaces.IPenUpDown;
import model.interfaces.ITurtleProperties;
import model.interfaces.PassToFrontInterface;
import model.interfaces.StampInterface;
import ui.interfaces.IFront;

import java.util.*; 

public class DisplayTurtle implements IFront {
	private Canvas myCanvas;
	private Group root;
	private GraphicsContext gc;
	private List<Rectangle> rect;
	private ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/centerResource");
	private Image image = new Image(r.getString("image"));
	private CreateTooltip tip;
	private IPenUpDown pInterface;
	private InitialTurtle initial;
	private PassToFrontInterface passInterface;
	private StampInterface stampInterface;
	private ReceiveFromFront receiveInterface;
	
	public DisplayTurtle(List<ITurtleProperties> tp, IPenUpDown pi, PassToFrontInterface pf, StampInterface si, ReceiveFromFront rf) {
		myCanvas = new Canvas(Double.parseDouble(r.getString("canvasWidth")), Double.parseDouble(r.getString("canvasHeight")));
		myCanvas.setTranslateX(Double.parseDouble(r.getString("canvasTranslateX")));
		myCanvas.setTranslateY(Double.parseDouble(r.getString("canvasTranslateY")));
		rect = new ArrayList<Rectangle>(tp.size()); //(40, 40);
		for (int i = 0; i < tp.size(); i++) {
			Rectangle rectangle = new Rectangle(Double.parseDouble(r.getString("rectangleWidth")), Double.parseDouble(r.getString("rectangleHeight")));
			rect.add(rectangle);
		}
		tip = new CreateTooltip();
		initial = new InitialTurtle();
		gc = myCanvas.getGraphicsContext2D();
		root = initial.makeTurtle(tp, pi, rect, tip, myCanvas, gc);
		pInterface = pi;
		passInterface = pf;
		stampInterface = si;
		receiveInterface = rf;
	}

	
	private void drawStamp(GraphicsContext gc, Image image, double angle, double tlx, double tly) {
		gc.save();
		rotate(gc, angle, tlx + image.getWidth() / 2, tly + image.getHeight() / 2);
		gc.drawImage(image, tlx, tly);
		gc.restore();
	}
	
	
	private void rotate(GraphicsContext gc, double angle, double pivotX, double pivotY) {
		Rotate rot = new Rotate(angle, pivotX, pivotY);
		gc.setTransform(rot.getMxx(), rot.getMyx(), rot.getMxy(), rot.getMyy(), rot.getTx(), rot.getTy());
	}
	
	
	public void update() {
		List<ITurtleProperties> turtleList = passInterface.getTurtleList();
		
		for (int i = 0; i < rect.size(); i++) {
			root.getChildren().remove(rect.get(i));
		}
		rect.clear();
		
		for (int i = 0; i < turtleList.size(); i++) {
			Rectangle rectangle = new Rectangle(Double.parseDouble(r.getString("rectangleWidth")), Double.parseDouble(r.getString("rectangleHeight")));

			rectangle.setFill(new ImagePattern(getImage()));
			rect.add(rectangle);
		}
		
		gc.setFill(passInterface.getUpdatedBackgroundColor());
		gc.fillRect(Double.parseDouble(r.getString("originX")), Double.parseDouble(r.getString("originY")), Double.parseDouble(r.getString("canvasWidth")), Double.parseDouble(r.getString("canvasHeight")));
		
		for (int i = 0; i < turtleList.size(); i++) {
			Rectangle rectangle = rect.get(i);
			ITurtleProperties turtleProp = turtleList.get(i);
			rectangle.setOnMouseClicked((event) -> { 
			try {
				receiveInterface.receiveCommand("tell [ " + turtleProp.getID() + " ] ");
			}
			catch (Exception e) {
				e.printStackTrace();
			}});
			
			double xpos = 250 + turtleProp.getX() - rectangle.getWidth() / 2;
			double ypos = 250 - turtleProp.getY() - rectangle.getHeight() / 2;
			rectangle.setVisible(turtleProp.isVisible());
			if (turtleProp.isVisible()) {
				rectangle.setX(xpos);
				rectangle.setY(ypos);
				rectangle.setRotate(turtleProp.absoluteAngleFrontend());
			}
		
			tip.update(turtleProp, pInterface, rectangle);
			List<Line> lineList = turtleProp.getLineList();
			for (int j = 0; j < lineList.size(); j++) {
				Line line = lineList.get(j);
				drawLine(line);
			}
			double coordinateOffset = Double.parseDouble(r.getString("coordinateOffset"));
			for (Stamp s : stampInterface.getStampList()) {
				drawStamp(gc, this.image, (90 - s.getMyAngle()) % 360, coordinateOffset + s.getMyX() - rectangle.getWidth() / 2, coordinateOffset - s.getMyY() - rectangle.getHeight() / 2);
			}
			
			root.getChildren().add(rectangle);

		}

	}

	private void drawLine(Line line) {
		double coordinateOffset = Double.parseDouble(r.getString("coordinateOffset"));
		gc.setStroke(line.getFill());
		gc.setLineWidth(line.getStrokeWidth());
		gc.strokeLine(line.getStartX() + coordinateOffset, coordinateOffset - line.getStartY(), line.getEndX() + coordinateOffset, coordinateOffset - line.getEndY());
	}

	public void setImage(String s) {
		for (Rectangle rectangle: rect) {
			rectangle.setFill(Color.WHITE);
			Image i = new Image(getClass().getClassLoader().getResourceAsStream(s), Double.parseDouble(r.getString("rectangleWidth")), Double.parseDouble(r.getString("rectangleHeight")), false, false);
			this.image = i;
			rectangle.setFill(new ImagePattern(this.image));
		}
	}

	public Image getImage() {
		return this.image;
	}

	public GraphicsContext getGC() {
		return this.gc;
	}

	public Group getGroup() {
		return this.root;
	}

}
