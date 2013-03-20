package org.thoughtworks.zeph.rich.syntax;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.executor.*;
import org.thoughtworks.zeph.rich.map.FirstMap;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SyntaxParserTest {

	private String instruction;
	private Map map;
	private final PlayerFactory factory = new PlayerFactoryImp();
	private Player player;
	private SyntaxParser parser;
	private Executor executor;

	@Before
	public void setUp() {
		map = new FirstMap();
		player = factory.createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_a_block_executor_when_input_is_block_5() {
		instruction = "block 5";
		parser = new BlockSyntaxParser(instruction, map, player);
		executor = new BlockExecutor(map, player, 5);
		assertThat(parser.parse(), is(executor));
	}

	@Test
	public void should_return_a_bomb_executor_when_input_is_bomb_5() {
		instruction = "bomb 5";
		parser = new BombSyntaxParser(instruction, map, player);
		executor = new BombExecutor(map, player, 5);
		assertThat(parser.parse(), is(executor));
	}

	@Test
	public void should_return_a_query_executor_when_input_is_query() {
		instruction = "query";
		parser = new QuerySyntaxParser(player);
		executor = new QueryExecutor(player);
		assertThat(parser.parse(), is(executor));
	}

	@Test
	public void should_return_a_robot_executor_when_input_is_robot() {
		instruction = "robot";
		parser = new RobotSyntaxParser(instruction, map, player);
		executor = new RobotExecutor(map, player);
		assertThat(parser.parse(), is(executor));
	}

	@Test
	public void should_return_a_roll_executor_when_input_is_roll() {
		instruction = "roll";
		parser = new RollSyntaxParser(instruction, map, player);
		executor = new RollExecutor(map, player);
		assertThat(parser.parse(), is(executor));
	}

	@Test
	public void should_return_a_sell_tool_executor_when_input_is_sellTool_1() {
		instruction = "sellTool 1";
		parser = new SellToolSyntaxParser(instruction, player);
		executor = new SellToolExecutor(player, 1);
		assertThat(parser.parse(), is(executor));
	}
}
