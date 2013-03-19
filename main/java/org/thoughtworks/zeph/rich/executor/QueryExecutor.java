package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.player.Player;

public class QueryExecutor implements Executor {
	private Player player;

	public QueryExecutor(Player player) {
		this.player = player;
	}

	@Override
	public void execute() {
		player.query();
	}
}
