package controller.toppane;

import java.io.File;
import java.io.IOException;

import javafx.stage.FileChooser;
import model.interfaces.FileInterface;

public class UpdateFile {
	private File inputFile;
	private FileInterface fi;
	public UpdateFile(FileInterface fileInterface) {
		this.fi = fileInterface;
	}

	public void loadLibraryFile() throws IOException{
		FileChooser fc = new FileChooser();
		fc.setTitle("Load File");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fc.getExtensionFilters().add(extFilter);
		inputFile = fc.showOpenDialog(null);
		if (!(inputFile==null)) {
			try {
				fi.readLibraryXmlFile(inputFile.getAbsolutePath());
				//fi.readWorkspaceXmlFile(inputFile.getAbsolutePath());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadWorkspaceFile() throws IOException{
		FileChooser fc = new FileChooser();
		fc.setTitle("Load File");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fc.getExtensionFilters().add(extFilter);
		inputFile = fc.showOpenDialog(null);
		if (!(inputFile==null)) {
			try {
				fi.readWorkspaceXmlFile(inputFile.getAbsolutePath());
				//fi.readWorkspaceXmlFile(inputFile.getAbsolutePath());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveLibraryFile() throws IOException {
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fc.getExtensionFilters().add(extFilter);
		inputFile = fc.showSaveDialog(null);
		if (inputFile != null) {
			try {
				fi.writeLibraryXmlFile(inputFile.getAbsolutePath());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveWorkspaceFile() throws IOException {
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fc.getExtensionFilters().add(extFilter);
		inputFile = fc.showSaveDialog(null);
		if (inputFile != null) {
			try {
				fi.writeWorkspaceXmlFile(inputFile.getAbsolutePath());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
