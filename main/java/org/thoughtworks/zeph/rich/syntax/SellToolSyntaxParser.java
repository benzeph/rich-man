package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.SellToolExecutor;
import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Arrays;

public class SellToolSyntaxParser implements SyntaxParser {

	private String instruction;
	private Grid[] map;
	private Player player;

	public SellToolSyntaxParser(String instruction, Grid[] map, Player player) {
		this.instruction = instruction;
		this.map = map;
		this.player = player;
	}

	@Override
	public Executor parser() {
		int n = Integer.valueOf(instruction.replace("sellTool ", ""));
		if (n >= 1 && n <= 3) {
			return new SellToolExecutor(map, player, n);
		} else {
			return null;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SellToolSyntaxParser that = (SellToolSyntaxParser) o;

		if (instruction != null ? !instruction.equals(that.instruction) : that.instruction != null) return false;
		if (!Arrays.equals(map, that.map)) return false;
		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = instruction != null ? instruction.hashCode() : 0;
		result = 31 * result + (map != null ? Arrays.hashCode(map) : 0);
		result = 31 * result + (player != null ? player.hashCode() : 0);
		return result;
	}
}
