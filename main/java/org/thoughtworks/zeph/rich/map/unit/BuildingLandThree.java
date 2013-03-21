package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

public class BuildingLandThree extends Grid {

	private int level;
	private int price;
	private Player belongTo;

	@Override
	public void doesWhatItNeedToDo(Player player) {

	}

	public BuildingLandThree(int mapId, char symbol) {
		super(mapId, symbol);
		this.price = 500;
	}


	public int getPrice() {
		return price;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void setOwner(Player player) {
		belongTo = player;
	}


}
