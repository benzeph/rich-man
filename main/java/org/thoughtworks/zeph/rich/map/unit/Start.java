package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

import java.util.Set;

public class Start extends Grid {

	public Start(int id, char symbol) {
		super(id, symbol);
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getPrice() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getLevel() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void setOwner(Player player) {
		//To change body of implemented methods use File | Settings | File Templates.
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
