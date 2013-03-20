package org.thoughtworks.zeph.rich.map;

public class FirstMap implements Map {

	private Grid[] grids;

	public FirstMap() {
		grids = new Grid[70];
		grids[0] = new Grid(0, 'S');
		for (int i = 1; i <= 13; i++) {
			grids[i] = new BuildingLandOneTwo(i, '0');
		}
		grids[14] = new Hospital(14, 'H');
		for (int i = 15; i <= 27; i++) {
			grids[i] = new BuildingLandOneTwo(i, '0');
		}
		grids[28] = new PropRoom(28, 'T');
		for (int i = 29; i <= 34; i++) {
			grids[i] = new BuildingLandThree(i, '0');
		}
		grids[35] = new GiftRoom(35, 'G');
		for (int i = 36; i <= 48; i++) {
			grids[i] = new BuildingLandFourFive(i, '0');
		}
		grids[49] = new Prison(49, 'P');
		for (int i = 50; i <= 62; i++) {
			grids[i] = new BuildingLandFourFive(i, '0');
		}
		grids[63] = new MagicRoom(63, 'M');
		grids[64] = new Mine(64, 20, '$');
		grids[65] = new Mine(65, 80, '$');
		grids[66] = new Mine(66, 100, '$');
		grids[67] = new Mine(67, 40, '$');
		grids[68] = new Mine(68, 80, '$');
		grids[69] = new Mine(69, 60, '$');
	}

	@Override
	public int getPrisonId() {
		return 49;
	}

	@Override
	public int getHospitalId() {
		return 14;
	}

	@Override
	public Grid getGrid(int id) {
		return grids[id];
	}

	@Override
	public int getMapLength() {
		return grids.length;
	}
}
