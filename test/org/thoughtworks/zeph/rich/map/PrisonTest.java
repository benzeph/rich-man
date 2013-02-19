package org.thoughtworks.zeph.rich.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.role.Role;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PrisonTest {

	private Role role;
	private Prison prison;

	@Before
	public void setUp() {

		role = new Role("Qian Furen",1);
		prison = new Prison(2);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_1_when_a_role_hit_by_bomb() {
		prison.addRole(role);
		Set<Role> roles = prison.getRoles();
		assertThat(roles.size(),is(1));
	}

	@Test
	public void should_return_2_when_one_round_finish(){
		role.setPrisonDays(3);
		role.countDownPrisonDays();
		assertThat(role.getPrisonDays(),is(2));
	}
}
