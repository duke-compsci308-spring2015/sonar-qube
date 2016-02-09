package ui.toppane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.toppane.UpdateLanguage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IMenu;

public class LanguageMenu extends AbstractMenu implements IMenu {

	ResourceBundle r = ResourceBundle.getBundle("resources/GUIProperties/TopResource");

	@Override
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf) {
		Menu language = new Menu(r.getString("languageTitle"));
		UpdateLanguage updateLang = new UpdateLanguage(rf);
		List<String> languageOptions = new ArrayList<String>();
		List<String> flagOptions = new ArrayList<String>();
		for (int i = 1; i <= 8; i++) {
			languageOptions.add(r.getString("languageItem" + Integer.toString(i)));
			flagOptions.add(r.getString("languageFlag" + Integer.toString(i)));
		}
		Rectangle[] flagRects = makeImageNodes(flagOptions, Integer.parseInt(r.getString("flagImageWidth")), Integer.parseInt(r.getString("flagImageHeight")));
		addMenuItem(language, languageOptions, flagRects);
		for (MenuItem m: language.getItems()) {
			m.setOnAction((event) -> updateLang.changeLanguage(m.getText()));
		}
		return language;
	}

}
