package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.syntax.SyntaxParserFactory;

import java.util.Scanner;

public class Rich {

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

	public Rich(Player[] players, Map map, String instructions) {
		this.players = players;
		this.map = map;
		totalPlayerNum = players.length;
		currentPlayerNum = players.length;
		scanner = new Scanner(instructions);
	}

	public void run() {
		int currentPlayer = 0;
		while (currentPlayerNum > 1) {
			if (players[currentPlayer] != null) {
				if (players[currentPlayer].isInHospital()) {
					players[currentPlayer].countDownHospitalDays();
				} else if (players[currentPlayer].isInPrison()) {
					players[currentPlayer].countDownPrisonDays();
				} else {
					if (players[currentPlayer].isGodExist()) {
						players[currentPlayer].getGod().timeCountDown();
					}
					String instruction = "";
					while (!instruction.equals("roll")) {
						instruction = scanner.nextLine();
						parserFactory.buildSyntaxParser(instruction, map, players[currentPlayer]).parse().execute();
						map.getGrid(players[currentPlayer].getCurrentMapPosition()).doesWhatItNeedToDo(players[currentPlayer]);
					}
				}
				map.drawMap();
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			} else {
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			}
		}
		for (Player player : players) {
			if (null != player) {
				System.out.println(player.getName() + " win");
			}
		}
	}
}
