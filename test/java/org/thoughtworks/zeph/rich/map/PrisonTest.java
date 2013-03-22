package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.Prison;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PrisonTest {

	private Player player;
	private Prison prison;
	private PlayerFactory playerFactory = new PlayerFactoryImp();

	@Before
	public void setUp() {
		player = playerFactory.createPlayer(1, 10000);
		prison = new Prison(45, 'P');
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_0_when_player_stay_in_prison() {
		Player anotherPlayer = playerFactory.createPlayer(2, 10000);
		prison.addPlayer(anotherPlayer);
		prison.doesWhatItNeedToDo(player);
		assertThat(prison.getPlayers().size(), is(0));
	}
}
