package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.output.Printer;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Set;

public class Mine extends Grid {

	private int gamePoint;

	public Mine(int mapId, int gamePoint, char symbol) {
		super(mapId, symbol);
		this.gamePoint = gamePoint;
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		Printer.printGetGamePoint(gamePoint);
		player.addGamePoint(gamePoint);
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void setOwner(Player player) {

	}

	@Override
	public void addPlayer(Player player) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Set<Player> getPlayers() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

}
