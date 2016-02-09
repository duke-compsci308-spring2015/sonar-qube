package model.newCommands;

import model.commands.SetMultipleTurtlesCommand;

public class Tell extends SetMultipleTurtlesCommand {
	public Tell() {
		super();
	}

	@Override
	public double execute() {
		return 0;
	}

	@Override
	public String toString() {
		return "Tell";
	}
}