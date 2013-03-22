package org.thoughtworks.zeph.rich.executor;

import org.thoughtworks.zeph.rich.output.Color;
import org.thoughtworks.zeph.rich.output.SystemOut;
import org.thoughtworks.zeph.rich.player.Player;

public class QueryExecutor implements Executor {
	private Player player;

	public QueryExecutor(Player player) {
		this.player = player;
	}

	@Override
	public void execute() {
		SystemOut.colorPrint(player.query(), Color.WHITE);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		QueryExecutor that = (QueryExecutor) o;

		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return player != null ? player.hashCode() : 0;
	}
}
