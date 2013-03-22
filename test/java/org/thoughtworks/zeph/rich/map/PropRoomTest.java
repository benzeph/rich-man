package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.Grid;
import org.thoughtworks.zeph.rich.map.unit.ToolRoom;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PropRoomTest {

	private Player player;
	private Grid grid;
	private PlayerFactory playerFactory = new PlayerFactoryImp();

	@Before
	public void setUp() {
		player = playerFactory.createPlayer(1, 10000);

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_10_when_play_buy_3_bomb() {
		grid = new ToolRoom(23, 'T', "3\n3\n3");
		player.addGamePoint(160);
		grid.doesWhatItNeedToDo(player);
		assertThat(player.getGamePoint(), is(10));
	}

	@Test
	public void should_return_0_when_play_buy_3_bomb() {
		grid = new ToolRoom(23, 'T', "1\n2\n3\n2");
		player.addGamePoint(160);
		grid.doesWhatItNeedToDo(player);
		assertThat(player.getGamePoint(), is(0));
	}

}
