package org.thoughtworks.zeph.rich.interpreter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.*;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.props.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class InterpreterTest {

	private Map[] gameMap;
	private Player player;
	private Interpreter interpreter;

	@Before
	public void setUp() {

		gameMap = new Map[70];
		gameMap[0] = new Map(0);

		for (int i = 1; i <= 13; i++) {
			gameMap[i] = new BuildingLotOneTwo(i);
		}

		gameMap[14] = new Hospital(14);

		for (int i = 15; i <= 27; i++) {
			gameMap[i] = new BuildingLotOneTwo(i);
		}

		gameMap[28] = new PropRoom(28);

		for (int i = 29; i <= 34; i++) {
			gameMap[i] = new BuildingLotThree(i);
		}

		gameMap[35] = new GiftRoom(35);

		for (int i = 36; i <= 48; i++) {
			gameMap[i] = new BuildingLotFourFive(i);
		}

		gameMap[49] = new Prison(49);

		for (int i = 50; i <= 62; i++) {
			gameMap[i] = new BuildingLotFourFive(i);
		}

		gameMap[61] = new MagicRoom(63);

		gameMap[64] = new Mine(64, 20);
		gameMap[65] = new Mine(65, 80);
		gameMap[66] = new Mine(66, 100);
		gameMap[67] = new Mine(67, 40);
		gameMap[68] = new Mine(68, 80);
		gameMap[69] = new Mine(69, 60);
		player = new Player("Qian Furen", 1);
		interpreter = new Interpreter();
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_a_block_from_map_5_when_input_is_block_5() {
		player.addGamePoint(1000);
		player.buyProp(new Bomb());
		Prop bomb = new Bomb();
		interpreter.interpret("bomb 5", gameMap, player);
		assertThat(gameMap[5].getProp(), is(bomb));
		assertThat(player.getProps().size(), is(0));
	}

	@Test
	public void should_return_true_when_match_bomb_10() {
		String instruction = "bomb 10";
		Pattern pattern = Pattern.compile("bomb (-)?\\d*");
		Matcher matcher = pattern.matcher(instruction);
		assertThat(matcher.matches(), is(true));
	}

	@Test
	public void should_return_true_when_match_bomb_n10() {
		String instruction = "bomb -10";
		Pattern pattern = Pattern.compile("bomb (-)?\\d*");
		Matcher matcher = pattern.matcher(instruction);
		assertThat(matcher.matches(), is(true));
	}

	@Test
	public void should_return_a_block_when_input_is_block_3() {
		player.addGamePoint(1000);
		player.buyProp(new Block());
		String instruction = "block 3";
		Prop block = new Block();
		interpreter.interpret(instruction, gameMap, player);
		assertThat(gameMap[3].getProp(), is(block));
		assertThat(player.getProps().size(), is(0));
	}

	@Test
	public void should_not_stop_at_0_when_roll() {
		String instruction = "roll";
		interpreter.interpret(instruction, gameMap, player);
		assertThat(player.getCurrentMapPosition(), not(0));
	}

	@Test
	public void should_stop_at_3_when_block_3_and_roll() {
		player.addGamePoint(1000);
		player.buyProp(new Block());
		String instruction = "block 3";
		Prop block = new Block();
		interpreter.interpret(instruction, gameMap, player);
		String instructionRoll = "roll";
		interpreter.interpret(instructionRoll, gameMap, player);
		if (player.getCurrentMapPosition() != 3) {
			interpreter.interpret(instructionRoll, gameMap, player);
		}
		assertThat(gameMap[3].getProp(), is(block));
		assertThat(player.getProps().size(), is(0));
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
		interpreter.interpret(instructionBomb, gameMap, player);
		interpreter.interpret(instructionBlock, gameMap, player);
		interpreter.interpret(instructionRobot, gameMap, player);
		for (int i = 1; i <= 10; i++) {
			assertNull(gameMap[i].getProp());
		}
	}

	@Test
	public void should_return_null_and_200_when_sell_5() {
		player.buyLand((BuildingLotOneTwo) gameMap[5]);
		assertThat(player.getMoney(), is(9800));
		assertThat(((BuildingLotOneTwo) gameMap[5]).getBelongTo(), is(player.getId()));
		String instruction = "sell 5";
		interpreter.interpret(instruction, gameMap, player);
		assertThat(player.getMoney(), is(10000));
		assertThat(((Land) gameMap[5]).getBelongTo(), is(0));
	}
}
