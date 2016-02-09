package ui.interfaces;

import javafx.scene.control.Menu;
import model.commands.ReceiveFromFront;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;

public interface IMenu {
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf);
}
