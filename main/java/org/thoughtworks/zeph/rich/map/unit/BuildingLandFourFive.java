package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

public class BuildingLandFourFive extends Grid {

	private int level;
	private int price;
	private Player owner;

	@Override
	public void doesWhatItNeedToDo(Player player) {

	}

	public BuildingLandFourFive(int mapId, char symbol) {
		super(mapId, symbol);
		this.price = 300;
	}


	public int getPrice() {
		return price;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setOwner(Player player) {
		owner = player;
	}

}
