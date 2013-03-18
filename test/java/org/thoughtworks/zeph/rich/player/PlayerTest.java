package org.thoughtworks.zeph.rich.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.BuildingLandOneTwo;
import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.props.Robot;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

	private PlayerFactory playerFactory = new PlayerFactoryImp();
	private Player qianFuRen = playerFactory.createPlayer(1, 10000);
	private Player aTuBo = playerFactory.createPlayer(2, 10000);

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_2_when_role_buys_two_lands() {
		Land buildingLotOneTwo1 = new BuildingLandOneTwo(2, '0');
		Land buildingLotOneTwo2 = new BuildingLandOneTwo(3, '0');
		qianFuRen.buyLand(buildingLotOneTwo1);
		qianFuRen.buyLand(buildingLotOneTwo2);
		Map<Integer, Land> lands = qianFuRen.getLands();
		assertThat(lands.size(), is(2));
	}

	@Test
	public void should_return_level_1_land_when_role_level_up_land_from_level_0() {
		Land buildingLotOneTwo = new BuildingLandOneTwo(2, '0');
		Land land2 = new BuildingLandOneTwo(2, '0');
		land2.levelUp();
		land2.setBelongTo(qianFuRen);
		qianFuRen.buyLand(buildingLotOneTwo);
		qianFuRen.upgradeLand(buildingLotOneTwo);
		Map<Integer, Land> lands = qianFuRen.getLands();
		Land land = lands.get(2);
		assertThat(land, is(land2));
	}

	@Test
	public void should_return_null_when_role_sells_land() {
		Land land = new BuildingLandOneTwo(2, '0');
		qianFuRen.buyLand(land);
		qianFuRen.sellLand(land);
		Map<Integer, Land> lands = qianFuRen.getLands();
		Land land2 = lands.get(2);
		assertNull(land2);
	}

	@Test
	public void should_return_9900_when_role_pay_the_rent_of_land_1_level_0() {
		Land land = new BuildingLandOneTwo(2, '0');
		qianFuRen.payRent(land, aTuBo);
		assertThat(qianFuRen.getMoney(), is(9900));
		assertThat(aTuBo.getMoney(), is(10100));
	}

	@Test
	public void should_return_3_when_role_buy_3_props() {
		Prop block = new Block();
		Prop robot = new Robot();
		Prop bomb = new Bomb();
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(block);
		qianFuRen.buyProp(robot);
		qianFuRen.buyProp(bomb);
		Map<Integer, Integer> props = qianFuRen.getProps();
		assertThat(props.size(), is(3));
	}

	@Test
	public void should_return_15_when_role_buy_1_bomb() {
		Prop bomb = new Bomb();
		qianFuRen.addGamePoint(15);
		qianFuRen.addGamePoint(50);
		qianFuRen.buyProp(bomb);
		assertThat(qianFuRen.getGamePoint(), is(15));
	}

	@Test
	public void should_return_1_when_role_buy_2_bomb() {
		Prop bomb1 = new Bomb();
		Prop bomb2 = new Bomb();
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(bomb1);
		qianFuRen.buyProp(bomb2);
		Map<Integer, Integer> props = qianFuRen.getProps();
		assertThat(props.size(), is(1));
	}

	@Test
	public void should_return_1_when_role_use_a_bomb() {
		Prop bomb1 = new Bomb();
		Prop bomb2 = new Bomb();
		qianFuRen.addGamePoint(1000);
		qianFuRen.buyProp(bomb1);
		qianFuRen.buyProp(bomb2);
		boolean isUsed = qianFuRen.useProp(bomb1);
		Map<Integer, Integer> props = qianFuRen.getProps();
		assertThat(isUsed, is(true));
		assertThat(props.get(bomb1.getId()), is(1));
	}

	@Test
	public void should_return_0_when_role_buy_and_sell_a_bomb() {
		Prop bomb = new Bomb();
		qianFuRen.buyProp(bomb);
		qianFuRen.sellProp(bomb);
		assertThat(qianFuRen.getGamePoint(), is(0));
	}

	@Test
	public void should_return_12000_when_role_get_a_gift_of_2000() {
		qianFuRen.addMoney(2000);
		assertThat(qianFuRen.getMoney(), is(12000));
	}

	@Test
	public void should_return_200_when_role_get_a_gift_of_200_game_point() {
		qianFuRen.addGamePoint(200);
		assertThat(qianFuRen.getGamePoint(), is(200));
	}

	@Test
	public void should_return_1_when_mock_a_player_dice(){
		Player player = mock(Player.class);
		when(player.dice()).thenReturn(1);
		assertThat(player.dice(),is(1));
	}
}
