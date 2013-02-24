package org.thoughtworks.zeph.rich.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.player.Player;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_28_when_input_up_map() {
		String str = "S0000000000000H0000000000000T";
		assertThat(str.length() - 1, is(28));
	}

	@Test
	public void should_return_34_when_input_up_and_right() {
		String str = "S0000000000000H0000000000000T000000";
		assertThat(str.length() - 1, is(34));
	}

	@Test
	public void should_return_63_when_input_up_and_right() {
		String str = "S0000000000000H0000000000000T000000M0000000000000P0000000000000G";
		assertThat(str.length() - 1, is(63));
	}

	@Test
	public void should_return_69_when_input_the_right_map() {
		String str = "S0000000000000H0000000000000T000000M0000000000000P0000000000000G$$$$$$";
		assertThat(str.length() - 1, is(69));
	}

	@Test
	public void should_quit_the_game_when_input_is_instructions_below() {
		String instruction = "roll\nn\nroll\nn\nroll\nn\nquit";
		Player player1 = new Player("Qian Furen", 1);
		Player player2 = new Player("A Tubo", 2);
		Game game = new RichGame(new Player[]{player1, player2});
		game.runForTest(instruction);
		assertThat(player1.getMoney(), is(10000));
	}
	@Test
	public void should_add_game_point_when_input_is_instructions_below() {
		String instruction = "roll\nroll\nquit";
		Player player1 = new Player("Qian Furen", 1);
		player1.setCurrentMapPosition(62);
		Player player2 = new Player("A Tubo", 2);
		player2.setCurrentMapPosition(62);
		Game game = new RichGame(new Player[]{player1, player2});
		game.runForTest(instruction);
	}
}
