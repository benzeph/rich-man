package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.map.FirstMapFactory;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.map.MapFactory;
import org.thoughtworks.zeph.rich.player.Player;

public class Main {
	public static void main(String[] args) {
		MapFactory mapFactory = new FirstMapFactory();
		Map map = mapFactory.createMap();
		Player[] players = InitializeGame.initialize();
		Rich rich = new Rich(players, map);
		rich.run();
	}
}

