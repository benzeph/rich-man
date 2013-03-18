package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.output.Color;
import org.thoughtworks.zeph.rich.player.Player;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartGame {

	public static final String MONEY_PATTERN = "\\d\\d+";
	private Game game;
	private Scanner scanner;
	private Player[] players;

	public StartGame(InputStream inputStream) {
		scanner = new Scanner(inputStream);
	}

	public void start() {
		int money = setPlayerInitialMoney();
		boolean isNotBetween1234 = true;
		initialPlayer(money, isNotBetween1234);
		game = new RichGame(players);
		game.run();
	}

	private void initialPlayer(int money, boolean notBetween1234) {
		String inputLine;
		while (notBetween1234) {
			System.out.println("please choose 2~4 players , input num(1.Qian Furen;2.A Tubo;3.Sun Xiaomei;4.Jin Beibei):");
			inputLine = scanner.nextLine();
			Matcher matcher = Pattern.compile(MONEY_PATTERN).matcher(inputLine);
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
							if (isQianFuRenChoose) {
								isDuplicate = true;
							} else {
								players[i] = new Player("Qian Furen", 1, Color.PURPLE);
								players[i].setMoney(money);
								isQianFuRenChoose = true;
							}
							break;
						case '2':
							if (isATuBoChoose) {
								isDuplicate = true;
							} else {
								players[i] = new Player("A Tubo", 2, Color.YELLOW);
								players[i].setMoney(money);
								isATuBoChoose = true;
							}
							break;
						case '3':
							if (isSunXiaoMeiChoose) {
								isDuplicate = true;
							} else {
								players[i] = new Player("Sun Xiaomei", 3, Color.RED);
								players[i].setMoney(money);
								isSunXiaoMeiChoose = true;
							}
							break;
						case '4':
							if (isJinBeibeiChoose) {
								isDuplicate = true;
							} else {
								players[i] = new Player("Jin Beibei", 4, Color.GREEN);
								players[i].setMoney(money);
								isJinBeibeiChoose = true;
							}
							break;
						default:
							isDuplicate = true;
					}
				}
				if (!isDuplicate) {
					notBetween1234 = false;
				}
			}
		}
	}

	private int setPlayerInitialMoney() {
		String inputLine;
		boolean isNotBetween1000To50000 = true;
		int money = 10000;
		while (isNotBetween1000To50000) {
			System.out.println("set the initial money(1000~50000):");
			inputLine = scanner.nextLine();
			if (inputLine.equals(""))
				break;
			Matcher matcher = Pattern.compile(MONEY_PATTERN).matcher(inputLine);

			if (matcher.matches()) {
				if ((Integer.valueOf(inputLine) >= 1000) && (Integer.valueOf(inputLine) <= 50000)) {
					money = Integer.valueOf(inputLine);
					isNotBetween1000To50000 = false;
				}
			}
		}
		return money;
	}

	public static void main(String[] args) {
		StartGame startGame = new StartGame(System.in);
		startGame.start();
	}
}
