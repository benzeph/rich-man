package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;

public class BlockExecutor implements Executor {
	private Map[] map;
	private Player player;

	public BlockExecutor(Map[] map, Player player) {
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() {
	}

	@Override
	public void execute(int para) {

		int blockPlace = (map.length + player.getCurrentMapPosition() + para) % map.length;
		boolean isBeenOccupied = false;
		if (map[blockPlace].isPlayerHere()) {
			isBeenOccupied = true;
		}
		if (null != map[blockPlace].getProp()) {

		}
		if (isBeenOccupied) {
		}
		if (map[blockPlace].getProp() == null) {
			if (player.useProp(new Block())) {
				map[blockPlace].setProp(new Block());
			}
		}
	}
}
