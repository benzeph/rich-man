package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.input.InputSystem;
import org.thoughtworks.zeph.rich.interpreter.Interpreter;
import org.thoughtworks.zeph.rich.map.*;
import org.thoughtworks.zeph.rich.player.Player;

public class RichGame extends Game {
	private int currentPlayerNum;
	private int totalPlayerNum;
	private Player[] players;
	private Interpreter interpreter;

	public RichGame(Player[] players) {
		this.players = players;
		totalPlayerNum = players.length;
		currentPlayerNum = players.length;
		interpreter = new Interpreter();
		gameMap = new Map[70];
		gameMap[0] = new Map(0);

		for (int i = 1; i <= 13; i++) {
			gameMap[i] = new BuildingLotOneTwo(i);
		}

		gameMap[14] = new Hospital(14);

		for (int i = 15; i <= 27; i++) {
			gameMap[i] = new BuildingLotOneTwo(i);
		}

		gameMap[28] = new PropRoom(28);

		for (int i = 29; i <= 34; i++) {
			gameMap[i] = new BuildingLotThree(i);
		}

		gameMap[35] = new GiftRoom(35);

		for (int i = 36; i <= 48; i++) {
			gameMap[i] = new BuildingLotFourFive(i);
		}

		gameMap[49] = new Prison(49);

		for (int i = 50; i <= 62; i++) {
			gameMap[i] = new BuildingLotFourFive(i);
		}

		gameMap[61] = new MagicRoom(63);

		gameMap[64] = new Mine(64, 20);
		gameMap[65] = new Mine(65, 80);
		gameMap[66] = new Mine(66, 100);
		gameMap[67] = new Mine(67, 40);
		gameMap[68] = new Mine(68, 80);
		gameMap[69] = new Mine(69, 60);
	}

	public void run() {
		InputSystem input = new InputSystem(System.in);
		int currentPlayer = 0;
		while (currentPlayerNum != 1) {
			if (players[currentPlayer] != null) {

			} else {
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			}
		}
	}

	@Override
	public void runForTest(String instruction) {
		InputSystem input = new InputSystem(instruction);
		int currentPlayer = 0;
		while (currentPlayerNum != 1) {
			if (players[currentPlayer] != null) {
				String order = interpreter.interpret(input.getInput(), gameMap, players[currentPlayer]);
				System.out.println(order);
				if (order.equals("quit")) {
					break;
				}
				if (gameMap[players[currentPlayer].getCurrentMapPosition()] instanceof BuildingLotOneTwo) {
					if (((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getBelongTo() == 0) {
						System.out.println("Do you want to buy this land," + ((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getPrice() + "(Y/N)?");
						String str = input.getInput();
						if (str.equals("Y") || str.equals("y")) {
							if (players[currentPlayer].buyLand((Land) gameMap[players[currentPlayer].getCurrentMapPosition()])) {
								System.out.println("buy land success");
							} else {
								System.out.println("buy land fail");
							}
						}else{
							System.out.println("give up buy");
						}
					} else if (((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getBelongTo() == players[currentPlayer].getId()) {
						System.out.println("Do you want to level up this land," + ((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getPrice() + "(Y/N)?");
						String str = input.getInput();
						if (str.equals("Y") || str.equals("y")) {
							if (players[currentPlayer].upgradeLand((Land) gameMap[players[currentPlayer].getCurrentMapPosition()])) {
								System.out.println("level up land success");
							} else {
								System.out.println("level up land fail");
							}
						}else{
							System.out.println("give up level up");
						}
					}
				} else if (gameMap[players[currentPlayer].getCurrentMapPosition()] instanceof BuildingLotThree) {
					if (((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getBelongTo() == 0) {
						System.out.println("Do you want to buy this land," + ((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getPrice() + "(Y/N)?");
						String str = input.getInput();
						if (str.equals("Y") || str.equals("y")) {
							if (players[currentPlayer].buyLand((Land) gameMap[players[currentPlayer].getCurrentMapPosition()])) {
								System.out.println("buy land success");
							} else {
								System.out.println("buy land fail");
							}
						}else {
							System.out.println("give up buy");
						}
					} else if (((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getBelongTo() == players[currentPlayer].getId()) {
						System.out.println("Do you want to level up this land," + ((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getPrice() + "(Y/N)?");
						String str = input.getInput();
						if (str.equals("Y") || str.equals("y")) {
							if (players[currentPlayer].upgradeLand((Land) gameMap[players[currentPlayer].getCurrentMapPosition()])) {
								System.out.println("level up land success");
							} else {
								System.out.println("level up land fail");
							}
						}else {
							System.out.println("give up level up");
						}
					}
				} else if (gameMap[players[currentPlayer].getCurrentMapPosition()] instanceof BuildingLotFourFive) {
					if (((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getBelongTo() == 0) {
						System.out.println("Do you want to buy this land," + ((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getPrice() + "(Y/N)?");
						String str = input.getInput();
						if (str.equals("Y") || str.equals("y")) {
							if (players[currentPlayer].buyLand((Land) gameMap[players[currentPlayer].getCurrentMapPosition()])) {
								System.out.println("buy land success");
							} else {
								System.out.println("buy land fail");
							}
						}else{
							System.out.println("give up buy");
						}
					} else if (((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getBelongTo() == players[currentPlayer].getId()) {
						System.out.println("Do you want to level up this land," + ((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getPrice() + "(Y/N)?");
						String str = input.getInput();
						if (str.equals("Y") || str.equals("y")) {
							if (players[currentPlayer].upgradeLand((Land) gameMap[players[currentPlayer].getCurrentMapPosition()])) {
								System.out.println("level up land success");
							} else {
								System.out.println("level up land fail");
							}
						}else {
							System.out.println("give up level up");
						}
					}
				}
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			} else {
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			}
		}
	}
}
