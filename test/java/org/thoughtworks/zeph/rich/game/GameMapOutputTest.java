package org.thoughtworks.zeph.rich.game;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.player.Player;

public class GameMapOutputTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_draw_the_map_when_input_rich_map() {
		Player player1 = new Player("Qian Furen", 1);
		Player player2 = new Player("A Tubo", 2);
		Game game = new RichGame(new Player[]{player1, player2});
		game.drawMap();
	}
}
