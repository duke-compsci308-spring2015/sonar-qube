package ui.toppane;

import javafx.scene.Group;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.FileInterface;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IFront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.toppane.UpdateFile;
import javafx.collections.ObservableList;


public class MenuHandler implements IFront {
	private DisplayTurtle display;
	private ReceiveFromFront rf;
	private PassToFrontInterface pf;
	private Menu backgroundColor;
	private Menu penColor;
	private FileInterface fi;
	
	public MenuHandler(DisplayTurtle disp, ReceiveFromFront receive, PassToFrontInterface pass, FileInterface fileInterface) {
		this.display = disp;
		this.rf = receive;
		this.pf = pass;
		this.fi = fileInterface;
	}


	public Group makeMenuBar() {
		Group root = new Group();
		MenuBar menuBar = new MenuBar();
		root = new Group();

		BackgroundMenu bgMenu = new BackgroundMenu();
		backgroundColor = bgMenu.makeMenu(display, pf, rf);


		PenColorMenu pcMenu = new PenColorMenu();
		penColor = pcMenu.makeMenu(display, pf, rf);


		ImageMenu iMenu = new ImageMenu();
		Menu image = iMenu.makeMenu(display, pf, rf);

		LanguageMenu langMenu = new LanguageMenu();
		Menu language = langMenu.makeMenu(display, pf, rf);


		PenPropertyMenu penPropMenu = new PenPropertyMenu();
		Menu penProperties = penPropMenu.makeMenu(display, pf, rf);


		HelpMenu hMenu = new HelpMenu();
		Menu help = hMenu.makeMenu(display, pf, rf);

		ChangeShapeMenu csMenu = new ChangeShapeMenu();
		Menu changeShape = csMenu.makeMenu(display, pf, rf);		
		
		NewWorkspaceMenu nwMenu = new NewWorkspaceMenu();
		Menu newWorkspace = nwMenu.makeMenu(display, pf, rf);
		
		FileMenu fileMenu = new FileMenu(fi);
		Menu file = fileMenu.makeMenu(display, pf, rf);
		
		menuBar.getMenus().addAll(backgroundColor, image, penColor, language, penProperties, changeShape, help, newWorkspace, file);
		root.getChildren().add(menuBar);
		return root;
	}

	@Override
	public void update() {
		Map<Double, Color> colorMap = pf.getPalette();
		ObservableList<MenuItem> backgroundList = backgroundColor.getItems();
		ObservableList<MenuItem> penList = penColor.getItems();

		for (int i = 0; i < colorMap.keySet().size(); i++) {
			Rectangle rect = (Rectangle) backgroundList.get(i).getGraphic();
			Rectangle penRect = (Rectangle) penList.get(i).getGraphic();
			rect.setFill(colorMap.get((double) i));
			penRect.setFill(colorMap.get((double) i));
		}
	}
}
