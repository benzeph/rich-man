package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Arrays;

public class RobotExecutor implements Executor {
	public static final int step = 1;
	private Grid[] map;
	private Player player;

	public RobotExecutor(Grid[] map, Player player) {
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() {
		int robotMapPosition = player.getCurrentMapPosition();
		for (int i = 1; i <= 10; i++) {
			robotMapPosition = robotMapPosition + step;
			map[robotMapPosition].setProp(null);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RobotExecutor that = (RobotExecutor) o;

		if (!Arrays.equals(map, that.map)) return false;
		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = map != null ? Arrays.hashCode(map) : 0;
		result = 31 * result + (player != null ? player.hashCode() : 0);
		return result;
	}
}
