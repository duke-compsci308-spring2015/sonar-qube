package ui.toppane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.toppane.UpdateHelpPage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IMenu;

public class HelpMenu extends AbstractMenu implements IMenu{

	ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TopResource");

	@Override
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf) {
		Menu help = new Menu(r.getString("helpTitle"));
		List<String> helpOptions = new ArrayList<String>();
		List<String> helpPics = new ArrayList<String>();
		helpOptions.add(r.getString("link1"));
		helpOptions.add(r.getString("link2"));
		helpPics.add(r.getString("linkImage1"));
		helpPics.add(r.getString("linkImage2"));
		Rectangle[] helpRects = makeImageNodes(helpPics, Integer.parseInt(r.getString("helpImageWidth")), Integer.parseInt(r.getString("helpImageHeight")));
		addMenuItem(help, helpOptions, helpRects);
		UpdateHelpPage updateHelp = new UpdateHelpPage();
		for (MenuItem m: help.getItems()) {
			m.setOnAction((event) -> updateHelp.openPage(m.getText()));
		}
		return help;
	}

}
