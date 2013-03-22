package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.Grid;
import org.thoughtworks.zeph.rich.map.unit.Mine;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MineTest {

	private Player player;
	private PlayerFactory playerFactory = new PlayerFactoryImp();
	private Grid mine;

	@Before
	public void setUp() {
		player = playerFactory.createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_20_when_player_stand_on_mine_20() {
		mine = new Mine(67, 20, '$');
		mine.doesWhatItNeedToDo(player);
		assertThat(player.getGamePoint(), is(20));
	}
}
