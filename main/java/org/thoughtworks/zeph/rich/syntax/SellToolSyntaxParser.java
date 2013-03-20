package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.SellToolExecutor;
import org.thoughtworks.zeph.rich.player.Player;

public class SellToolSyntaxParser implements SyntaxParser {

	private String instruction;
	private Player player;

	public SellToolSyntaxParser(String instruction, Player player) {
		this.instruction = instruction;
		this.player = player;
	}

	@Override
	public Executor parse() {
		int n = Integer.valueOf(instruction.replace("sellTool ", ""));
		if (n >= 1 && n <= 3) {
			return new SellToolExecutor(player, n);
		} else {
			return null;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SellToolSyntaxParser that = (SellToolSyntaxParser) o;

		if (instruction != null ? !instruction.equals(that.instruction) : that.instruction != null) return false;
		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = instruction != null ? instruction.hashCode() : 0;
		result = 31 * result + (player != null ? player.hashCode() : 0);
		return result;
	}
}
