package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.player.Player;

public class Land extends Grid {

	private int level;
	private int price;
	private Player belongTo;

	public Land(int mapId, char symbol) {
		super(mapId, symbol);
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

	public Player getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(Player belongTo) {
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

		if (level != land.level) return false;
		if (price != land.price) return false;
		if (belongTo != null ? !belongTo.equals(land.belongTo) : land.belongTo != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + level;
		result = 31 * result + (belongTo != null ? belongTo.hashCode() : 0);
		result = 31 * result + price;
		return result;
	}
}
