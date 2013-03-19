package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;

public class SellExecutor implements Executor {
	private Map[] map;
	private Player player;

	public SellExecutor(Map[] map, Player player) {
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() {

	}

	@Override
	public void execute(int para) {

		if (player.sellLand((Land) map[para])) {

		} else {

		}

	}
}
