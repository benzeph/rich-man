package org.thoughtworks.zeph.rich.props;

public abstract class Prop {
	private int id;
	private int price;
	private char icon;

	public Prop(int id, int price, char icon) {
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

		Prop prop = (Prop) o;

		if (icon != prop.icon) return false;
		if (id != prop.id) return false;
		if (price != prop.price) return false;

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
