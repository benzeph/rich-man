package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.map.Map;

public abstract class Game {

	protected Map[] gameMap;

	public abstract void run();

	public abstract void runForTest(String instruction);

	public abstract void drawMap();

	public Map[] getGameMap() {
		return gameMap;
	}
}
