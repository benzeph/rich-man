package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.BombExecutor;
import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Arrays;

public class BombSyntaxParser implements SyntaxParser {
	private String instruction;
	private Grid[] map;
	private Player player;

	public BombSyntaxParser(String instruction, Grid[] map, Player player) {
		this.instruction = instruction;
		this.map = map;
		this.player = player;
	}

	@Override
	public Executor parser() {
		int n = Integer.valueOf(instruction.replace("bomb ", ""));
		if (-10 <= n && n <= 10) {
			return new BombExecutor(map, player, n);
		} else {
			return null;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BombSyntaxParser that = (BombSyntaxParser) o;

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
