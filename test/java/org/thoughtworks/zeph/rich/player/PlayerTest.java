package org.thoughtworks.zeph.rich.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.BuildingLotOneTwo;
import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.props.Robot;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PlayerTest {

	private Player player;

	@Before
	public void setUp() {

		player = new Player("Qian Furen", 1);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_2_when_role_buys_two_lands() {
		Player player = new Player("Qian Furen", 1);
		Land buildingLotOneTwo1 = new BuildingLotOneTwo(2,'0');
		Land buildingLotOneTwo2 = new BuildingLotOneTwo(3,'0');
		player.buyLand(buildingLotOneTwo1);
		player.buyLand(buildingLotOneTwo2);
		Map<Integer, Land> lands = player.getLands();
		assertThat(lands.size(), is(2));
	}

	@Test
	public void should_return_level_1_land_when_role_level_up_land_from_level_0() {
		Land buildingLotOneTwo = new BuildingLotOneTwo(2,'0');
		Land land2 = new BuildingLotOneTwo(2,'0');
		land2.levelUp();
		land2.setBelongTo(1);
		player.buyLand(buildingLotOneTwo);
		player.upgradeLand(buildingLotOneTwo);
		Map<Integer, Land> lands = player.getLands();
		Land land = lands.get(2);
		assertThat(land, is(land2));
	}

	@Test
	public void should_return_null_when_role_sells_land() {
		Land land = new BuildingLotOneTwo(2,'0');
		player.buyLand(land);
		player.sellLand(land);
		Map<Integer, Land> lands = player.getLands();
		Land land2 = lands.get(2);
		assertNull(land2);
	}

	@Test
	public void should_return_9900_when_role_pay_the_rent_of_land_1_level_0() {
		Land land = new BuildingLotOneTwo(2,'0');
		Player player1 = new Player("A Tubo",2);
		player.payRent(land,player1);
		assertThat(player.getMoney(), is(9900));
		assertThat(player1.getMoney(), is(10100));
	}

	@Test
	public void should_return_10100_when_role_collect_the_rent_of_land_1_level_0() {
		Land land = new BuildingLotOneTwo(2,'0');
		player.buyLand(land);
		player.collectRent(land.getMapId());
		assertThat(player.getMoney(), is(9900));
	}

	@Test
	public void should_return_3_when_role_buy_3_props() {
		Prop block = new Block();
		Prop robot = new Robot();
		Prop bomb = new Bomb();
		player.addGamePoint(1000);
		player.buyProp(block);
		player.buyProp(robot);
		player.buyProp(bomb);
		Map<Integer, Integer> props = player.getProps();
		assertThat(props.size(), is(3));
	}

	@Test
	public void should_return_15_when_role_buy_1_bomb() {
		Prop bomb = new Bomb();
		player.addGamePoint(15);
		player.addGamePoint(50);
		player.buyProp(bomb);
		assertThat(player.getGamePoint(), is(15));
	}

	@Test
	public void should_return_1_when_role_buy_2_bomb() {
		Prop bomb1 = new Bomb();
		Prop bomb2 = new Bomb();
		player.addGamePoint(1000);
		player.buyProp(bomb1);
		player.buyProp(bomb2);
		Map<Integer, Integer> props = player.getProps();
		assertThat(props.size(), is(1));
	}

	@Test
	public void should_return_1_when_role_use_a_bomb() {
		Prop bomb1 = new Bomb();
		Prop bomb2 = new Bomb();
		player.addGamePoint(1000);
		player.buyProp(bomb1);
		player.buyProp(bomb2);
		boolean isUsed = player.useProp(bomb1);
		Map<Integer, Integer> props = player.getProps();
		assertThat(isUsed, is(true));
		assertThat(props.get(bomb1.getId()), is(1));
	}

	@Test
	public void should_return_0_when_role_buy_and_sell_a_bomb() {
		Prop bomb = new Bomb();
		player.buyProp(bomb);
		player.sellProp(bomb);
		assertThat(player.getGamePoint(),is(0));
	}

	@Test
	public void should_return_12000_when_role_get_a_gift_of_2000(){
		player.addMoney(2000);
		assertThat(player.getMoney(),is(12000));
	}

	@Test
	public void should_return_200_when_role_get_a_gift_of_200_game_point(){
		player.addGamePoint(200);
		assertThat(player.getGamePoint(),is(200));
	}
}
