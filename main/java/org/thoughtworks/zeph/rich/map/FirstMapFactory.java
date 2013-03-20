package org.thoughtworks.zeph.rich.map;

public class FirstMapFactory implements MapFactory {

	@Override
	public Map createMap() {
		return new FirstMap();
	}
}
