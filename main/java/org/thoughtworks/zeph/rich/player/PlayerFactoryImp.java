package org.thoughtworks.zeph.rich.player;

import org.thoughtworks.zeph.rich.output.Color;

public class PlayerFactoryImp implements PlayerFactory {

	@Override
	public Player createPlayer(int id, int money) {
		switch (id) {
			case 1:
				return new Player("Qian Furen", 1, Color.PURPLE, money);
			case 2:
				return new Player("A Tubo", 2, Color.YELLOW, money);
			case 3:
				return new Player("Sun Xiaomei", 3, Color.RED, money);
			case 4:
				return new Player("Jin Beibei", 4, Color.GREEN, money);
			default:
				return null;
		}
	}
}
