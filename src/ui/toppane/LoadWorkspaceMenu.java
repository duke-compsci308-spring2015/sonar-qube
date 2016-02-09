package ui.toppane;

import java.util.ArrayList;
import java.util.List;

import controller.toppane.UpdateFile;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.FileInterface;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;
import ui.interfaces.IMenu;

public class LoadWorkspaceMenu extends AbstractMenu implements IMenu{

	FileInterface fi;
	public LoadWorkspaceMenu(FileInterface fileInterface) {
		this.fi = fileInterface;
	}
	@Override
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf) {
		Menu load = new Menu(r.getString("loadTitle"));
		List<String> loadOptions = new ArrayList<String>();
		List<String> loadImages = new ArrayList<String>();
		loadOptions.add(r.getString("loadWorkspaceFile"));
		loadImages.add(r.getString("loadImage"));
		UpdateFile updateFile = new UpdateFile(fi);
		Rectangle[] fImages = makeImageNodes(loadImages, 20, 20);
		addMenuItem(load, loadOptions, fImages);
		for (MenuItem m : load.getItems()) {
			m.setOnAction((event) -> {
				try {
					updateFile.loadWorkspaceFile();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		return load;
	}

}
