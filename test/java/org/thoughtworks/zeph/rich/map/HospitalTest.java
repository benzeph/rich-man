package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.Hospital;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HospitalTest {
	private Player player;
	private Hospital hospital;
	private PlayerFactory playerFactory = new PlayerFactoryImp();

	@Before
	public void setUp() {
		player = playerFactory.createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_0_when_a_player_stay_in_hospital() {
		hospital = new Hospital(1, 'H');
		Player anotherPlayer = playerFactory.createPlayer(2, 10000);
		hospital.addPlayer(anotherPlayer);
		hospital.doesWhatItNeedToDo(player);
		assertThat(hospital.getPlayers().size(), is(0));
	}
}
