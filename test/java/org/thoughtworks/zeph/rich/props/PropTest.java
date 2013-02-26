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
	public void should_return_0_when_bomb_left_time_is_1_and_role_walks_1_step_ahead() {
		Prop bomb = new Bomb();
		((Bomb) bomb).timeCountDown();
		assertThat(((Bomb) bomb).getLeftTime(), is(0));
	}
}
