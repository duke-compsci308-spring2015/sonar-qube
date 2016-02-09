package model.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class SpecialCommand extends Command {
	protected List<String> runList;

	public SpecialCommand() {
		super();
		runList = new ArrayList<String>();
	}

	public List<String> getRunList() {
		return runList;
	}
}