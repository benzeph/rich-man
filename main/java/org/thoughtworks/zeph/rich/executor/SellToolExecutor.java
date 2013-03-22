package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.output.SystemOut;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.tools.Block;
import org.thoughtworks.zeph.rich.tools.Bomb;
import org.thoughtworks.zeph.rich.tools.Robot;

public class SellToolExecutor implements Executor {

	private Player player;
	private int n;

	public SellToolExecutor(Player player, int n) {
		this.player = player;
		this.n = n;
	}

	@Override
	public void execute() {
		switch (n) {
			case 1:
				if (player.isPlayerHasABlock()) {
					sellBlock(player);
				}
				break;
			case 2:
				if (player.isPlayerHasARobot()) {
					sellRobot(player);
				}
				break;
			case 3:
				if (player.isPlayerHasABomb()) {
					sellBomb(player);
				}
				break;
		}
	}

	private void sellBomb(Player player) {
		if (player.sellTool(new Bomb())) {

		} else {
			SystemOut.sellToolFailed(new Bomb().getName());
		}
	}

	private void sellRobot(Player player) {
		if (player.sellTool(new Robot())) {

		} else {
			SystemOut.sellToolFailed(new Robot().getName());
		}
	}

	private void sellBlock(Player player) {
		if (player.sellTool(new Block())) {

		} else {
			SystemOut.sellToolFailed(new Robot().getName());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SellToolExecutor that = (SellToolExecutor) o;

		if (n != that.n) return false;
		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = player != null ? player.hashCode() : 0;
		result = 31 * result + n;
		return result;
	}
}
