package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;

public class RobotExecutor implements Executor {
	private Map[] map;
	private Player player;

	public RobotExecutor(Map[] map, Player player) {
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() {
		int robotMapPosition = player.getCurrentMapPosition();
		for (int i = 1; i <= 10; i++) {
			robotMapPosition = robotMapPosition + 1;
			map[robotMapPosition].setProp(null);
		}
	}
}
