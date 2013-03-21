package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.BuildingLandOneTwo;
import org.thoughtworks.zeph.rich.map.unit.Grid;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

public class BuildingLandOneTwoTest {

	private final PlayerFactory playerFactory = new PlayerFactoryImp();
	private Player player;
	private Grid grid;

	@Before
	public void setUp() {
		player = playerFactory.createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void test(){
		grid = new BuildingLandOneTwo(2,'0');
		grid.doesWhatItNeedToDo(player);
	}

	@Ignore
	@Test
	public void should_return_level_1_building_lot_1_when_input_is_level_0_building_lot_1_and_level_up() {

	}

	@Ignore
	@Test
	public void should_return_level_2_building_lot_3_when_input_is_level_1_building_lot_3_and_level_up() {

	}

	@Ignore
	@Test
	public void should_return_level_3_building_lot_45_when_input_is_level_2_building_lot_45_and_level_up() {

	}

	@Ignore
	@Test
	public void should_return_100_when_input_is_level_0_building_lot_12() {

	}
}