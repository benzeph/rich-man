package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

public class BuildingLandOneTwo extends Grid {

	private int level;
	private int price;
	private Player belongTo;

	@Override
	public void doesWhatItNeedToDo(Player player) {

	}

	public BuildingLandOneTwo(int mapId, char symbol) {
		super(mapId, symbol);
		setPrice(200);
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
	public int getLevel() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
