package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;

public class SellExecutor implements Executor {
	private Map[] map;
	private Player player;
	private int n;

	public SellExecutor(Map[] map, Player player, int n) {
		this.map = map;
		this.player = player;
		this.n = n;
	}

	@Override
	public void execute() {
		if (player.sellLand((Land) map[n])) {

		} else {

		}

	}
}
