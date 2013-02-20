package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PrisonTest {

	private Player player;
	private Prison prison;

	@Before
	public void setUp() {
		player = new Player("Qian Furen",1);
		prison = new Prison(2);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_1_when_a_role_hit_by_bomb() {
		prison.addRole(player);
		Set<Player> players = prison.getPlayers();
		assertThat(players.size(),is(1));
	}

	@Test
	public void should_return_2_when_one_round_finish(){
		player.setPrisonDays(3);
		player.countDownPrisonDays();
		assertThat(player.getPrisonDays(),is(2));
	}
}
