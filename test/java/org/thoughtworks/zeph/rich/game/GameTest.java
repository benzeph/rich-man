package org.thoughtworks.zeph.rich.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.thoughtworks.zeph.rich.god.WealthGod;
import org.thoughtworks.zeph.rich.map.*;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;
import org.thoughtworks.zeph.rich.props.Bomb;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

	private Player qianFuRen;
	private Player aTuBo;
	private Rich game;
	private Map[] map;
	private PlayerFactory playerFactory = new PlayerFactoryImp();

	private void mapInitialize() {
		map = new Map[70];
		map[0] = new Map(0, 'S');
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
	}

	@Before
	public void setUp() {
		qianFuRen = playerFactory.createPlayer(1, 10000);
		aTuBo = playerFactory.createPlayer(2, 10000);
		mapInitialize();
		game = new Rich(new Player[]{qianFuRen, aTuBo}, map);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_quit_the_game_when_input_is_instructions_below() {
		String instruction = "roll\nn\nroll\nn\nroll\nn\nquit";
		game.runForTest(instruction);
		assertThat(qianFuRen.getMoney(), is(10000));
	}

	@Test
	public void should_add_game_point_when_input_is_instructions_below() {
		String instruction = "roll\nroll\nquit";
		qianFuRen.setCurrentMapPosition(62);
		aTuBo.setCurrentMapPosition(62);
		game.runForTest(instruction);
	}

	@Test
	public void should_pay_rent_300_when_input_is_roll_one_like_below() {
		String instruction = "roll one\ny\nroll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nquit";
		game.runForTest(instruction);
		assertThat(qianFuRen.getMoney(), is(9700));
	}

	@Test
	public void should_stay_in_hospital_when_play_one_put_a_bomb() {
		String instruction = "roll one\ny\nbomb 2\nroll one\nroll one\ny\nroll one\nroll one\nroll one\ny\nquit";
		aTuBo.addGamePoint(1000);
		aTuBo.buyProp(new Bomb());
		game.runForTest(instruction);
		assertThat(qianFuRen.getCurrentMapPosition(), is(14));
	}

	@Test
	public void should_return_3_when_player_buy_bomb_robot_block() {
		String instruction = "roll one\n1\n2\n3\nF\nquit";
		qianFuRen.setCurrentMapPosition(27);
		qianFuRen.addGamePoint(1000);
		game.runForTest(instruction);
		assertThat(qianFuRen.getProps().size(), is(3));
	}

	@Test
	public void should_return_0_when_player_walk_in_prop_room_but_do_not_have_enough_game_point() {
		String instruction = "roll one\nquit";
		qianFuRen.setCurrentMapPosition(27);
		game.runForTest(instruction);
		assertThat(qianFuRen.getProps().size(), is(0));
	}

	@Test
	public void should_return_12000_when_player_walk_in_gift_room() {
		String instruction = "roll one\n1\nquit";
		qianFuRen.setCurrentMapPosition(34);
		game.runForTest(instruction);
		assertThat(qianFuRen.getMoney(), is(12000));
	}

	@Test
	public void should_return_0_when_player_stay_in_hospital_3_days() {
		String instruction = "roll one\ny\nroll one\ny\nroll one\ny\nroll one\ny\nquit";
		qianFuRen.setCurrentMapPosition(14);
		qianFuRen.setHospitalDays(3);
		game.runForTest(instruction);
		assertThat(qianFuRen.getCurrentMapPosition(), is(15));
		assertThat(qianFuRen.getMoney(), is(9800));
	}

	@Test
	public void should_not_pay_the_rent_when_player_got_the_wealth_god() {
		String instruction = "roll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nroll one\ny\n" +
				"roll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nquit";
		Map[] gameMap = game.getMap();
		((Land) gameMap[1]).setBelongTo(aTuBo);
		((Land) gameMap[2]).setBelongTo(aTuBo);
		((Land) gameMap[3]).setBelongTo(aTuBo);
		((Land) gameMap[4]).setBelongTo(aTuBo);
		((Land) gameMap[5]).setBelongTo(aTuBo);
		((Land) gameMap[6]).setBelongTo(aTuBo);
		qianFuRen.setGod(new WealthGod());
		game.runForTest(instruction);
		assertThat(qianFuRen.getMoney(), is(9800));
	}

	@Test
	public void should_show_a_prop_when_player_set_a_bomb() {
		String instruction = "bomb 2\nbomb 3\ndrawMap\nquit";
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(new Bomb());
		qianFuRen.buyProp(new Bomb());
		game.runForTest(instruction);
	}

	@Test
	public void should_show_help_when_player_input_help() {
		String instruction = "help\nquery\nquit";
		game.runForTest(instruction);
	}

	@Test
	public void should_broke_when_player_do_not_have_enough_money_to_pay_the_rent() {
		String instruction = "roll one\ny\nroll one";
		aTuBo.setMoney(99);
		game.runForTest(instruction);
	}

	@Ignore
	@Test
	public void should_stay_in_0_when_player_stay_in_69_and_roll_one_step() {
		String instruction = "roll\nquit";
		Player qianFuRen = mock(Player.class);
		Player aTuBo = mock(Player.class);
		when(qianFuRen.dice()).thenReturn(1);
		qianFuRen.setCurrentMapPosition(69);
		Rich game = new Rich(new Player[]{qianFuRen, aTuBo}, map);
		game.runForTest(instruction);
		assertThat(qianFuRen.getCurrentMapPosition(), is(0));
	}
}
