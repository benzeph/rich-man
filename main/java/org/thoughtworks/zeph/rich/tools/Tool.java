package org.thoughtworks.zeph.rich.tools;

public abstract class Tool {
	private int id;
	private int price;
	private char icon;

	public Tool(int id, int price, char icon) {
		this.id = id;
		this.price = price;
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public char getIcon() {
		return icon;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tool tool = (Tool) o;

		if (icon != tool.icon) return false;
		if (id != tool.id) return false;
		if (price != tool.price) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + price;
		result = 31 * result + (int) icon;
		return result;
	}
}
