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

	@Override
	public void drawMap() {
		for (int i = 0; i < 29; i++) {
			getGrid(i).getSymbol();
		}
		System.out.println();
		getGrid(69).getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		getGrid(29).getSymbol();
		System.out.println();
		getGrid(68).getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		getGrid(30).getSymbol();
		System.out.println();
		getGrid(67).getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		getGrid(31).getSymbol();
		System.out.println();
		getGrid(66).getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		getGrid(32).getSymbol();
		System.out.println();
		getGrid(65).getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		getGrid(33).getSymbol();
		System.out.println();
		getGrid(64).getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		getGrid(34).getSymbol();
		System.out.println();
		for (int i = 63; i >= 35; i--) {
			getGrid(i).getSymbol();
		}
		System.out.println();
	}

	@Override
	public boolean isBuildingLandOneTwo(int id) {
		return (id >= 1 && id <= 13) || (id >= 15 && id <= 27);
	}

	@Override
	public boolean isBuildingLandThree(int id) {
		return id >= 29 && id <= 34;
	}

	@Override
	public boolean isBuildingLandFour(int id) {
		return (id >= 36 && id <= 48) || (id >= 50 && id <= 62);
	}


}
