package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.output.SystemOut;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.tools.Bomb;

public class BombExecutor implements Executor {

	private Map map;
	private Player player;
	private int n;

	public BombExecutor(Map map, Player player, int n) {
		this.map = map;
		this.player = player;
		this.n = n;
	}

	@Override
	public void execute() {
		int bombPlace = (map.getMapLength() + player.getCurrentMapPosition() + n) % map.getMapLength();
		boolean isBeenOccupied = false;
		if (map.getGrid(bombPlace).isPlayerHere()) {
			isBeenOccupied = true;
		}
		if (map.getGrid(bombPlace).isPropHere()) {
			isBeenOccupied = true;
		}
		if (isBeenOccupied) {
			SystemOut.bombFailed();
		} else {
			if (player.isPlayerHasABomb()) {
				player.useTool(new Bomb());
				map.getGrid(bombPlace).setTool(new Bomb());
			} else {
				SystemOut.youDoNotHaveABomb();
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BombExecutor that = (BombExecutor) o;

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
