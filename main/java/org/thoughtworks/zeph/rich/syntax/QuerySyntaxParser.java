package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.QueryExecutor;
import org.thoughtworks.zeph.rich.player.Player;

public class QuerySyntaxParser implements SyntaxParser {

	private Player player;

	public QuerySyntaxParser(Player player) {
		this.player = player;
	}

	@Override
	public Executor parser() {
		return new QueryExecutor(player);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		QuerySyntaxParser that = (QuerySyntaxParser) o;

		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return player != null ? player.hashCode() : 0;
	}
}
