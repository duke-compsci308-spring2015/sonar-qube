package controller;

import javafx.scene.Scene;
import model.TurtleWorld;
import ui.TurtleView;

public class TurtleMainController {
	private TurtleView view;
	private TurtleWorld model;
	
	public TurtleMainController() {
		this.model = new TurtleWorld();
		this.view = new TurtleView(model, model.getTurtleList(), model, model, model, model);
		
	}
	public Scene getScene() {
		return view.getScene();
	}
}
