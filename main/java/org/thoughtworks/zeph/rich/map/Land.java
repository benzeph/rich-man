package org.thoughtworks.zeph.rich.map;

public class Land extends Map {
	private int level;
	private int belongTo;
	private int price;

	public Land(int mapId) {
		super(mapId);
		level = 0;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(int belongTo) {
		this.belongTo = belongTo;
	}

	public int getCost() {
		return price * (level + 1) / 2;
	}

	public void levelUp() {
		level = level + 1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Land land = (Land) o;

		if (belongTo != land.belongTo) return false;
		if (level != land.level) return false;
		if (price != land.price) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + level;
		result = 31 * result + belongTo;
		result = 31 * result + price;
		return result;
	}
}
