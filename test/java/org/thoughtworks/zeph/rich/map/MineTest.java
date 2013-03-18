package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MineTest {

	private Player qianFuRen;

	@Before
	public void setUp() {

		qianFuRen = new PlayerFactoryImp().createPlayer(1,10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_80_when_player_stand_on_mine_80() {
		Mine mine = new Mine(2, 80, '$');
		mine.addGamePoint(qianFuRen);
		assertThat(qianFuRen.getGamePoint(), is(80));
	}
}
