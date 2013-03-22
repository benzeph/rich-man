package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.tools.Block;
import org.thoughtworks.zeph.rich.tools.Bomb;
import org.thoughtworks.zeph.rich.tools.Tool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class Grid {

	private int id;
	private char symbol;
	private Set<Character> playerSymbol = new HashSet<Character>();
	private Tool tool;

	public Grid(int id, char symbol) {
		this.id = id;
		this.symbol = symbol;
	}

	private boolean isPlayerHere = false;

	public abstract void doesWhatItNeedToDo(Player player);

	public abstract int getPrice();

	public abstract int getLevel();

	public abstract void setOwner(Player player);

	public abstract void addPlayer(Player player);

	public abstract Set<Player> getPlayers();

	public boolean isPropHere() {
		return tool != null;
	}

	public boolean isBlockHere() {
		if (null == tool) {
			return false;
		} else {
			return tool instanceof Block;
		}
	}

	public boolean isBombHere() {
		if (null == tool) {
			return false;
		} else {
			return tool instanceof Bomb;
		}
	}

	public boolean isPlayerHere() {
		return isPlayerHere;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public int getId() {
		return id;
	}

	public Set getPlayerSymbol() {
		return playerSymbol;
	}

	public void addPlayerSymbol(char playerSymbol) {
		this.playerSymbol.add(playerSymbol);
	}

	public char getSymbol() {
		if (playerSymbol.size() > 0) {
			Iterator it = playerSymbol.iterator();
			Character symbol = (Character) it.next();
			return symbol;
		}
		if (null != tool) {
			return tool.getIcon();
		}
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Grid grid = (Grid) o;

		if (id != grid.id) return false;
		if (isPlayerHere != grid.isPlayerHere) return false;
		if (symbol != grid.symbol) return false;
		if (playerSymbol != null ? !playerSymbol.equals(grid.playerSymbol) : grid.playerSymbol != null) return false;
		if (tool != null ? !tool.equals(grid.tool) : grid.tool != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (int) symbol;
		result = 31 * result + (playerSymbol != null ? playerSymbol.hashCode() : 0);
		result = 31 * result + (tool != null ? tool.hashCode() : 0);
		result = 31 * result + (isPlayerHere ? 1 : 0);
		return result;
	}
}
