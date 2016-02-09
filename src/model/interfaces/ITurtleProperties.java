package model.interfaces;

import java.util.List;

import javafx.scene.shape.Line;

public interface ITurtleProperties {
	public double getX();

	public double getY();

	public List<Line> getLineList();
	
	public double absoluteAngleFrontend();
	
	public boolean isVisible();
	
	public boolean isActive();
	
	public int getID();
}
