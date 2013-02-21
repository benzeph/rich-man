package org.thoughtworks.zeph.rich.props;

public class Prop {
	protected int id;
	protected int price;
	protected char icon;

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public int timeCountDown(int i) {
		return 0;
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
