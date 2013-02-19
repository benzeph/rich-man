package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.player.Player;

public class Mine extends Map{
	private int gamePoint;

	public Mine(int mapId,int gamePoint) {
		super(mapId);
		this.gamePoint = gamePoint;
	}

	public void getGamePoint(Player player) {
		player.addGamePoint(gamePoint);
	}
}
