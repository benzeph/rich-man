package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.BuildingLandThree;
import org.thoughtworks.zeph.rich.map.unit.Grid;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BuildingLandThreeTest {
	private PlayerFactory playerFactory = new PlayerFactoryImp();
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
	public void should_return_9500_when_player_buy_this_empty_land() {
		grid = new BuildingLandThree(25, '0', "Y");
		grid.doesWhatItNeedToDo(player);
		assertThat(player.getMoney(), is(9500));
	}

	@Test
	public void should_return_10000_when_player_do_not_buy_this_empty_land() {
		grid = new BuildingLandThree(25, '0', "N");
		grid.doesWhatItNeedToDo(player);
		assertThat(player.getMoney(), is(10000));
	}

	@Test
	public void should_return_9750_when_player_pay_rent() {
		Player anotherPlayer = playerFactory.createPlayer(2, 10000);
		grid = new BuildingLandThree(25, '0');
		grid.setOwner(anotherPlayer);
		grid.doesWhatItNeedToDo(player);
		assertThat(player.getMoney(), is(9750));
	}

	@Test
	public void should_return_9500_when_player_level_up_this_land() {
		grid = new BuildingLandThree(25, '0', "Y");
		grid.setOwner(player);
		grid.doesWhatItNeedToDo(player);
		assertThat(player.getMoney(), is(9500));
	}

	@Test
	public void should_return_10000_when_player_do_not_level_up_this_land() {
		grid = new BuildingLandThree(25, '0', "N");
		grid.setOwner(player);
		grid.doesWhatItNeedToDo(player);
		assertThat(player.getMoney(), is(10000));
	}
}
