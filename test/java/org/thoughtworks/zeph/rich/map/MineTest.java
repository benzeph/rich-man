package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.player.Player;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MineTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_80_when_player_stand_on_mine_80() {
		Mine mine = new Mine(2,80);
		Player player = new Player("Qian Furen",1);
		mine.addGamePoint(player);
		assertThat(player.getGamePoint(),is(80));
	}
}
