package org.thoughtworks.zeph.rich.player;

import org.thoughtworks.zeph.rich.output.Color;

public class PlayerFactoryImp implements PlayerFactory {

	@Override
	public Player createPlayer(int id, int money) {
		switch (id) {
			case 1:
				return new Player("钱夫人", 1, Color.PURPLE, money);
			case 2:
				return new Player("阿土伯", 2, Color.YELLOW, money);
			case 3:
				return new Player("孙小美", 3, Color.RED, money);
			case 4:
				return new Player("金贝贝", 4, Color.GREEN, money);
			default:
				return null;
		}
	}
}
