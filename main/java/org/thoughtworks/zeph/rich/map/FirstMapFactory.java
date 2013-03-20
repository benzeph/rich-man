package org.thoughtworks.zeph.rich.map;

public class FirstMapFactory implements MapFactory {
	private Grid[] map;

	@Override
	public Grid[] createMap() {
		map = new Grid[70];
		map[0] = new Grid(0, 'S');
		for (int i = 1; i <= 13; i++) {
			map[i] = new BuildingLandOneTwo(i, '0');
		}
		map[14] = new Hospital(14, 'H');
		for (int i = 15; i <= 27; i++) {
			map[i] = new BuildingLandOneTwo(i, '0');
		}
		map[28] = new PropRoom(28, 'T');
		for (int i = 29; i <= 34; i++) {
			map[i] = new BuildingLandThree(i, '0');
		}
		map[35] = new GiftRoom(35, 'G');
		for (int i = 36; i <= 48; i++) {
			map[i] = new BuildingLandFourFive(i, '0');
		}
		map[49] = new Prison(49, 'P');
		for (int i = 50; i <= 62; i++) {
			map[i] = new BuildingLandFourFive(i, '0');
		}
		map[63] = new MagicRoom(63, 'M');
		map[64] = new Mine(64, 20, '$');
		map[65] = new Mine(65, 80, '$');
		map[66] = new Mine(66, 100, '$');
		map[67] = new Mine(67, 40, '$');
		map[68] = new Mine(68, 80, '$');
		map[69] = new Mine(69, 60, '$');
		return map;
	}
}
