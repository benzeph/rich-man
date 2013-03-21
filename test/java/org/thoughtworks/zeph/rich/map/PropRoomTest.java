package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.PropRoom;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PropRoomTest {

	private Player qianFuRen;

	@Before
	public void setUp() {

		qianFuRen = new PlayerFactoryImp().createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_5_when_role_buy_two_items() {
		PropRoom room = new PropRoom(4, 'T');
		qianFuRen.addGamePoint(1000);
		Prop prop = new Bomb();
		room.sell(qianFuRen, prop);
		Map<Integer, Integer> props = qianFuRen.getProps();
		assertThat(props.size(), is(1));
	}
}
