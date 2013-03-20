package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Robot;

import java.util.Arrays;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SellToolExecutor that = (SellToolExecutor) o;

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
