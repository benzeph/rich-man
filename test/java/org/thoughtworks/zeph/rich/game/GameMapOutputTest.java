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
	@Test
	public void should_draw_the_map_when_input_is_rich_map_player_stay_at_2_and_3() {
		Player player1 = new Player("Qian Furen", 1);
		Player player2 = new Player("A Tubo", 2);
		Game game = new RichGame(new Player[]{player1, player2});
		player1.setCurrentMapPosition(2);
		player2.setCurrentMapPosition(3);
		game.runForTest("roll one\ny\nroll one\ny\nquit");
		game.drawMap();
	}
}
