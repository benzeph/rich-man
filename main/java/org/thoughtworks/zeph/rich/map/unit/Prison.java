package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.output.Printer;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Prison extends Grid {

	private Set<Player> players;

	public Prison(int mapId, char symbol) {
		super(mapId, symbol);
		players = new HashSet<Player>();
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		Iterator it = players.iterator();
		while (it.hasNext()) {
			Player prisoner = (Player) it.next();
			prisoner.setPrisonDays(0);
		}
		players.clear();
		Printer.youReleaseAllPrisoner();
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public Set<Player> getPlayers() {
		return players;
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
}
