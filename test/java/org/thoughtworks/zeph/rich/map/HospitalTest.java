package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HospitalTest {
	private Player player;
	private Hospital hospital;
	private Player aTuBo;

	@Before
	public void setUp() {

		player = new PlayerFactoryImp().createPlayer(1,10000);
		hospital = new Hospital(1, 'H');
		aTuBo = new PlayerFactoryImp().createPlayer(2,10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_1_when_a_role_hit_by_bomb() {
		hospital.addRole(player);
		Set<Player> players = hospital.getPlayers();
		assertThat(players.size(), is(1));
	}

	@Test
	public void should_return_2_when_one_round_finish() {
		player.setHospitalDays(3);
		player.countDownHospitalDays();
		assertThat(player.getHospitalDays(), is(2));
	}

	@Test
	public void should_return_0_when_prison_release_all_prisoner() {
		hospital.addRole(player);
		hospital.addRole(aTuBo);
		hospital.release();
		assertThat(hospital.getPlayers().size(), is(0));
	}
}
