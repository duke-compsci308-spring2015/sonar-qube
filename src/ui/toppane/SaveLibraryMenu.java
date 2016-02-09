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

public class SaveLibraryMenu extends AbstractMenu implements IMenu{
	FileInterface fi;
	public SaveLibraryMenu(FileInterface fileInterface) {
		this.fi = fileInterface;
	}

	@Override
	public Menu makeMenu(DisplayTurtle display, PassToFrontInterface pf, ReceiveFromFront rf) {
		Menu save = new Menu(r.getString("saveTitle"));
		List<String> saveOptions = new ArrayList<String>();
		List<String> saveImages = new ArrayList<String>();
		saveOptions.add(r.getString("saveLibraryFile"));
		saveImages.add(r.getString("saveImage"));
		UpdateFile updateFile = new UpdateFile(fi);
		Rectangle[] fImages = makeImageNodes(saveImages, 20, 20);
		addMenuItem(save, saveOptions, fImages);

		for (MenuItem m : save.getItems()) {
			m.setOnAction((event) -> {
				try {
					updateFile.saveLibraryFile();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		return save;
	}

}
