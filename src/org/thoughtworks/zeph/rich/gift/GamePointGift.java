package org.thoughtworks.zeph.rich.gift;

import org.thoughtworks.zeph.rich.player.Player;

public class GamePointGift extends Gift {
	@Override
	public void openGift(Player player) {
		player.addGamePoint(200);
	}
}
