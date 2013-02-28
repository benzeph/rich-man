package org.thoughtworks.zeph.rich.map;

import org.thoughtworks.zeph.rich.output.ColorSystemOut;
import org.thoughtworks.zeph.rich.props.Prop;

public class Map {

	private int mapId;
	private Prop prop;
	private char symbol;
	private char playerSymbol = ' ';

	public Prop getProp() {
		return prop;
	}

	public void setProp(Prop prop) {
		this.prop = prop;
	}

	public Map(int mapId, char symbol) {
		this.mapId = mapId;
		this.symbol = symbol;
	}

	public int getMapId() {
		return mapId;
	}

	public char getPlayerSymbol() {
		return playerSymbol;
	}

	public void setPlayerSymbol(char playerSymbol) {
		this.playerSymbol = playerSymbol;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Map map = (Map) o;

		if (mapId != map.mapId) return false;
		if (playerSymbol != map.playerSymbol) return false;
		if (symbol != map.symbol) return false;
		if (prop != null ? !prop.equals(map.prop) : map.prop != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = mapId;
		result = 31 * result + (prop != null ? prop.hashCode() : 0);
		result = 31 * result + (int) symbol;
		result = 31 * result + (int) playerSymbol;
		return result;
	}

	public void release() {

	}

	public void getSymbol() {
		if (playerSymbol != ' ') {
			ColorSystemOut.print(String.valueOf(playerSymbol), 7);
			return;
		}
		if (this instanceof Land) {
			if (((Land) this).getBelongTo() != null) {
				ColorSystemOut.print(String.valueOf(symbol), ((Land) this).getBelongTo().getColorNum());
				return;
			}
		}
		if (null != prop) {
			ColorSystemOut.print(String.valueOf(prop.getIcon()), 7);
			return;
		}
		ColorSystemOut.print(String.valueOf(symbol), 7);
	}
}
