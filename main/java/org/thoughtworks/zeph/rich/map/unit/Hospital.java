package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

import java.util.HashSet;
import java.util.Set;

public class Hospital extends Grid {

	private Set<Player> players;

	@Override
	public void doesWhatItNeedToDo(Player player) {

	}

	@Override
	public int getPrice() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getCost() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void setBelongTo(Player player) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void levelUp() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getLevel() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public Hospital(int mapId, char symbol) {
		super(mapId, symbol);
		players = new HashSet<Player>();
	}

	public void addRole(Player player) {
		players.add(player);
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void release() {
		players.clear();
	}
}
