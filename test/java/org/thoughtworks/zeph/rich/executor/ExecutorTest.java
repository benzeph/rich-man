package org.thoughtworks.zeph.rich.executor;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.*;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.props.Robot;
import org.thoughtworks.zeph.rich.syntax.SyntaxParserFactory;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ExecutorTest {

	private Player player = new PlayerFactoryImp().createPlayer(1, 10000);
	private SyntaxParserFactory parserFactory = new SyntaxParserFactory();
	private MapFactory mapFactory = new FirstMapFactory();
	private Grid[] map;

	@Before
	public void setUp() {
		map = mapFactory.createMap();
		player = new PlayerFactoryImp().createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_bomb_from_map_5_when_input_is_bomb_5() {
		player.addGamePoint(1000);
		player.buyProp(new Bomb());
		String instruction = "bomb 5";
		parserFactory.buildSyntaxParser(instruction, map, player).parser().execute();
		Prop bomb = new Bomb();
		assertThat(map[5].getProp(), is(bomb));
		assertThat(player.getProps().size(), is(0));
	}

	@Test
	public void should_return_a_block_when_input_is_block_3() {
		player.addGamePoint(1000);
		player.buyProp(new Block());
		String instruction = "block 3";
		Prop block = new Block();
		parserFactory.buildSyntaxParser(instruction, map, player).parser().execute();
		assertThat(map[3].getProp(), is(block));
		assertThat(player.getProps().size(), is(0));
	}

	@Test
	public void should_not_stop_at_0_when_roll() {
		String instruction = "roll";
		parserFactory.buildSyntaxParser(instruction, map, player).parser().execute();
		assertThat(player.getCurrentMapPosition(), not(0));
	}

	@Test
	public void should_return_null_when_robot() {
		player.addGamePoint(1000);
		player.buyProp(new Bomb());
		player.buyProp(new Block());
		player.buyProp(new Robot());
		String instructionRobot = "robot";
		String instructionBomb = "bomb 5";
		String instructionBlock = "block 3";
		parserFactory.buildSyntaxParser(instructionBomb, map, player).parser().execute();
		parserFactory.buildSyntaxParser(instructionBlock, map, player).parser().execute();
		parserFactory.buildSyntaxParser(instructionRobot, map, player).parser().execute();
		for (int i = 1; i <= 10; i++) {
			assertNull(map[i].getProp());
		}
	}

	@Test
	public void should_return__10000_null_and_200_when_sell_5() {
		player.buyLand((Land) map[5]);
		String instruction = "sell 5";
		parserFactory.buildSyntaxParser(instruction, map, player).parser().execute();
		assertThat(player.getMoney(), is(10000));
		assertNull(((Land) map[5]).getBelongTo());
	}

	@Test
	public void should_return_50_and_0_when_sell_tool_1() {
		player.addGamePoint(100);
		player.buyProp(new Block());
		String instruction = "sellTool 1";
		parserFactory.buildSyntaxParser(instruction, map, player).parser().execute();
		assertThat(player.getGamePoint(), CoreMatchers.is(100));
		assertThat(player.getProps().size(),is(0));
	}
}
