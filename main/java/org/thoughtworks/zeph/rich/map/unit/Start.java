package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 13-3-21
 * Time: 下午9:32
 * To change this template use File | Settings | File Templates.
 */
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
}
