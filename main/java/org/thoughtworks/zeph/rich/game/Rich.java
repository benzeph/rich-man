package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.input.InputSystem;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.syntax.SyntaxParserFactory;

public class Rich {

	private int currentPlayerNum;
	private int totalPlayerNum;
	private Player[] players;
	private Map map;
	private SyntaxParserFactory parserFactory = new SyntaxParserFactory();

	public Rich(Player[] players, Map map) {
		this.players = players;
		totalPlayerNum = players.length;
		currentPlayerNum = players.length;
		this.map = map;
	}

	public void run(String instructions) {
		InputSystem input = new InputSystem(instructions);
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
						instruction = input.getInput();
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
