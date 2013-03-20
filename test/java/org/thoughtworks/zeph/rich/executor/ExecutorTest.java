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

	private Player qianFuRen = new PlayerFactoryImp().createPlayer(1, 10000);
	private Player aTuBo = new PlayerFactoryImp().createPlayer(2, 10000);
	private Player[] players;
	private Grid[] map;
	private SyntaxParserFactory factory = new SyntaxParserFactory();

	@Before
	public void setUp() {
		map = new Grid[70];
		map[0] = new Grid(0, 'S');
		for (int i = 1; i <= 13; i++) {
			map[i] = new BuildingLandOneTwo(i, '0');
		}
		map[14] = new Hospital(14, 'H');
		for (int i = 15; i <= 27; i++) {
			map[i] = new BuildingLandOneTwo(i, '0');
		}
		map[28] = new PropRoom(28, 'T');
		for (int i = 29; i <= 34; i++) {
			map[i] = new BuildingLandThree(i, '0');
		}
		map[35] = new GiftRoom(35, 'G');
		for (int i = 36; i <= 48; i++) {
			map[i] = new BuildingLandFourFive(i, '0');
		}
		map[49] = new Prison(49, 'P');
		for (int i = 50; i <= 62; i++) {
			map[i] = new BuildingLandFourFive(i, '0');
		}
		map[63] = new MagicRoom(63, 'M');
		map[64] = new Mine(64, 20, '$');
		map[65] = new Mine(65, 80, '$');
		map[66] = new Mine(66, 100, '$');
		map[67] = new Mine(67, 40, '$');
		map[68] = new Mine(68, 80, '$');
		map[69] = new Mine(69, 60, '$');
		qianFuRen = new PlayerFactoryImp().createPlayer(1, 10000);
		aTuBo = new PlayerFactoryImp().createPlayer(2, 10000);
		players = new Player[]{qianFuRen, aTuBo};
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_bomb_from_map_5_when_input_is_bomb_5() {
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Bomb());
		String instruction = "bomb 5";
		factory.buildSyntaxParser(instruction, map, qianFuRen).parser().execute();
		Prop bomb = new Bomb();
		assertThat(map[5].getProp(), is(bomb));
		assertThat(qianFuRen.getProps().size(), is(0));
	}

	@Test
	public void should_return_a_block_when_input_is_block_3() {
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Block());
		String instruction = "block 3";
		Prop block = new Block();
		factory.buildSyntaxParser(instruction, map, qianFuRen).parser().execute();
		assertThat(map[3].getProp(), is(block));
		assertThat(qianFuRen.getProps().size(), is(0));
	}

	@Test
	public void should_not_stop_at_0_when_roll() {
		String instruction = "roll";
		factory.buildSyntaxParser(instruction, map, qianFuRen).parser().execute();
		assertThat(qianFuRen.getCurrentMapPosition(), not(0));
	}

	@Test
	public void should_return_null_when_robot() {
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Bomb());
		qianFuRen.buyProp(new Block());
		qianFuRen.buyProp(new Robot());
		String instructionRobot = "robot";
		String instructionBomb = "bomb 5";
		String instructionBlock = "block 3";
		factory.buildSyntaxParser(instructionBomb, map, qianFuRen).parser().execute();
		factory.buildSyntaxParser(instructionBlock, map, qianFuRen).parser().execute();
		factory.buildSyntaxParser(instructionRobot, map, qianFuRen).parser().execute();
		for (int i = 1; i <= 10; i++) {
			assertNull(map[i].getProp());
		}
	}

	@Test
	public void should_return__10000_null_and_200_when_sell_5() {
		qianFuRen.buyLand((Land) map[5]);
		String instruction = "sell 5";
		factory.buildSyntaxParser(instruction, map, qianFuRen).parser().execute();
		assertThat(qianFuRen.getMoney(), is(10000));
		assertNull(((Land) map[5]).getBelongTo());
	}

	@Test
	public void should_return_50_and_0_when_sell_tool_1() {
		qianFuRen.addGamePoint(100);
		qianFuRen.buyProp(new Block());
		String instruction = "sellTool 1";
		factory.buildSyntaxParser(instruction, map, qianFuRen).parser().execute();
		assertThat(qianFuRen.getGamePoint(), CoreMatchers.is(100));
		assertThat(qianFuRen.getProps().size(),is(0));
	}
}
