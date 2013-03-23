package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.map.unit.Grid;

public interface Map {


	public int getPrisonId();

	public int getHospitalId();

	public Grid getGrid(int id);

	public int getMapLength();

	public void drawMap();

	public void printGrid(int id,char symbol);


	public boolean isBuildingLandOneTwo(int id);

	public boolean isBuildingLandThree(int id);

	public boolean isBuildingLandFourFive(int id);
}
