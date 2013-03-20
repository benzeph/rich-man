package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Arrays;

public class SellExecutor implements Executor {
	private Grid[] map;
	private Player player;
	private int n;

	public SellExecutor(Grid[] map, Player player, int n) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SellExecutor that = (SellExecutor) o;

		if (n != that.n) return false;
		if (!Arrays.equals(map, that.map)) return false;
		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = map != null ? Arrays.hashCode(map) : 0;
		result = 31 * result + (player != null ? player.hashCode() : 0);
		result = 31 * result + n;
		return result;
	}
}
