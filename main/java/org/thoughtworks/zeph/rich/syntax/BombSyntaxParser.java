package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.BlockExecutor;
import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;

public class BombSyntaxParser implements SyntaxParser {
	private String instruction;
	private Map[] map;
	private Player player;

	public BombSyntaxParser(String instruction, Map[] map, Player player) {
		this.instruction = instruction;
		this.map = map;
		this.player = player;
	}

	@Override
	public Executor parser() {
		int n = Integer.valueOf(instruction.replace("bomb ", ""));
		if (-10 <= n && n <= 10) {
			return new BlockExecutor(map, player, n);
		} else {
			return null;
		}
	}
}
