package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.SellToolExecutor;
import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;

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
}
