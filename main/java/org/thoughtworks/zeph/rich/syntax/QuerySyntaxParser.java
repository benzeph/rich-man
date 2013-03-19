package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.QueryExecutor;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;

public class QuerySyntaxParser implements SyntaxParser {

	private String instruction;
	private Map[] map;
	private Player player;

	public QuerySyntaxParser(String instruction, Map[] map, Player player) {
		this.instruction = instruction;
		this.map = map;
		this.player = player;
	}

	@Override
	public Executor parser() {
		return new QueryExecutor(player);
	}
}
