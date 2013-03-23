package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.map.unit.*;
import org.thoughtworks.zeph.rich.output.Color;
import org.thoughtworks.zeph.rich.output.Printer;

public class FirstMap implements Map {

	public static final int WHITE = 7;
	private Grid[] grids;

	public FirstMap() {
		grids = new Grid[70];
		grids[0] = new Start(0, 'S');
		for (int i = 1; i <= 13; i++) {
			grids[i] = new BuildingLandOneTwo(i, '0');
		}
		grids[14] = new Hospital(14, 'H');
		for (int i = 15; i <= 27; i++) {
			grids[i] = new BuildingLandOneTwo(i, '0');
		}
		grids[28] = new ToolRoom(28, 'T');
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
			printGrid(i, getGrid(i).getSymbol());
		}
		System.out.println();

		printGrid(69, getGrid(69).getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(' ');
		}
		printGrid(29, getGrid(29).getSymbol());
		System.out.println();

		printGrid(68, getGrid(68).getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(' ');
		}
		printGrid(30, getGrid(30).getSymbol());
		System.out.println();

		printGrid(67, getGrid(67).getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(' ');
		}
		printGrid(31, getGrid(31).getSymbol());
		System.out.println();

		printGrid(66, getGrid(66).getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(' ');
		}
		printGrid(32, getGrid(32).getSymbol());
		System.out.println();

		printGrid(65, getGrid(65).getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(' ');
		}
		printGrid(33, getGrid(33).getSymbol());
		System.out.println();

		printGrid(64, getGrid(64).getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(' ');
		}
		printGrid(34, getGrid(34).getSymbol());
		System.out.println();

		for (int i = 63; i >= 35; i--) {
			printGrid(i, getGrid(i).getSymbol());
		}
		System.out.println();
	}

	@Override
	public void printGrid(int id, char symbol) {
		if (isBuildingLandOneTwo(id)) {
			printBuildingLandOneTwo(id, symbol);
		} else if (isBuildingLandThree(id)) {
			printBuildingLandThree(id, symbol);
		} else if (isBuildingLandFourFive(id)) {
			printBuildingLandFourFive(id, symbol);
		} else {
			printGeneralSymbol(id);
		}
	}

	private void printGeneralSymbol(int id) {
		Printer.colorPrint(grids[id].getSymbol(), Color.WHITE);
	}

	private void printBuildingLandFourFive(int id, char symbol) {
		BuildingLandFourFive buildingLandFourFive = (BuildingLandFourFive) grids[id];
		if (buildingLandFourFive.isOwnerExist()) {
			if (isSymbolBelongToBuilding(symbol)) {
				Printer.colorPrint(symbol, buildingLandFourFive.getOwner().getColorNum());
			}else{
				Printer.colorPrint(symbol, Color.WHITE);
			}
		} else {
			Printer.colorPrint(symbol, Color.WHITE);
		}
	}

	private void printBuildingLandThree(int id, char symbol) {
		BuildingLandThree buildingLandThree = (BuildingLandThree) grids[id];
		if (buildingLandThree.isOwnerExist()) {
			if (isSymbolBelongToBuilding(symbol)) {
				Printer.colorPrint(symbol, buildingLandThree.getOwner().getColorNum());
			}else{
				Printer.colorPrint(symbol, Color.WHITE);
			}
		} else {
			Printer.colorPrint(symbol, Color.WHITE);
		}
	}

	private void printBuildingLandOneTwo(int id, char symbol) {
		BuildingLandOneTwo buildingLandOneTwo = (BuildingLandOneTwo) grids[id];
		if (buildingLandOneTwo.isOwnerExist()) {
			if (isSymbolBelongToBuilding(symbol)) {
				Printer.colorPrint(symbol, buildingLandOneTwo.getOwner().getColorNum());
			}else{
				Printer.colorPrint(symbol, Color.WHITE);
			}
		} else {
			Printer.colorPrint(symbol, Color.WHITE);
		}
	}

	private boolean isSymbolBelongToBuilding(char symbol) {
		return symbol == '0'||symbol=='1'||symbol=='2'||symbol=='3';
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
	public boolean isBuildingLandFourFive(int id) {
		return (id >= 36 && id <= 48) || (id >= 50 && id <= 62);
	}


}
