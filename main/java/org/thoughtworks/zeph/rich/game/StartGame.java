package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.player.Player;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartGame {

	private Game game;
	private Scanner scanner;
	private Player[] players;

	public StartGame(String instruction) {
		scanner = new Scanner(instruction);
	}

	public StartGame(InputStream inputStream) {
		scanner = new Scanner(inputStream);
	}

	public void startForTest() {
		System.out.println("input \"rich\" to start the game:");
		String inputLine = scanner.nextLine();
		while (!inputLine.equals("rich")) {
			System.out.println("input \"rich\" to startForTest game");
			inputLine = scanner.nextLine();
		}
		Pattern pattern = Pattern.compile("\\d\\d+");
		boolean isNotBetween1000To50000 = true;
		int money = 10000;
		while (isNotBetween1000To50000) {
			System.out.println("set the initial money(1000~50000):");
			inputLine = scanner.nextLine();
			if (inputLine.equals(""))
				break;
			Matcher matcher = pattern.matcher(inputLine);
			if (inputLine.equals(null)) {
				break;
			}
			if (matcher.matches()) {
				if ((Integer.valueOf(inputLine) >= 1000) && (Integer.valueOf(inputLine) <= 50000)) {
					money = Integer.valueOf(inputLine);
					isNotBetween1000To50000 = false;
				}
			}
		}
		boolean isNotBetween1234 = true;
		while (isNotBetween1234) {
			System.out.println("please choose 2~4 players , input num(1.Qian Furen;2.A Tubo;3.Sun Xiaomei;4.Jin Beibei):");
			inputLine = scanner.nextLine();
			Matcher matcher = pattern.matcher(inputLine);
			if (matcher.matches()) {
				players = new Player[inputLine.length()];
				boolean isQianFuRenChoose = false;
				boolean isATuBoChoose = false;
				boolean isSunXiaoMeiChoose = false;
				boolean isJinBeibeiChoose = false;
				boolean isDuplicate = false;
				for (int i = 0; i < inputLine.length(); i++) {
					switch (inputLine.charAt(i)) {
						case '1':
							if (!isQianFuRenChoose) {
								players[i] = new Player("Qian Furen", 1, 5);
								players[i].setMoney(money);
								isQianFuRenChoose = true;
							} else {
								isDuplicate = true;
							}
							break;
						case '2':
							if (!isATuBoChoose) {
								players[i] = new Player("A Tubo", 2, 6);
								players[i].setMoney(money);
							} else {
								isDuplicate = true;
							}
							break;
						case '3':
							if (!isSunXiaoMeiChoose) {
								players[i] = new Player("Sun Xiaomei", 3, 4);
								players[i].setMoney(money);
							} else {
								isDuplicate = true;
							}
							break;
						case '4':
							if (!isJinBeibeiChoose) {
								players[i] = new Player("Jin Beibei", 4, 2);
								players[i].setMoney(money);
							} else {
								isDuplicate = true;
							}
							break;
						default:
							isDuplicate = true;
					}
				}
				if (!isDuplicate) {
					isNotBetween1234 = false;
				}
			}
		}
		game = new RichGame(players);
		game.runForTest(scanner.next());
	}

	public void start() {
		System.out.println("input \"rich\" to start the game:");
		String inputLine = scanner.nextLine();
		while (!inputLine.equals("rich")) {
			System.out.println("input \"rich\" to startForTest game");
			inputLine = scanner.nextLine();
		}
		Pattern pattern = Pattern.compile("\\d\\d+");
		boolean isNotBetween1000To50000 = true;
		int money = 10000;
		while (isNotBetween1000To50000) {
			System.out.println("set the initial money(1000~50000):");
			inputLine = scanner.nextLine();
			if (inputLine.equals(""))
				break;
			Matcher matcher = pattern.matcher(inputLine);
			if (inputLine.equals(null)) {
				break;
			}
			if (matcher.matches()) {
				if ((Integer.valueOf(inputLine) >= 1000) && (Integer.valueOf(inputLine) <= 50000)) {
					money = Integer.valueOf(inputLine);
					isNotBetween1000To50000 = false;
				}
			}
		}
		boolean isNotBetween1234 = true;
		while (isNotBetween1234) {
			System.out.println("please choose 2~4 players , input num(1.Qian Furen;2.A Tubo;3.Sun Xiaomei;4.Jin Beibei):");
			inputLine = scanner.nextLine();
			Matcher matcher = pattern.matcher(inputLine);
			if (matcher.matches()) {
				players = new Player[inputLine.length()];
				boolean isQianFuRenChoose = false;
				boolean isATuBoChoose = false;
				boolean isSunXiaoMeiChoose = false;
				boolean isJinBeibeiChoose = false;
				boolean isDuplicate = false;
				for (int i = 0; i < inputLine.length(); i++) {
					switch (inputLine.charAt(i)) {
						case '1':
							if (!isQianFuRenChoose) {
								players[i] = new Player("Qian Furen", 1, 5);
								players[i].setMoney(money);
								isQianFuRenChoose = true;
							} else {
								isDuplicate = true;
							}
							break;
						case '2':
							if (!isATuBoChoose) {
								players[i] = new Player("A Tubo", 2, 6);
								players[i].setMoney(money);
							} else {
								isDuplicate = true;
							}
							break;
						case '3':
							if (!isSunXiaoMeiChoose) {
								players[i] = new Player("Sun Xiaomei", 3, 4);
								players[i].setMoney(money);
							} else {
								isDuplicate = true;
							}
							break;
						case '4':
							if (!isJinBeibeiChoose) {
								players[i] = new Player("Jin Beibei", 4, 2);
								players[i].setMoney(money);
							} else {
								isDuplicate = true;
							}
							break;
						default:
							isDuplicate = true;
					}
				}
				if (!isDuplicate) {
					isNotBetween1234 = false;
				}
			}
		}
		game = new RichGame(players);
		game.run();
	}

	public static void main(String[] args) {

		StartGame startGame = new StartGame(System.in);
		startGame.start();
	}
}
