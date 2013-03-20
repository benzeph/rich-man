package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.SellExecutor;
import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;

public class SellSyntaxParser implements SyntaxParser {

	private String instruction;
	private Grid[] map;
	private Player player;

	public SellSyntaxParser(String instruction, Grid[] map, Player player) {
		this.instruction = instruction;
		this.map = map;
		this.player = player;
	}

	@Override
	public Executor parser() {
		int n = Integer.valueOf(instruction.replace("sell ", ""));
		if (0 < n && n < map.length) {
			return new SellExecutor(map, player, n);
		} else {
			return null;
		}
	}
}
