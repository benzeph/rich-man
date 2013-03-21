package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.BuildingLandOneTwo;
import org.thoughtworks.zeph.rich.map.unit.Grid;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
	public void should_return_9800_when_player_buy_this_empty_building_land() {
		grid = new BuildingLandOneTwo(2, '0', "Y");
		grid.doesWhatItNeedToDo(player);
		assertThat(player.getMoney(), is(9800));
	}

}