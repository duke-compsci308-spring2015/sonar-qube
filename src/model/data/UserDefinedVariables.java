package model.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserDefinedVariables {
	private Map<String, Double> variableMap;

	public UserDefinedVariables() {
		variableMap = new HashMap<String, Double>();
	}

	public boolean containsKey(String key) {
		return variableMap.containsKey(key);
	}

	public double getVariable(String key) {
		return variableMap.get(key);
	}

	public void addVariable(String name, double value) {
		variableMap.put(name, value);
	}

	public double removeVariable(String name) {
		return variableMap.remove(name);
	}

	public Map<String, Double> getVariableMap() {
		return Collections.unmodifiableMap(variableMap);
	}
}
