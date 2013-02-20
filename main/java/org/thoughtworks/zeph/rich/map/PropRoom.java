package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.player.Player;

public class PropRoom extends Map{

	public PropRoom(int mapId){
		super(mapId);
	}
	public void sell(Player player, Prop prop) {
		player.buyProp(prop);
	}
}
