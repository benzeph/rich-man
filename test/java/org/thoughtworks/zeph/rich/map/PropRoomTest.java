package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PropRoomTest {
	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_5_when_role_buy_two_items() {
		PropRoom room = new PropRoom(4, 'T');
		Player player = new Player("Qian Furen", 1);
		player.addGamePoint(1000);
		Prop prop = new Bomb();
		room.sell(player, prop);
		Map<Integer, Integer> props = player.getProps();
		assertThat(props.size(), is(1));
	}
}
