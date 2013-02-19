package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.player.Player;

public class Mine {
	private int gamePoint;

	public Mine(int gamePoint) {
		this.gamePoint = gamePoint;
	}

	public void getGamePoint(Player player) {
		player.addGamePoint(gamePoint);
	}
}
