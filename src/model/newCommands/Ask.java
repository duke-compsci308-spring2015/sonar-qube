package model.newCommands;

import model.commands.SetMultipleTurtlesCommand;

public class Ask extends SetMultipleTurtlesCommand {
	public Ask() {
		super();
	}

	@Override
	public double execute() {
		return 0;
	}

	@Override
	public String toString() {
		return "Ask";
	}
}