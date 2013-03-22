package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.output.SystemOut;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.syntax.SyntaxParserFactory;

import java.util.Scanner;

public class Rich {

	public static final String ROLL = "roll";
	public static final int PLAY_ARRAY_STEP = 1;
	public static final String WIN = " win";
	public static final String WHAT_EVER = "what ever";
	public static final int MINIMUM_PLAYER_NUMBER = 1;
	public static final int FIRST_PLAYER_NUMBER = 0;
	private int currentPlayerNum;
	private int totalPlayerNum;
	private Player[] players;
	private Map map;
	private SyntaxParserFactory parserFactory = new SyntaxParserFactory();
	private Scanner scanner = new Scanner(System.in);

	public Rich(Player[] players, Map map) {
		this.players = players;
		this.map = map;
		totalPlayerNum = players.length;
		currentPlayerNum = players.length;
	}

	public void run() {
		int currentPlayer = FIRST_PLAYER_NUMBER;
		while (currentPlayerNum > MINIMUM_PLAYER_NUMBER) {
			if (players[currentPlayer] != null) {
				if (players[currentPlayer].isInHospital()) {
					players[currentPlayer].countDownHospitalDays();
				} else if (players[currentPlayer].isInPrison()) {
					players[currentPlayer].countDownPrisonDays();
				} else {
					if (players[currentPlayer].isGodExist()) {
						players[currentPlayer].getGod().timeCountDown();
					}
					String instruction = WHAT_EVER;
					while (!instruction.equals(ROLL)) {
						SystemOut.waitForInstruction(players[currentPlayer].getName());
						instruction = scanner.nextLine();
						parserFactory.buildSyntaxParser(instruction, map, players[currentPlayer]).parse().execute();
					}
					map.getGrid(players[currentPlayer].getCurrentMapPosition()).doesWhatItNeedToDo(players[currentPlayer]);
				}
				map.drawMap();
				currentPlayer = (currentPlayer + PLAY_ARRAY_STEP) % totalPlayerNum;
			} else {
				currentPlayer = (currentPlayer + PLAY_ARRAY_STEP) % totalPlayerNum;
			}
		}
		for (Player player : players) {
			if (null != player) {
				System.out.println(player.getName() + WIN);
			}
		}
	}
}
