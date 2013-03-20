package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.executor.Executor;
import org.thoughtworks.zeph.rich.executor.RobotExecutor;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;

public class RobotSyntaxParser implements SyntaxParser {

	private String instruction;
	private Map map;
	private Player player;

	public RobotSyntaxParser(String instruction, Map map, Player player) {
		this.instruction = instruction;
		this.map = map;
		this.player = player;
	}

	@Override
	public Executor parse() {
		return new RobotExecutor(map,player);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RobotSyntaxParser that = (RobotSyntaxParser) o;

		if (instruction != null ? !instruction.equals(that.instruction) : that.instruction != null) return false;
		if (map != null ? !map.equals(that.map) : that.map != null) return false;
		if (player != null ? !player.equals(that.player) : that.player != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = instruction != null ? instruction.hashCode() : 0;
		result = 31 * result + (map != null ? map.hashCode() : 0);
		result = 31 * result + (player != null ? player.hashCode() : 0);
		return result;
	}
}
