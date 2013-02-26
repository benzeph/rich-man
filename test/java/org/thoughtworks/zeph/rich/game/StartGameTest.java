package org.thoughtworks.zeph.rich.game;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StartGameTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_end_game_when_game_start_and_input_quit() {
		String instruction = "dss\nsds\nsdwe\nsdwe\nrich\n200\n3000\n12345\n65\n123\nquit";
		StartGame startGame = new StartGame(instruction);
		startGame.startForTest();
	}
	@Test
	public void should_set_default_money_when_game_start_and_input_quit() {
		String instruction = "dss\nsds\nsdwe\nsdwe\nrich\n\n123\nquit";
		StartGame startGame = new StartGame(instruction);
		startGame.startForTest();
	}
}
