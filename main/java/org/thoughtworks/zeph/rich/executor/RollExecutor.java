package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;

import java.util.Arrays;

public class RollExecutor implements Executor {

	private Grid[] map;
	private Player player;

	public RollExecutor(Grid[] map, Player player) {
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() {
		int currentMapPosition = player.getCurrentMapPosition();
		int step = player.dice();
		for (int i = 1; i <= step; i++) {
			currentMapPosition = (currentMapPosition + 1) % map.length;
			setMapSymbolEveryStep(map, player, currentMapPosition);
			player.setCurrentMapPosition(currentMapPosition);
			if (player.getProp() instanceof Bomb) {
				((Bomb) player.getProp()).timeCountDown();
				if (((Bomb) player.getProp()).getLeftTime() == 0) {
					bombExplode(player);
					break;
				}
			}
			if (map[currentMapPosition].getProp() instanceof Block) {
				map[currentMapPosition].setProp(null);

			}
		}
		if (map[currentMapPosition].getProp() instanceof Bomb) {
			player.setProp(new Bomb());
			map[currentMapPosition].setProp(null);

		}

	}

	private void bombExplode(Player player) {
		player.setCurrentMapPosition(14);
		player.setHospitalDays(3);
		player.setProp(null);
		System.out.println(player.getName() + ":bomb explode , you are in hospital");
	}

	private void setMapSymbolEveryStep(Grid[] gameMap, Player player, int currentMapPosition) {
		if (gameMap[currentMapPosition].getPlayerSymbol() == ' ') {
			gameMap[currentMapPosition].setPlayerSymbol(player.getSymbol());
		}
		if (gameMap[(gameMap.length + currentMapPosition - 1) % gameMap.length].getPlayerSymbol() == player.getSymbol()) {
			gameMap[(gameMap.length + currentMapPosition - 1) % gameMap.length].setPlayerSymbol(' ');
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RollExecutor that = (RollExecutor) o;

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
