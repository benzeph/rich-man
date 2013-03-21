package org.thoughtworks.zeph.rich.executor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.FirstMapFactory;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.map.MapFactory;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ExecutorTest {

	private Player player = new PlayerFactoryImp().createPlayer(1, 10000);
	private MapFactory mapFactory = new FirstMapFactory();
	private Map map;
	private Executor executor;

	@Before
	public void setUp() {
		map = mapFactory.createMap();
		player.addGamePoint(1000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void grid_5_should_has_a_block_when_parameter_n_is_5() {
		player.buyProp(new Block());
		int n = 5;
		executor = new BlockExecutor(map, player, n);
		executor.execute();
		Prop expectProp = new Block();
		assertThat(map.getGrid(5).getProp(), is(expectProp));

	}

	@Test
	public void grid_5_should_has_a_bomb_when_parameter_n_5() {
		player.buyProp(new Bomb());
		int n = 5;
		executor = new BombExecutor(map, player, n);
		executor.execute();
		Prop expectProp = new Bomb();
		assertThat(map.getGrid(5).getProp(), is(expectProp));
	}

	@Test
	public void should_not_stand_on_grid_0_when_execute_roll() {
		executor = new RollExecutor(map, player);
		executor.execute();
		assertThat(player.getCurrentMapPosition(), not(0));
	}

	@Test
	public void should_return_null_when_robot() {
		player.buyProp(new Block());
		int n = 5;
		executor = new BlockExecutor(map, player, n);
		executor.execute();
		executor = new RobotExecutor(map, player);
		executor.execute();
		assertNull(map.getGrid(5).getProp());
	}

	@Test
	public void should_return_10000_when_sell_5() {
		player.buyLand(map.getGrid(5));
		executor = new SellExecutor(map, player, 5);
		executor.execute();
		assertThat(player.getMoney(), is(10000));
	}

	@Test
	public void should_return_1000_when_sell_tool_1() {
		player.buyProp(new Block());
		executor = new SellToolExecutor(player, 1);
		executor.execute();
		assertThat(player.getGamePoint(), is(1000));
	}
}
