package org.thoughtworks.zeph.rich.executor;


import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.tools.Robot;

public class RobotExecutor implements Executor {
	public static final int step = 1;
	private Map map;
	private Player player;

	public RobotExecutor(Map map, Player player) {
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() {
		if (player.isPlayerHasARobot()) {
			player.useTool(new Robot());
			int robotMapPosition = player.getCurrentMapPosition();
			for (int i = 1; i <= 10; i++) {
				robotMapPosition = robotMapPosition + step;
				map.getGrid(robotMapPosition).setTool(null);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RobotExecutor that = (RobotExecutor) o;

		if (map != null ? !map.equals(that.map) : that.map != null) return false;
		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = map != null ? map.hashCode() : 0;
		result = 31 * result + (player != null ? player.hashCode() : 0);
		return result;
	}
}
