package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Hospital extends Grid {

	private Set<Player> players;

	public Hospital(int mapId, char symbol) {
		super(mapId, symbol);
		players = new HashSet<Player>();
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		Iterator it = players.iterator();
		while (it.hasNext()) {
			Player patient = (Player) it.next();
			patient.setHospitalDays(0);
		}
		players.clear();
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		players.add(player);
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
