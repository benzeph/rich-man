package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.map.*;

public class RichGame extends Game {

	public RichGame(String map) {
		super(map);
		gameMap[0] = new Map(0);

		for (int i = 1; i <= 13; i++) {
			gameMap[i] = new BuildingLotOneTwo(i);
		}

		gameMap[14] = new Hospital(14);

		for (int i = 15; i <= 27; i++) {
			gameMap[i] = new BuildingLotOneTwo(i);
		}

		gameMap[28] = new PropRoom(28);

		for (int i = 29; i <= 34; i++) {
			gameMap[i] = new BuildingLotThree(i);
		}

		gameMap[35] = new GiftRoom(35);

		for (int i = 36; i <=48; i++) {
			gameMap[i] = new BuildingLotFourFive(i);
		}

		gameMap[49] = new Prison(49);

		for (int i = 50; i <= 62; i++) {
			gameMap[i] = new BuildingLotFourFive(i);
		}

		gameMap[61] = new MagicRoom(63);

		gameMap[64] = new Mine(64,20);
		gameMap[65] = new Mine(65,80);
		gameMap[66] = new Mine(66,100);
		gameMap[67] = new Mine(67,40);
		gameMap[68] = new Mine(68,80);
		gameMap[69] = new Mine(69,60);
	}
}
