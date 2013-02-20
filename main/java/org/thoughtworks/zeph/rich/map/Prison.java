package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.player.Player;

import java.util.HashSet;
import java.util.Set;

public class Prison extends Map{
	private Set<Player> players;

	public Prison(int mapId){
		super(mapId);
		players = new HashSet<Player>();
	}
	public void addRole(Player player) {
		players.add(player);
	}

	public Set<Player> getPlayers() {
		return players;
	}

	@Override
	public void release() {
		players.clear();
	}
}
