package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;

import java.util.Arrays;

public class BlockExecutor implements Executor {
	private Grid[] map;
	private Player player;
	private int n;

	public BlockExecutor(Grid[] map, Player player, int n) {
		this.map = map;
		this.player = player;
		this.n = n;
	}

	@Override
	public void execute() {
		int blockPlace = (map.length + player.getCurrentMapPosition() + n) % map.length;
		boolean isBeenOccupied = false;
		if (map[blockPlace].isPlayerHere()) {
			isBeenOccupied = true;
		}
		if (map[blockPlace].isPropHere()) {
			isBeenOccupied = true;
		}
		if (isBeenOccupied) {

		} else {
			if (player.useProp(new Block())) {
				map[blockPlace].setProp(new Block());
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
