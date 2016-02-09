package model.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.shape.Line;
import model.interfaces.ITurtleProperties;

public class Turtle implements ITurtleProperties {
	private double x, y;
	private double angle;
	private boolean visible;
	private boolean active;
	private static Pen pen;
	private List<Line> lineList;
	private DecimalFormat df;
	private int myID;
	private static int turtleCount = -1;
	private LineSplitter lineSplitter = new LineSplitter();
	private ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TurtleResource");

	public static void setPen(Pen p) {
		pen = p;
	}
	
	public static void resetTurtleCount() {
		turtleCount = 0;
	}

	public Turtle(int id) {
		turtleCount++;
		myID = id;
		initialize();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean b) {
		active = b;
	}

	public static int getNumTurtles() {
		return turtleCount;
	}

	public void setTurtle(Turtle copyTurtle) {
		x = copyTurtle.getX();
		y = copyTurtle.getY();
		angle = copyTurtle.getAngle();
		visible = copyTurtle.isVisible();
		active = copyTurtle.isActive();
		df = new DecimalFormat("#.#####");
		lineList = new ArrayList<Line>(copyTurtle.getLineList());
	}

	public void reset() {
		initialize();
	}

	private void initialize() {
		x = Integer.parseInt(r.getString("startX"));
		y = Integer.parseInt(r.getString("startY"));
		angle = Integer.parseInt(r.getString("startAngle"));
		visible = Boolean.parseBoolean(r.getString("visible"));
		// active = false;
		active = true;
		df = new DecimalFormat("#.#####");
		lineList = new ArrayList<Line>();
	}

	public double absoluteAngleFrontend() {
		return (90 - getAngle()) % 360;
	}

	public void addLine(double x0, double y0, double x1, double y1) {
		Line line = new Line(format(x0), format(y0), format(x1), format(y1));
		line.setFill(pen.getPenColor());
		line.setStrokeWidth(pen.getPenSize());
		// line.getStrokeDashArray().addAll(50d, 40d);
		List<Line> tempList = lineSplitter.split(line);

		Line lastLine = tempList.get(tempList.size() - 1);
		x = lastLine.getEndX();
		y = lastLine.getEndY();

		if (pen.isPenDown()) {
			lineList.addAll(tempList);
		}
	}

	public void resetLineList() {
		lineList.clear();
	}

	public List<Line> getLineList() {
		return lineList;
	}

	public int getID() {
		return myID;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = format(x);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = format(y);
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = format(angle);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	private double format(double d) {
		if (Double.valueOf(df.format(d)) == 0.00000)
			return 0;
		return Double.valueOf(df.format(d));
	}
}