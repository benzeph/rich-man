package org.thoughtworks.zeph.rich.gift;

import org.thoughtworks.zeph.rich.god.God;
import org.thoughtworks.zeph.rich.god.WealthGod;
import org.thoughtworks.zeph.rich.player.Player;

public class GodGift extends Gift {

	@Override
	public void openGift(Player player) {
		God god = new WealthGod();
		player.setGod(god);
	}
}
