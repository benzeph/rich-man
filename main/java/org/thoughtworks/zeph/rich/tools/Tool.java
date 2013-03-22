package org.thoughtworks.zeph.rich.tools;

public abstract class Tool {
	private int id;
	private int price;
	private char icon;
	private String name;

	protected Tool(int id, int price, char icon, String name) {
		this.id = id;
		this.price = price;
		this.icon = icon;
		this.name = name;
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

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tool tool = (Tool) o;

		if (icon != tool.icon) return false;
		if (id != tool.id) return false;
		if (price != tool.price) return false;
		if (name != null ? !name.equals(tool.name) : tool.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + price;
		result = 31 * result + (int) icon;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
