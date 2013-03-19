package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Robot;

public class SellToolExecutor implements Executor {

	private Map[] map;
	private Player player;
	private int n;

	public SellToolExecutor(Map[] map, Player player, int n) {
		this.map = map;
		this.player = player;
		this.n = n;
	}

	@Override
	public void execute() {
		switch (n) {
			case 1:
				sellBlock(player);
				break;
			case 2:
				sellRobot(player);
				break;
			case 3:
				sellBomb(player);
				break;
		}
	}

	private void sellBomb(Player player) {
		if (player.sellProp(new Bomb())) {

		} else {

		}
	}

	private void sellRobot(Player player) {
		if (player.sellProp(new Robot())) {

		} else {

		}
	}

	private void sellBlock(Player player) {
		if (player.sellProp(new Block())) {

		} else {

		}
	}
}
