package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.map.unit.Grid;
import org.thoughtworks.zeph.rich.output.SystemOut;
import org.thoughtworks.zeph.rich.player.Player;

public class SellExecutor implements Executor {
	private Map map;
	private Player player;
	private int n;

	public SellExecutor(Map map, Player player, int n) {
		this.map = map;
		this.player = player;
		this.n = n;
	}

	@Override
	public void execute() {

		if (player.isPlayerHasBuilding(n)) {
			player.sellLand((Grid) map.getGrid(n));
		} else {
			SystemOut.sellFail();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SellExecutor that = (SellExecutor) o;

		if (n != that.n) return false;
		if (map != null ? !map.equals(that.map) : that.map != null) return false;
		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = map != null ? map.hashCode() : 0;
		result = 31 * result + (player != null ? player.hashCode() : 0);
		result = 31 * result + n;
		return result;
	}
}
