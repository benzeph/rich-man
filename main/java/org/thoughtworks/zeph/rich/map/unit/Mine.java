package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

public class Mine extends Grid {

	private int gamePoint;

	public Mine(int mapId, int gamePoint, char symbol) {
		super(mapId, symbol);
		this.gamePoint = gamePoint;
	}

	public int getGamePoint() {
		return gamePoint;
	}

	public void addGamePoint(Player player) {
		player.addGamePoint(gamePoint);
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		player.addGamePoint(gamePoint);
	}

	@Override
	public int getPrice() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getLevel() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void setOwner(Player player) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

}
