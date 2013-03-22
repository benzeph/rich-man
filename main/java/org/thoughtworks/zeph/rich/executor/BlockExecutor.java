package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.tools.Block;

public class BlockExecutor implements Executor {
	private Map map;
	private Player player;
	private int n;

	public BlockExecutor(Map map, Player player, int n) {
		this.map = map;
		this.player = player;
		this.n = n;
	}

	@Override
	public void execute() {
		int blockPlace = (map.getMapLength() + player.getCurrentMapPosition() + n) % map.getMapLength();
		boolean isBeenOccupied = false;
		if (map.getGrid(blockPlace).isPlayerHere()) {
			isBeenOccupied = true;
		}
		if (map.getGrid(blockPlace).isPropHere()) {
			isBeenOccupied = true;
		}
		if (isBeenOccupied) {

		} else {
			if (player.isPlayerHasABlock()) {
				player.useTool(new Block());
				map.getGrid(blockPlace).setTool(new Block());
			} else {

			}
		}

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BlockExecutor that = (BlockExecutor) o;

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
