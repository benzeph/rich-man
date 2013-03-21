package org.thoughtworks.zeph.rich.game;

import org.junit.After;
import org.junit.Before;
import org.thoughtworks.zeph.rich.map.FirstMapFactory;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.map.MapFactory;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

public class GameTest {

	private Player qianFuRen;
	private Player aTuBo;
	private Rich game;
	private MapFactory mapFactory = new FirstMapFactory();
	private Map map = mapFactory.createMap();
	private PlayerFactory playerFactory = new PlayerFactoryImp();

	@Before
	public void setUp() {
		qianFuRen = playerFactory.createPlayer(1, 10000);
		aTuBo = playerFactory.createPlayer(2, 10000);
		game = new Rich(new Player[]{qianFuRen, aTuBo}, map);
	}

	@After
	public void tearDown() {

	}
}
