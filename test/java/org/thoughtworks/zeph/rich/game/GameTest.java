package org.thoughtworks.zeph.rich.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.FirstMapFactory;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.map.MapFactory;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

	private Player qianFuRen;
	private Player aTuBo;
	private Rich game;
	private MapFactory mapFactory = new FirstMapFactory();
	private Map map = mapFactory.createMap();
	private PlayerFactory playerFactory = new PlayerFactoryImp();

	@Before
	public void setUp() {
		qianFuRen = playerFactory.createPlayer(1, 10000);
		aTuBo = playerFactory.createPlayer(2, 10000);
		game = new Rich(new Player[]{qianFuRen, aTuBo}, map);
	}

	@After
	public void tearDown() {

	}

	@Ignore
	@Test
	public void should_quit_the_game_when_input_is_instructions_below() {
		String instruction = "roll\nn\nroll\nn\nroll\nn\nquit";
		game.run(instruction);
		assertThat(qianFuRen.getMoney(), is(10000));
	}

	@Ignore
	@Test
	public void should_add_game_point_when_input_is_instructions_below() {
		String instruction = "roll\nroll\nquit";
		qianFuRen.setCurrentMapPosition(62);
		aTuBo.setCurrentMapPosition(62);
		game.run(instruction);
	}

	@Ignore
	@Test
	public void should_pay_rent_300_when_input_is_roll_one_like_below() {
		String instruction = "roll one\ny\nroll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nquit";
		game.run(instruction);
		assertThat(qianFuRen.getMoney(), is(9700));
	}


	@Ignore
	@Test
	public void should_return_3_when_player_buy_bomb_robot_block() {
		String instruction = "roll one\n1\n2\n3\nF\nquit";
		qianFuRen.setCurrentMapPosition(27);
		qianFuRen.addGamePoint(1000);
		game.run(instruction);
		//assertThat(qianFuRen.getProps().size(), is(3));
	}

	@Ignore
	@Test
	public void should_return_0_when_player_walk_in_prop_room_but_do_not_have_enough_game_point() {
		String instruction = "roll one\nquit";
		qianFuRen.setCurrentMapPosition(27);
		game.run(instruction);
		//(qianFuRen.getProps().size(), is(0));
	}

	@Ignore
	@Test
	public void should_return_12000_when_player_walk_in_gift_room() {
		String instruction = "roll one\n1\nquit";
		qianFuRen.setCurrentMapPosition(34);
		game.run(instruction);
		assertThat(qianFuRen.getMoney(), is(12000));
	}

	@Ignore
	@Test
	public void should_return_0_when_player_stay_in_hospital_3_days() {
		String instruction = "roll one\ny\nroll one\ny\nroll one\ny\nroll one\ny\nquit";
		qianFuRen.setCurrentMapPosition(14);
		qianFuRen.setHospitalDays(3);
		game.run(instruction);
		assertThat(qianFuRen.getCurrentMapPosition(), is(15));
		assertThat(qianFuRen.getMoney(), is(9800));
	}

	@Ignore
	@Test
	public void should_not_pay_the_rent_when_player_got_the_wealth_god() {
		String instruction = "roll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nroll one\ny\n" +
				"roll one\nroll one\ny\nroll one\nroll one\ny\nroll one\nquit";
		/*Grid[] gameMap = game.getMap();
		((Land) gameMap[1]).setOwner(aTuBo);
		((Land) gameMap[2]).setOwner(aTuBo);
		((Land) gameMap[3]).setOwner(aTuBo);
		((Land) gameMap[4]).setOwner(aTuBo);
		((Land) gameMap[5]).setOwner(aTuBo);
		((Land) gameMap[6]).setOwner(aTuBo);
		qianFuRen.setGod(new WealthGod());
		game.run(instruction);
		assertThat(qianFuRen.getMoney(), is(9800));*/
	}


	@Ignore
	@Test
	public void should_show_help_when_player_input_help() {
		String instruction = "help\nquery\nquit";
		game.run(instruction);
	}

	@Ignore
	@Test
	public void should_broke_when_player_do_not_have_enough_money_to_pay_the_rent() {
		String instruction = "roll one\ny\nroll one";
		game.run(instruction);
	}

	@Ignore
	@Test
	public void should_stay_in_0_when_player_stay_in_69_and_roll_one_step_mock() {
		String instruction = "roll\nquit";
		Player qianFuRen = mock(Player.class);
		Player aTuBo = mock(Player.class);
		when(qianFuRen.dice()).thenReturn(1);
		qianFuRen.setCurrentMapPosition(69);
		Rich game = new Rich(new Player[]{qianFuRen, aTuBo}, map);
		game.run(instruction);
		assertThat(qianFuRen.getCurrentMapPosition(), is(0));
	}
}
