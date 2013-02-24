package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.player.Player;

public class Mine extends Map{
	private int gamePoint;

	public Mine(int mapId,int gamePoint) {
		super(mapId);
		this.gamePoint = gamePoint;
	}

	public int getGamePoint() {
		return gamePoint;
	}

	public void addGamePoint(Player player) {
		player.addGamePoint(gamePoint);
	}
}
