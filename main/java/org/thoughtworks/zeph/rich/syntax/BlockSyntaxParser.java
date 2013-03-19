package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;

public class BlockSyntaxParser implements SyntaxParser {
	private String instruction;
	private Map[] map;
	private Player player;

	public BlockSyntaxParser(String instruction, Map[] map, Player player) {
		this.instruction = instruction;
		this.map = map;
		this.player = player;
	}

	@Override
	public Executor parser() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
