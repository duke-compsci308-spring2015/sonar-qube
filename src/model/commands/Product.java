package model.commands;

public class Product extends Command {
	public Product() {
		super();
	}

	public double execute() {
		return myParameters.get(0) * myParameters.get(1);
	}

	@Override
	public String toString() {
		return "Product";
	}
}