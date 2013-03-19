package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Bomb;

public class BombExecutor implements Executor {

	private Map[] map;
	private Player player;
	private int n;

	public BombExecutor(Map[] map, Player player, int n) {
		this.map = map;
		this.player = player;
		this.n = n;
	}

	@Override
	public void execute() {
		int bombPlace = (map.length + player.getCurrentMapPosition() + n) % map.length;
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
