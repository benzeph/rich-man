package org.thoughtworks.zeph.rich.syntax;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.FirstMapFactory;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.map.MapFactory;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SyntaxTest {

	private SyntaxParserFactory parserFactory = new SyntaxParserFactory();
	private PlayerFactory playerFactory = new PlayerFactoryImp();
	private Player player = playerFactory.createPlayer(1, 10000);
	private MapFactory mapFactory = new FirstMapFactory();
	private String instruction;
	private Map map = mapFactory.createMap();
	private SyntaxParser expectParser;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_a_block_syntax_parser_when_input_is_block_5() {
		instruction = "block 5";
		expectParser = new BlockSyntaxParser(instruction, map, player);
		assertThat(parserFactory.buildSyntaxParser(instruction, map, player), is(expectParser));
	}

	@Test
	public void should_return_a_bomb_syntax_parser_when_input_is_bomb_6() {
		instruction = "bomb 6";
		expectParser = new BombSyntaxParser(instruction, map, player);
		assertThat(parserFactory.buildSyntaxParser(instruction, map, player), is(expectParser));
	}

	@Test
	public void should_return_a_robot_syntax_parser_when_input_is_robot() {
		instruction = "robot";
		expectParser = new RobotSyntaxParser(instruction, map, player);
		assertThat(parserFactory.buildSyntaxParser(instruction, map, player), is(expectParser));
	}

	@Test
	public void should_return_a_roll_syntax_parser_when_input_is_roll() {
		instruction = "roll";
		expectParser = new RollSyntaxParser(instruction, map, player);
		assertThat(parserFactory.buildSyntaxParser(instruction, map, player), is(expectParser));
	}

	@Test
	public void should_return_a_sell_syntax_parser_when_input_is_sell_21() {
		instruction = "sell 21";
		expectParser = new SellSyntaxParser(instruction, map, player);
		assertThat(parserFactory.buildSyntaxParser(instruction, map, player), is(expectParser));
	}

	@Test
	public void should_return_a_sell_tool_syntax_parser_when_input_is_sellTool_1() {
		instruction = "sellTool 1";
		expectParser = new SellToolSyntaxParser(instruction, player);
		assertThat(parserFactory.buildSyntaxParser(instruction, map, player), is(expectParser));
	}

	@Test
	public void should_return_a_query_syntax_parser_when_input_is_query() {
		instruction = "query";
		expectParser = new QuerySyntaxParser(player);
		assertThat(parserFactory.buildSyntaxParser(instruction, map, player), is(expectParser));
	}
}
