package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.output.Printer;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.tools.Bomb;

public class RollExecutor implements Executor {

	public static final int HOSPITAL_DAYS = 3;
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
		Printer.dice(step);
		for (int i = 1; i <= step; i++) {
			currentMapPosition = (currentMapPosition + 1) % map.getMapLength();
			player.setCurrentMapPosition(currentMapPosition);
			reviseMapSymbolEveryStep(map, player, currentMapPosition);
			if (player.isCarriedWithABomb()) {
				player.bombTimeCountDown();
				if (player.isBombExplode()) {
					bombExplode(player);
					Printer.bombExplode();
					break;
				}
			}
			if (map.getGrid(currentMapPosition).isBlockHere()) {
				map.getGrid(currentMapPosition).setTool(null);
				Printer.youMeetABlock();
				break;
			}
		}
		if (map.getGrid(currentMapPosition).isBombHere()) {
			Printer.youMeetABomb();
			player.setTool(new Bomb());
			map.getGrid(currentMapPosition).setTool(null);
		}

	}

	private void bombExplode(Player player) {
		player.setCurrentMapPosition(map.getHospitalId());
		player.setHospitalDays(HOSPITAL_DAYS);
		player.setTool(null);
	}

	private void reviseMapSymbolEveryStep(Map map, Player player, int currentMapPosition) {
		reviseCurrentPositionSymbol(map, player, currentMapPosition);
		reviseBackwardPositionSymbol(map, player, currentMapPosition);
	}

	private void reviseBackwardPositionSymbol(Map map, Player player, int currentMapPosition) {
		map.getGrid((map.getMapLength() + currentMapPosition - 1) % map.getMapLength()).getPlayerSymbol().remove(player.getSymbol());
	}


	private void reviseCurrentPositionSymbol(Map map, Player player, int currentMapPosition) {
		map.getGrid(currentMapPosition).addPlayerSymbol(player.getSymbol());
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
