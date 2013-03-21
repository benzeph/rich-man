package org.thoughtworks.zeph.rich.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

	private PlayerFactory playerFactory = new PlayerFactoryImp();
	private Player player = playerFactory.createPlayer(1, 10000);

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}


	@Test
	public void should_return_12000_when_role_get_a_gift_of_2000() {
		player.addMoney(2000);
		assertThat(player.getMoney(), is(12000));
	}

	@Test
	public void should_return_200_when_role_get_a_gift_of_200_game_point() {
		player.addGamePoint(200);
		assertThat(player.getGamePoint(), is(200));
	}

	@Ignore
	@Test
	public void should_return_1_when_mock_a_player_dice() {
		Player player = mock(Player.class);
		when(player.dice()).thenReturn(1);
		assertThat(player.dice(), is(1));
	}
}
