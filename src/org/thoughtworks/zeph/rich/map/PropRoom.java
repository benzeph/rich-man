package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.player.Player;

public class PropRoom {

	public void sell(Player player, Prop prop) {
		player.buyProp(prop);
	}
}
