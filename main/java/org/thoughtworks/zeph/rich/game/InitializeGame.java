package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.output.Printer;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.player.PlayerFactory;
import org.thoughtworks.zeph.rich.player.PlayerFactoryImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitializeGame {

	public static final String NUMBER_PATTERN = "\\d\\d+";
	public static final int MAX_PLAYER_NUM = 4;
	private static Player[] players = new Player[MAX_PLAYER_NUM];
	private static Scanner scanner = new Scanner(System.in);
	private static int money;

	public static Player[] initialize() {
		initialMoney();
		initialPlayer();
		return players;
	}

	private static void initialMoney() {
		String inputLine;
		money = 10000;
		while (true) {
			Printer.pleaseSetInitialMoney();
			inputLine = scanner.nextLine();
			if (inputLine.equals("")) {
				break;
			}
			if (isNumber(inputLine) && isBetween1000and50000(inputLine)) {
				money = Integer.valueOf(inputLine);
				break;
			}
		}
	}

	private static void initialPlayer() {
		String inputLine;
		PlayerFactory playerFactory = new PlayerFactoryImp();
		boolean isDuplicate = true;
		while (isDuplicate) {
			List<Character> list = new ArrayList<Character>();
			Printer.pleaseChoosePlayers();
			inputLine = scanner.nextLine();
			if (isNumber(inputLine) && inputLine.length() <= MAX_PLAYER_NUM) {
				for (int i = 0; i < inputLine.length(); i++) {
					if (!list.contains(inputLine.charAt(i)) && charToInt(inputLine.charAt(i)) <= MAX_PLAYER_NUM) {
						createPlayer(inputLine, playerFactory, i);
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

	private static int charToInt(char ch) {
		return (int) ch - 48;
	}

	private static void createPlayer(String inputLine, PlayerFactory playerFactory, int i) {
		players[Integer.valueOf(inputLine.charAt(i)) - 49] = playerFactory.createPlayer(charToInt(inputLine.charAt(i)), money);
	}

	private static boolean isNumber(String inputLine) {
		Matcher matcher = Pattern.compile(NUMBER_PATTERN).matcher(inputLine);
		return matcher.matches();
	}

	private static boolean isBetween1000and50000(String inputLine) {
		return (Integer.valueOf(inputLine) >= 1000) && (Integer.valueOf(inputLine) <= 50000);
	}
}
