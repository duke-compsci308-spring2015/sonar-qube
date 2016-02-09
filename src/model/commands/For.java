package model.commands;

import java.util.ArrayList;
import java.util.List;

public class For extends SpecialCommand {
	public For() {
		super();
	}

	public double execute() {
		List<String> tempList = new ArrayList<String>(myCommandLists.get(0));

		if (tempList.size() == 0) {
			return 0;
		}

		double param0 = Double.valueOf(myParameters.get(0));
		for (int i = (int) param0; i <= myParameters.get(1); i += myParameters.get(2)) {
			for (int j = 0; j < tempList.size(); j++) {
				if (tempList.get(j).equals(myVariables.get(0))) {
					runList.add(Integer.toString(i));
				} else {
					runList.add(tempList.get(j));
				}
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		return "For";
	}
}