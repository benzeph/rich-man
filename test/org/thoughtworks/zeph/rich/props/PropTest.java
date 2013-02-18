package org.thoughtworks.zeph.rich.props;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PropTest {
	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_16_when_bomb_left_time_is_20_and_role_walks_4_step_ahead() {
		Prop bomb = new Bomb();
		assertThat(bomb.timeCountDown(4), is(16));
	}
}
