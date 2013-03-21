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
	public int getLevel() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void setOwner(Player player) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public MagicRoom(int mapId, char symbol) {
		super(mapId, symbol);
	}
}
