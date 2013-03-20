package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Bomb;

public class RollExecutor implements Executor {

	private Map map;
	private Player player;

	public RollExecutor(Map map, Player player) {
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() {
		int currentMapPosition = player.getCurrentMapPosition();
		int step = player.dice();
		for (int i = 1; i <= step; i++) {
			currentMapPosition = (currentMapPosition + 1) % map.getMapLength();
			player.setCurrentMapPosition(currentMapPosition);
			reviseMapSymbolEveryStep(map, player, currentMapPosition);
			if (player.isCarriedWithABomb()) {
				player.bombTimeCountDown();
				if (player.isBombExplode()) {
					bombExplode(player);
					break;
				}
			}
			if (map.getGrid(currentMapPosition).isBlockHere()) {
				map.getGrid(currentMapPosition).setProp(null);
				break;
			}
		}
		if (map.getGrid(currentMapPosition).isBombHere()) {
			player.setProp(new Bomb());
			map.getGrid(currentMapPosition).setProp(null);
		}

	}

	private void bombExplode(Player player) {
		player.setCurrentMapPosition(map.getHospitalId());
		player.setHospitalDays(3);
		player.setProp(null);
	}

	private void reviseMapSymbolEveryStep(Map map, Player player, int currentMapPosition) {
		reviseCurrentPositionSymbol(map, player, currentMapPosition);
		reviseBackwardPositionSymbol(map, player, currentMapPosition);
	}

	private void reviseBackwardPositionSymbol(Map map, Player player, int currentMapPosition) {
		if (map.getGrid((map.getMapLength() + currentMapPosition - 1) % map.getMapLength()).getPlayerSymbol() == player.getSymbol()) {
			map.getGrid((map.getMapLength() + currentMapPosition - 1) % map.getMapLength()).setPlayerSymbol(' ');
		}
	}

	private void reviseCurrentPositionSymbol(Map map, Player player, int currentMapPosition) {
		if (map.getGrid(currentMapPosition).getPlayerSymbol() == ' ') {
			map.getGrid(currentMapPosition).setPlayerSymbol(player.getSymbol());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RollExecutor that = (RollExecutor) o;

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
