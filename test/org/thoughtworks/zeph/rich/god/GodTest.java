package org.thoughtworks.zeph.rich.god;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GodTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_4_when_role_go_1_round() {
		God god = new WealthGod();
		god.timeCountDown();
		assertThat(god.getLeftTime(), is(4));
	}
}
