package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.output.Printer;
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
				sellBlock();
				break;
			case 2:
				sellRobot();
				break;
			case 3:
				sellBomb();
				break;
		}
	}

	private void sellBomb() {
		if (player.isPlayerHasABomb()) {
			player.sellTool(new Bomb());
		} else {
			Printer.sellToolFailed(new Robot().getName());
		}
	}

	private void sellRobot() {
		if (player.isPlayerHasARobot()) {
			player.sellTool(new Robot());
		} else {
			Printer.sellToolFailed(new Robot().getName());
		}
	}

	private void sellBlock() {
		if (player.isPlayerHasABlock()) {
			player.sellTool(new Block());
		} else {
			Printer.sellToolFailed(new Bomb().getName());
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
