package org.thoughtworks.zeph.rich.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.BuildingLandOneTwo;
import org.thoughtworks.zeph.rich.tools.Block;
import org.thoughtworks.zeph.rich.tools.Robot;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

	private PlayerFactory playerFactory = new PlayerFactoryImp();
	private Player player;

	@Before
	public void setUp() {
		player = playerFactory.createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_10200_when_sell_a_building_land_one_two() {
		player.addBuilding(new BuildingLandOneTwo(2, '0'));
		player.sellLand(new BuildingLandOneTwo(2, '0'));
		assertThat(player.getMoney(), is(10200));
	}

	@Test
	public void should_return_50_when_sell_a_block() {
		player.addProp(1);
		player.sellTool(new Block());
		assertThat(player.getGamePoint(), is(50));
	}

	@Test
	public void should_return_false_when_use_a_robot() {
		player.addProp(2);
		player.useTool(new Robot());
		assertThat(player.isPlayerHasARobot(), is(false));
	}
}
