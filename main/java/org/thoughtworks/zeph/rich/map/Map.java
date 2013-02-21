package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.props.Prop;

public class Map {
	private int mapId;
	private Prop prop;

	public Prop getProp() {
		return prop;
	}

	public void setProp(Prop prop) {
		this.prop = prop;
	}

	public Map(int mapId) {
		this.mapId = mapId;
	}

	public int getMapId() {
		return mapId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Map map = (Map) o;

		if (mapId != map.mapId) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return mapId;
	}

	public void release() {

	}
}
