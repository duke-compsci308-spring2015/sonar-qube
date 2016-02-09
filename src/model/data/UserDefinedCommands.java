package model.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.commands.UserCommand;

public class UserDefinedCommands {
	private Map<String, UserCommand> commandsMap;

	public UserDefinedCommands() {
		commandsMap = new HashMap<String, UserCommand>();
	}

	public boolean containsKey(String key) {
		return commandsMap.containsKey(key);
	}

	public UserCommand getUserCommand(String key) {
		return commandsMap.get(key);
	}

	public void addCommand(UserCommand object) {
		commandsMap.put(object.getName().toLowerCase(), object);
	}

	public List<String> getCommandDefiniton(String name) {
		return commandsMap.get(name).getDefinition();
	}

	public UserCommand removeCommandDefinition(String name) {
		return commandsMap.remove(name);
	}

	public Map<String, UserCommand> getCommandMap() {
		return Collections.unmodifiableMap(commandsMap);
	}

	// public Map<String, String> getCommandsMap() {
	// HashMap<String, String> mapForFront = new HashMap<String, String>();
	// for (String command : commandsMap.keySet()) {
	// StringBuilder rebuiltDefinition = new StringBuilder();
	// for (String definition : commandsMap.get(command)) {
	// rebuiltDefinition.append(definition);
	// rebuiltDefinition.append(" ");
	// }
	// mapForFront.put(command, rebuiltDefinition.toString());
	// }
	// return mapForFront;
	// }
}
