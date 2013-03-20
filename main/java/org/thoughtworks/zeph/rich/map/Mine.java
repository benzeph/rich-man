package org.thoughtworks.zeph.rich.map;

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
}
