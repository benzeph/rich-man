package org.thoughtworks.zeph.rich.map;

public interface Map {


	public int getPrisonId();

	public int getHospitalId();

	public Grid getGrid(int id);

	public int getMapLength();
}
