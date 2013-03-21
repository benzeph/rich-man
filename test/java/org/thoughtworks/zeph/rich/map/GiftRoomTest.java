package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.gift.GamePointGift;
import org.thoughtworks.zeph.rich.gift.Gift;
import org.thoughtworks.zeph.rich.gift.MoneyGift;
import org.thoughtworks.zeph.rich.map.unit.GiftRoom;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GiftRoomTest {

	private GiftRoom room;
	private Player player;

	@Before
	public void setUp() {

		room = new GiftRoom(3, 'G');
		player = new PlayerFactoryImp().createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_12000_when_get_gift_of_money() {
		Gift gift = new MoneyGift();
		room.getGift(player, gift);
		assertThat(player.getMoney(), is(12000));
	}

	@Test
	public void should_return_200_when_get_gift_of_game_point() {
		Gift gift = new GamePointGift();
		room.getGift(player, gift);
		assertThat(player.getGamePoint(), is(200));
	}

}
