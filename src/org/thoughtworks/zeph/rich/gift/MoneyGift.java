package org.thoughtworks.zeph.rich.gift;

import org.thoughtworks.zeph.rich.player.Player;

public class MoneyGift extends Gift {
	@Override
	public void openGift(Player player) {
		player.addMoney(2000);
	}
}
