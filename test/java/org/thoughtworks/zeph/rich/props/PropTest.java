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
	public void should_return_2_when_bomb_time_count_down() {
		Prop bomb = new Bomb();
		((Bomb) bomb).timeCountDown();
		assertThat(((Bomb) bomb).getLeftTime(), is(2));
	}
}
