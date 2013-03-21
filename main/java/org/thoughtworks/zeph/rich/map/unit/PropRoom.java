package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Prop;

public class PropRoom extends Grid {

	public PropRoom(int mapId, char symbol) {
		super(mapId, symbol);
	}

	public void sell(Player player, Prop prop) {
		player.buyProp(prop);
	}
}
