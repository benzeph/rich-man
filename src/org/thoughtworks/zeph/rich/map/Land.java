package org.thoughtworks.zeph.rich.map;

public class Land {
	protected int level;
	protected int belongTo;
	protected int price;
	protected int mapId;

	public Land(int mapId) {
		level = 0;
		this.mapId = mapId;
	}

	public int getMapId() {
		return mapId;
	}

	public int getLevel() {
		return level;
	}

	public void levelUp() {
		level = level + 1;
	}

	public void setBelongTo(int belongTo) {
		this.belongTo = belongTo;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPrice() {
		return price;
	}

	public int getCost() {
		return price * (level + 1) / 2;  //To change body of created methods use File | Settings | File Templates.
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Land land = (Land) o;

		if (belongTo != land.belongTo) return false;
		if (level != land.level) return false;
		if (mapId != land.mapId) return false;
		if (price != land.price) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = level;
		result = 31 * result + belongTo;
		result = 31 * result + price;
		result = 31 * result + mapId;
		return result;
	}
}
