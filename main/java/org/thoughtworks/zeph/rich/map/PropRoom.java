package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.player.Player;

public class PropRoom extends Map{

	public PropRoom(int mapId,char symbol){
		super(mapId,symbol);
	}
	public void sell(Player player, Prop prop) {
		player.buyProp(prop);
	}
}
