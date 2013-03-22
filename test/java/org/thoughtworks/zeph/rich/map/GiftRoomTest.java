package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.unit.GiftRoom;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GiftRoomTest {

	private GiftRoom giftRoom;
	private Player player;

	@Before
	public void setUp() {
		player = new PlayerFactoryImp().createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_12000_when_input_is_1() {
		giftRoom = new GiftRoom(30, 'G', "1");
		giftRoom.doesWhatItNeedToDo(player);
		assertThat(player.getMoney(), is(12000));
	}

	@Test
	public void should_return_200_when_input_is_2() {
		giftRoom = new GiftRoom(30, 'G', "2");
		giftRoom.doesWhatItNeedToDo(player);
		assertThat(player.getGamePoint(), is(200));
	}

	@Test
	public void should_return_a_god_when_input_is_3() {
		giftRoom = new GiftRoom(30, 'G', "3");
		giftRoom.doesWhatItNeedToDo(player);
		assertTrue(player.isGodExist());
	}
}
