package org.thoughtworks.zeph.rich.interpreter;

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

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class InterpreterTest {

	private Map[] gameMap;
	private Player qianFuRen;
	private Interpreter interpreter;
	private Player[] players;
	private Player aTuBo;

	@Before
	public void setUp() {
		gameMap = new Map[70];
		gameMap[0] = new Map(0, 'S');

		for (int i = 1; i <= 13; i++) {
			gameMap[i] = new BuildingLandOneTwo(i, '0');
		}

		gameMap[14] = new Hospital(14, 'H');

		for (int i = 15; i <= 27; i++) {
			gameMap[i] = new BuildingLandOneTwo(i, '0');
		}

		gameMap[28] = new PropRoom(28, 'T');

		for (int i = 29; i <= 34; i++) {
			gameMap[i] = new BuildingLandThree(i, '0');
		}

		gameMap[35] = new GiftRoom(35, 'G');

		for (int i = 36; i <= 48; i++) {
			gameMap[i] = new BuildingLandFourFive(i, '0');
		}

		gameMap[49] = new Prison(49, 'P');

		for (int i = 50; i <= 62; i++) {
			gameMap[i] = new BuildingLandFourFive(i, '0');
		}

		gameMap[63] = new MagicRoom(63, 'M');

		gameMap[64] = new Mine(64, 20, '$');
		gameMap[65] = new Mine(65, 80, '$');
		gameMap[66] = new Mine(66, 100, '$');
		gameMap[67] = new Mine(67, 40, '$');
		gameMap[68] = new Mine(68, 80, '$');
		gameMap[69] = new Mine(69, 60, '$');
		qianFuRen = new PlayerFactoryImp().createPlayer(1, 10000);
		interpreter = new Interpreter();
		players = new Player[]{qianFuRen};
		aTuBo = new PlayerFactoryImp().createPlayer(2, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_bomb_from_map_5_when_input_is_bomb_5() {
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Bomb());
		Prop bomb = new Bomb();
		assertThat(interpreter.interpret("bomb 5", gameMap, players, 0), is("bomb set at 5"));
		assertThat(gameMap[5].getProp(), is(bomb));
		assertThat(qianFuRen.getProps().size(), is(0));
	}

	@Test
	public void should_return_bomb_n_when_input_is_bomb_20() {
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Bomb());
		assertThat(interpreter.interpret("bomb 20", gameMap, players, 0), is("bomb n(-10<=n<=10)"));
	}

	@Test
	public void should_return_a_block_when_input_is_block_3() {
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Block());
		String instruction = "block 3";
		Prop block = new Block();
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("block at 3"));
		assertThat(gameMap[3].getProp(), is(block));
		assertThat(qianFuRen.getProps().size(), is(0));
	}

	@Test
	public void should_return_block_n_when_input_is_block_12() {
		String instruction = "block 12";
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("block n(-10=<n<=10)"));
	}

	@Test
	public void should_not_stop_at_0_when_roll() {
		String instruction = "roll";
		interpreter.interpret(instruction, gameMap, players, 0);
		assertThat(qianFuRen.getCurrentMapPosition(), not(0));
	}

	@Test
	public void should_stop_at_3_when_block_3_and_roll() {
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Block());
		String instruction = "block 3";
		Prop block = new Block();
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("block at 3"));
		assertThat(gameMap[3].getProp(), is(block));
		String instructionRoll = "roll";
		interpreter.interpret(instructionRoll, gameMap, players, 0);
		if (qianFuRen.getCurrentMapPosition() < 3) {
			interpreter.interpret(instructionRoll, gameMap, players, 0);
		}
		if (qianFuRen.getCurrentMapPosition() < 3) {
			assertThat(interpreter.interpret(instructionRoll, gameMap, players, 0), is("block at 3"));
		}
		assertThat(qianFuRen.getProps().size(), is(0));
		assertNull(gameMap[3].getProp());
	}

	@Test
	public void should_return_you_do_no_have_a_bomb_when_input_is_bomb_3() {
		assertThat(interpreter.interpret("bomb 1", gameMap, players, 0), is("you don't have a bomb"));
	}

	@Test
	public void should_return_meet_bomb_when_roll() {
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Bomb());
		qianFuRen.buyProp(new Bomb());
		qianFuRen.buyProp(new Bomb());
		qianFuRen.buyProp(new Bomb());
		qianFuRen.buyProp(new Bomb());
		qianFuRen.buyProp(new Bomb());
		assertThat(interpreter.interpret("bomb 1", gameMap, players, 0), is("bomb set at 1"));
		assertThat(interpreter.interpret("bomb 2", gameMap, players, 0), is("bomb set at 2"));
		assertThat(interpreter.interpret("bomb 3", gameMap, players, 0), is("bomb set at 3"));
		assertThat(interpreter.interpret("bomb 4", gameMap, players, 0), is("bomb set at 4"));
		assertThat(interpreter.interpret("bomb 5", gameMap, players, 0), is("bomb set at 5"));
		assertThat(interpreter.interpret("bomb 6", gameMap, players, 0), is("bomb set at 6"));
		assertThat(interpreter.interpret("roll", gameMap, players, 0), is("roll , stop at " + qianFuRen.getCurrentMapPosition() + " , meet a bomb"));
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
		interpreter.interpret(instructionBomb, gameMap, players, 0);
		interpreter.interpret(instructionBlock, gameMap, players, 0);
		assertThat(interpreter.interpret(instructionRobot, gameMap, players, 0), is("robot out"));
		for (int i = 1; i <= 10; i++) {
			assertNull(gameMap[i].getProp());
		}
	}

	@Test
	public void should_return_9800_qianFuRen_10000_0_and_200_when_sell_5() {
		qianFuRen.buyLand((BuildingLandOneTwo) gameMap[5]);
		assertThat(qianFuRen.getMoney(), is(9800));
		assertThat(((BuildingLandOneTwo) gameMap[5]).getBelongTo(), is(qianFuRen));
		String instruction = "sell 5";
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("sell land 5, money:200"));
		assertThat(qianFuRen.getMoney(), is(10000));
		assertNull(((Land) gameMap[5]).getBelongTo());
	}

	@Test
	public void should_return_not_your_building_when_sell_5() {
		String instruction = "sell 5";
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("not your building"));
	}

	@Test
	public void should_return_sell_n_building_when_sell_100() {
		String instruction = "sell 100";
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("sell n(0<n<" + gameMap.length + ")"));
	}

	@Test
	public void should_return_50_100_when_sell_tool_1() {
		qianFuRen.addGamePoint(100);
		qianFuRen.buyProp(new Block());
		assertThat(qianFuRen.getGamePoint(), CoreMatchers.is(50));
		String instruction = "sellTool 1";
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("sell block,GP:50"));
		assertThat(qianFuRen.getGamePoint(), CoreMatchers.is(100));
	}

	@Test
	public void should_return_sell_tool_n_when_sell_tool_5() {
		qianFuRen.addGamePoint(100);
		qianFuRen.buyProp(new Block());
		String instruction = "sellTool 5";
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("sellTool n(n={1,2,3})"));
	}

	@Test
	public void should_return_you_do_not_have_a_block_when_sell_tool_5() {
		String instruction = "sellTool 1";
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("you don't have a block"));
	}

	@Test
	public void should_return_quit_when_input_is_quit() {
		assertThat(interpreter.interpret("quit", gameMap, players, 0), is("quit"));
	}


	@Test
	public void should_return_occupy_when_input_is_bomb_1_and_map_1_has_been_occupied() {
		String instruction = "bomb 1";
		gameMap[1].setProp(new Bomb());
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("place has been occupied"));
	}

	@Test
	public void should_return_occupy_when_input_is_block_1_and_map_1_has_been_occupied() {
		String instruction = "block 1";
		gameMap[1].setProp(new Bomb());
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("place has been occupied"));
	}

	@Test
	public void should_return_occupy_when_input_is_bomb_1_and_player_2_stand_at_map_1() {
		String instruction = "bomb 1";
		aTuBo.setCurrentMapPosition(1);
		players = new Player[]{qianFuRen, aTuBo};
		gameMap[1].setProp(new Bomb());
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("place has been occupied"));
	}

	@Test
	public void should_return_occupy_when_input_is_block_1_and_player_2_stand_at_map_1() {
		String instruction = "block 1";
		aTuBo.setCurrentMapPosition(1);
		players = new Player[]{qianFuRen, aTuBo};
		gameMap[1].setProp(new Bomb());
		assertThat(interpreter.interpret(instruction, gameMap, players, 0), is("place has been occupied"));
	}
}
