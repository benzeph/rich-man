package org.thoughtworks.zeph.rich.role;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.BuildingLotOneTwo;
import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.props.RoadBlock;
import org.thoughtworks.zeph.rich.props.Robot;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoleTest {

	private Role role;

	@Before
	public void setUp() {

		role = new Role("Qian Furen", 1);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_2_when_role_buys_two_lands() {
		Role role = new Role("Qian Furen", 1);
		Land buildingLotOneTwo1 = new BuildingLotOneTwo(2);
		Land buildingLotOneTwo2 = new BuildingLotOneTwo(3);
		role.buyLand(buildingLotOneTwo1);
		role.buyLand(buildingLotOneTwo2);
		Map<Integer, Land> lands = role.getLands();
		assertThat(lands.size(), is(2));
	}

	@Test
	public void should_return_level_1_land_when_role_level_up_land_from_level_0() {
		Land buildingLotOneTwo = new BuildingLotOneTwo(2);
		Land land2 = new BuildingLotOneTwo(2);
		land2.levelUp();
		role.buyLand(buildingLotOneTwo);
		role.upgradeLand(buildingLotOneTwo);
		Map<Integer, Land> lands = role.getLands();
		Land land = lands.get(2);
		assertThat(land, is(land2));
	}

	@Test
	public void should_return_null_when_role_sells_land() {
		Land land = new BuildingLotOneTwo(2);
		role.buyLand(land);
		role.sellLand(2);
		Map<Integer, Land> lands = role.getLands();
		Land land2 = lands.get(2);
		assertThat(null, is(land2));
	}

	@Test
	public void should_return_9900_when_role_pay_the_rent_of_land_1_level_0() {
		Land land = new BuildingLotOneTwo(2);
		role.payRent(land);
		assertThat(role.getMoney(), is(9900));
	}

	@Test
	public void should_return_10100_when_role_collect_the_rent_of_land_1_level_0() {
		Land land = new BuildingLotOneTwo(2);
		role.buyLand(land);
		role.collectRent(land.getMapId());
		assertThat(role.getMoney(), is(9900));
	}

	@Test
	public void should_return_3_when_role_buy_3_props() {
		Prop block = new RoadBlock();
		Prop robot = new Robot();
		Prop bomb = new Bomb();
		role.addGamePoint(1000);
		role.buyProps(block);
		role.buyProps(robot);
		role.buyProps(bomb);
		Map<Integer, Integer> props = role.getProps();
		assertThat(props.size(), is(3));
	}

	@Test
	public void should_return_15_when_role_buy_1_bomb() {
		Prop bomb = new Bomb();
		role.addGamePoint(15);
		role.addGamePoint(50);
		role.buyProps(bomb);
		assertThat(role.getGamePoint(), is(15));
	}

	@Test
	public void should_return_1_when_role_buy_2_bomb() {
		Prop bomb1 = new Bomb();
		Prop bomb2 = new Bomb();
		role.addGamePoint(1000);
		role.buyProps(bomb1);
		role.buyProps(bomb2);
		Map<Integer, Integer> props = role.getProps();
		assertThat(props.size(), is(1));
	}

	@Test
	public void should_return_1_when_role_use_a_bomb() {
		Prop bomb1 = new Bomb();
		Prop bomb2 = new Bomb();
		role.addGamePoint(1000);
		role.buyProps(bomb1);
		role.buyProps(bomb2);
		boolean isUsed = role.useProp(bomb1);
		Map<Integer, Integer> props = role.getProps();
		assertThat(isUsed, is(true));
		assertThat(props.get(bomb1.getId()), is(1));
	}

	@Test
	public void should_return_0_when_role_buy_and_sell_a_bomb() {
		Prop bomb = new Bomb();
		role.buyProps(bomb);
		role.sellProp(bomb);
		assertThat(role.getGamePoint(),is(0));
	}

	@Test
	public void should_return_12000_when_role_get_a_gift_of_2000(){
		role.addMoney(2000);
		assertThat(role.getMoney(),is(12000));
	}

	@Test
	public void should_return_200_when_role_get_a_gift_of_200_game_point(){
		role.addGamePoint(200);
		assertThat(role.getGamePoint(),is(200));
	}
}
