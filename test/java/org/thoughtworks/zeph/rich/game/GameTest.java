package org.thoughtworks.zeph.rich.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.god.WealthGod;
import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Bomb;

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

	@Test
	public void should_pay_rent_300_when_input_is_roll_one_like_below() {
		String instruction = "roll one\ny\nroll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nquit";
		Player player1 = new Player("Qian Furen", 1);
		Player player2 = new Player("A Tubo", 2);
		Game game = new RichGame(new Player[]{player1, player2});
		game.runForTest(instruction);
		assertThat(player1.getMoney(), is(9700));
	}

	@Test
	public void should_stay_in_hospital_when_play_one_put_a_bomb() {
		String instruction = "roll one\ny\nbomb 2\nroll one\nroll one\ny\nroll one\nroll one\nroll one\ny\nquit";
		Player player1 = new Player("Qian Furen", 1);
		Player player2 = new Player("A Tubo", 2);
		player2.addGamePoint(1000);
		player2.buyProp(new Bomb());
		Game game = new RichGame(new Player[]{player1, player2});
		game.runForTest(instruction);
		assertThat(player1.getCurrentMapPosition(), is(14));
	}

	@Test
	public void should_return_3_when_player_buy_bomb_robot_block() {
		String instruction = "roll one\n1\n2\n3\nF\nquit";
		Player player1 = new Player("Qian Furen", 1);
		player1.setCurrentMapPosition(27);
		player1.addGamePoint(1000);
		Player player2 = new Player("A Tubo", 2);
		Game game = new RichGame(new Player[]{player1, player2});
		game.runForTest(instruction);
		assertThat(player1.getProps().size(), is(3));
	}

	@Test
	public void should_return_0_when_player_walk_in_prop_room_but_do_not_have_enough_game_point() {
		String instruction = "roll one\nquit";
		Player player1 = new Player("Qian Furen", 1);
		player1.setCurrentMapPosition(27);
		Player player2 = new Player("A Tubo", 2);
		Game game = new RichGame(new Player[]{player1, player2});
		game.runForTest(instruction);
		assertThat(player1.getProps().size(), is(0));
	}

	@Test
	public void should_return_12000_when_player_walk_in_gift_room() {
		String instruction = "roll one\n1\nquit";
		Player player1 = new Player("Qian Furen", 1);
		player1.setCurrentMapPosition(34);
		Player player2 = new Player("A Tubo", 2);
		Game game = new RichGame(new Player[]{player1, player2});
		game.runForTest(instruction);
		assertThat(player1.getMoney(), is(12000));
	}

	@Test
	public void should_return_0_when_player_stay_in_hospital_3_days() {
		String instruction = "roll one\ny\nroll one\ny\nroll one\ny\nroll one\ny\nquit";
		Player player1 = new Player("Qian Furen", 1);
		Player player2 = new Player("A Tubo", 2);
		player1.setCurrentMapPosition(14);
		player1.setHospitalDays(3);
		Game game = new RichGame(new Player[]{player1, player2});
		game.runForTest(instruction);
		assertThat(player1.getCurrentMapPosition(), is(15));
		assertThat(player1.getMoney(),is(9800));
	}

	@Test
	public void should_not_pay_the_rent_when_player_got_the_wealth_god(){
		String instruction = "roll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nroll one\ny\n"+
				"roll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nquit";
		Player player1 = new Player("Qian Furen", 1);
		Player player2 = new Player("A Tubo", 2);
		Game game = new RichGame(new Player[]{player1, player2});
		Map[] gameMap = game.getGameMap();
		((Land)gameMap[1]).setBelongTo(2);
		((Land)gameMap[2]).setBelongTo(2);
		((Land)gameMap[3]).setBelongTo(2);
		((Land)gameMap[4]).setBelongTo(2);
		((Land)gameMap[5]).setBelongTo(2);
		((Land)gameMap[6]).setBelongTo(2);
		player1.setGod(new WealthGod());
		game.runForTest(instruction);
		assertThat(player1.getMoney(),is(9800));
	}
}
