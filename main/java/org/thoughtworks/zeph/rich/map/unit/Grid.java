package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;

import java.util.Set;

public abstract class Grid {

	private int id;
	private char symbol;
	private char playerSymbol = ' ';
	private Prop prop;
	private boolean isPlayerHere = false;

	public abstract void doesWhatItNeedToDo(Player player);

	public abstract int getPrice();

	public abstract int getLevel();

	public abstract void setOwner(Player player);

	public abstract void addPlayer(Player player);

	public abstract Set<Player> getPlayers();

	public boolean isPropHere() {
		return prop != null;
	}

	public boolean isBlockHere() {
		if (null == prop) {
			return false;
		} else {
			return prop instanceof Block;
		}
	}

	public boolean isBombHere() {
		if (null == prop) {
			return false;
		} else {
			return prop instanceof Bomb;
		}
	}

	public boolean isPlayerHere() {
		return isPlayerHere;
	}

	public Prop getProp() {
		return prop;
	}

	public void setProp(Prop prop) {
		this.prop = prop;
	}

	public Grid(int id, char symbol) {
		this.id = id;
		this.symbol = symbol;
	}

	public int getId() {
		return id;
	}

	public char getPlayerSymbol() {
		return playerSymbol;
	}

	public void setPlayerSymbol(char playerSymbol) {
		this.playerSymbol = playerSymbol;
	}

	public char getSymbol() {
		if (playerSymbol != ' ') {
			return playerSymbol;
		}
		if (null != prop) {
			return prop.getIcon();
		}
		return getSymbol();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Grid map = (Grid) o;

		if (id != map.id) return false;
		if (isPlayerHere != map.isPlayerHere) return false;
		if (playerSymbol != map.playerSymbol) return false;
		if (symbol != map.symbol) return false;
		if (prop != null ? !prop.equals(map.prop) : map.prop != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (prop != null ? prop.hashCode() : 0);
		result = 31 * result + (int) symbol;
		result = 31 * result + (int) playerSymbol;
		result = 31 * result + (isPlayerHere ? 1 : 0);
		return result;
	}
}
