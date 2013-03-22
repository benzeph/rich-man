package org.thoughtworks.zeph.rich.syntax;

import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxParserFactory {

	private static final String ROLL_PATTEN = "roll";
	private static final String QUERY_PATTEN = "query";
	private static final String QUIT_PATTEN = "quit";
	private static final String HELP_PATTEN = "help";
	private static final String ROBOT_PATTERN = "robot";
	private static final String BOMB_PATTEN = "bomb (-)?\\d*";
	private static final String BLOCK_PATTERN = "block (-)?\\d*";
	private static final String SELL_PATTERN = "sell \\d*";
	private static final String SELL_TOOL_PATTERN = "sellTool \\d";
	private Pattern pattern;
	private Matcher matcher;

	public SyntaxParser buildSyntaxParser(String instruction, Map map, Player player) {

		if (isRoll(instruction)) {
			return new RollSyntaxParser(instruction, map, player);
		}
		if (isRobot(instruction)) {
			return new RobotSyntaxParser(instruction, map, player);
		}
		if (isBomb(instruction)) {
			return new BombSyntaxParser(instruction, map, player);
		}
		if (isBlock(instruction)) {
			return new BlockSyntaxParser(instruction, map, player);
		}
		if (isSell(instruction)) {
			return new SellSyntaxParser(instruction, map, player);
		}
		if (isSellTool(instruction)) {
			return new SellToolSyntaxParser(instruction, player);
		}
		if (isQuery(instruction)) {
			return new QuerySyntaxParser(player);
		}
		if (isHelp(instruction)) {
			return new HelpSyntaxParser();
		}
		if (isQuit(instruction)) {
			return new QuitSyntaxParser();
		}
		return new SyntaxParserFailed();
	}

	private boolean isRobot(String instruction) {
		pattern = Pattern.compile(ROBOT_PATTERN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}

	private boolean isSellTool(String instruction) {
		pattern = Pattern.compile(SELL_TOOL_PATTERN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}

	private boolean isSell(String instruction) {
		pattern = Pattern.compile(SELL_PATTERN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}

	private boolean isBlock(String instruction) {
		pattern = Pattern.compile(BLOCK_PATTERN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}

	private boolean isBomb(String instruction) {
		pattern = Pattern.compile(BOMB_PATTEN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}

	private boolean isHelp(String instruction) {
		pattern = Pattern.compile(HELP_PATTEN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}

	private boolean isQuit(String instruction) {
		pattern = Pattern.compile(QUIT_PATTEN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}

	private boolean isQuery(String instruction) {
		pattern = Pattern.compile(QUERY_PATTEN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}

	private boolean isRoll(String instruction) {
		pattern = Pattern.compile(ROLL_PATTEN);
		matcher = pattern.matcher(instruction);
		return matcher.matches();
	}
}
