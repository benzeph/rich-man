package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.gift.Gift;
import org.thoughtworks.zeph.rich.player.Player;

public class GiftRoom extends Grid {

	public GiftRoom(int mapId, char symbol) {
		super(mapId, symbol);
	}

	public void getGift(Player player, Gift gift) {
		gift.openGift(player);
	}
}
