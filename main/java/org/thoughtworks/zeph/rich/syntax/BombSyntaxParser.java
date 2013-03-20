package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.BombExecutor;
import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;

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
}
