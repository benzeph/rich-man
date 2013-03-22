package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.map.FirstMap;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.output.SystemOut;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartGame {

	public static final String NUMBER_PATTERN = "\\d\\d+";
	public static final int MAX_PLAYER_NUM = 4;
	private Rich rich;
	private Map map = new FirstMap();
	private Player[] players;
	private Scanner scanner = new Scanner(System.in);

	public void start() {
		int money = setPlayerInitialMoney();
		initialPlayer(money);
		rich = new Rich(players, map);
		rich.run();
	}

	private void initialPlayer(int money) {
		String inputLine;
		PlayerFactory playerFactory = new PlayerFactoryImp();
		boolean isDuplicate = true;
		while (isDuplicate) {
			List<Character> list = new ArrayList<Character>();
			SystemOut.pleaseChoosePlayers();
			inputLine = scanner.nextLine();
			if (isNumber(inputLine) && inputLine.length() <= MAX_PLAYER_NUM) {
				players = new Player[inputLine.length()];
				for (int i = 0; i < inputLine.length(); i++) {
					if (!list.contains(inputLine.charAt(i)) && Integer.valueOf(inputLine.charAt(i)) - 48 <= MAX_PLAYER_NUM) {
						createPlayer(money, inputLine, playerFactory, i);
						list.add(inputLine.charAt(i));
						isDuplicate = false;
					} else {
						isDuplicate = true;
						break;
					}
				}
			}
		}
	}

	private void createPlayer(int money, String inputLine, PlayerFactory playerFactory, int i) {
		players[Integer.valueOf(inputLine.charAt(i)) - 49] = playerFactory.createPlayer(Integer.valueOf(inputLine.charAt(i)) - 48, money);
	}

	private int setPlayerInitialMoney() {
		String inputLine;
		int money = 10000;
		while (true) {
			SystemOut.pleaseSetInitialMoney();
			inputLine = scanner.nextLine();
			if (inputLine.equals("")) {
				break;
			}
			if (isNumber(inputLine)) {
				if (isBetween1000and50000(inputLine)) {
					money = Integer.valueOf(inputLine);
					break;
				}
			}
		}
		return money;
	}

	private boolean isNumber(String inputLine) {
		Matcher matcher = Pattern.compile(NUMBER_PATTERN).matcher(inputLine);
		return matcher.matches();
	}

	private boolean isBetween1000and50000(String inputLine) {
		return (Integer.valueOf(inputLine) >= 1000) && (Integer.valueOf(inputLine) <= 50000);
	}

	public static void main(String[] args) {
		StartGame startGame = new StartGame();
		startGame.start();
	}
}
