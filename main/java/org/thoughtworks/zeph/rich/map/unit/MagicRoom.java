package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

public class MagicRoom extends Grid {

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
	public Player getBelongTo() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void levelUp() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getLevel() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public MagicRoom(int mapId, char symbol) {
		super(mapId, symbol);
	}
}
