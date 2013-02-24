package org.thoughtworks.zeph.rich.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InputTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_roll_block_bomb_when_input_is_roll_block_bomb() {
		InputSystem input = new InputSystem("roll\nblock 1\nbomb 1");
		String roll = input.getInput();
		assertThat(roll, is("roll"));
		String block = input.getInput();
		assertThat(block, is("block 1"));
		String bomb = input.getInput();
		assertThat(bomb, is("bomb 1"));
	}
}
