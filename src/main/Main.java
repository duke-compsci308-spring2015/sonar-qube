package main;

import java.util.ArrayList;
import controller.TurtleMainController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.exceptions.CommandInputException;

public class Main extends Application {
	public static ArrayList<String> list;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws CommandInputException {
		TurtleMainController controller = new TurtleMainController();
		//TurtleView view = new TurtleView();
		stage.setScene(controller.getScene());
		stage.show();
	}
}
