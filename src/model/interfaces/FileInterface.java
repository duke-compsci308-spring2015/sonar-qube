package model.interfaces;

public interface FileInterface {
	
	public void writeLibraryXmlFile(String path);
	
	public void readLibraryXmlFile(String path);
	
	public void writeWorkspaceXmlFile(String path);
	
	public void readWorkspaceXmlFile(String path);
}
