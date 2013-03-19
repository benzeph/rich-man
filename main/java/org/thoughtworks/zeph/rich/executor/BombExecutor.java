package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Bomb;

public class BombExecutor implements Executor {

	private Map[] map;
	private Player player;

	public BombExecutor(Map[] map, Player player) {
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() {

	}

	@Override
	public void execute(int para) {
		int bombPlace = (map.length + player.getCurrentMapPosition() + para) % map.length;
		boolean isBeenOccupied = false;
		if (map[bombPlace].isPlayerHere()) {
			isBeenOccupied = true;
		}
		if (null != map[bombPlace].getProp()) {

		}
		if (isBeenOccupied) {

		}
		if (map[bombPlace].getProp() == null) {
			if (player.useProp(new Bomb())) {
				map[bombPlace].setProp(new Bomb());
			} else {

			}
		}
	}
}
