package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.RollExecutor;
import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;

public class RollSyntaxParser implements SyntaxParser {


	private String instruction;
	private Grid[] map;
	private Player player;

	public RollSyntaxParser(String instruction, Grid[] map, Player player) {
		this.instruction = instruction;
		this.map = map;
		this.player = player;
	}

	@Override
	public Executor parser() {
		return new RollExecutor(map,player);
	}
}
