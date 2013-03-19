package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;

public class RollExecutor implements Executor {

	private Map[] map;
	private Player player;

	public RollExecutor(Map[] map, Player player) {
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

	@Override
	public void execute(int para) {

	}

	private void bombExplode(Player player) {
		player.setCurrentMapPosition(14);
		player.setHospitalDays(3);
		player.setProp(null);
		System.out.println(player.getName() + ":bomb explode , you are in hospital");
	}

	private void setMapSymbolEveryStep(Map[] gameMap, Player player, int currentMapPosition) {
		if (gameMap[currentMapPosition].getPlayerSymbol() == ' ') {
			gameMap[currentMapPosition].setPlayerSymbol(player.getSymbol());
		}
		if (gameMap[(gameMap.length + currentMapPosition - 1) % gameMap.length].getPlayerSymbol() == player.getSymbol()) {
			gameMap[(gameMap.length + currentMapPosition - 1) % gameMap.length].setPlayerSymbol(' ');
		}
	}
}
