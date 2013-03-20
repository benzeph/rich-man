package org.thoughtworks.zeph.rich.syntax;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.zeph.rich.map.Grid;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SyntaxTest {

	private SyntaxParserFactory parserFactory = new SyntaxParserFactory();
	private PlayerFactory playerFactory = new PlayerFactoryImp();
	private Player player;
	private String instruction;
	private Grid[] map;

	@Before
	public void setUp() {
		player = playerFactory.createPlayer(1, 10000);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void should_return_a_block_syntax_parser_when_input_is_block_5() {
		instruction = "block 5";
		SyntaxParser expectParser = new BlockSyntaxParser(instruction, map, player);
		assertThat(expectParser,is(parserFactory.buildSyntaxParser(instruction, map, player)));
	}
}
