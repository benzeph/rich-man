package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

public class PropRoomTest {

	private Player qianFuRen;

	@Before
	public void setUp() {

		qianFuRen = new PlayerFactoryImp().createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}


}
