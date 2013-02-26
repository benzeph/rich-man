package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.gift.GamePointGift;
import org.thoughtworks.zeph.rich.gift.Gift;
import org.thoughtworks.zeph.rich.gift.GodGift;
import org.thoughtworks.zeph.rich.gift.MoneyGift;
import org.thoughtworks.zeph.rich.input.InputSystem;
import org.thoughtworks.zeph.rich.interpreter.Interpreter;
import org.thoughtworks.zeph.rich.map.*;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Robot;

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
		gameMap[0] = new Map(0, 'S');

		for (int i = 1; i <= 13; i++) {
			gameMap[i] = new BuildingLotOneTwo(i, '0');
		}

		gameMap[14] = new Hospital(14, 'H');

		for (int i = 15; i <= 27; i++) {
			gameMap[i] = new BuildingLotOneTwo(i, '0');
		}

		gameMap[28] = new PropRoom(28, 'T');

		for (int i = 29; i <= 34; i++) {
			gameMap[i] = new BuildingLotThree(i, '0');
		}

		gameMap[35] = new GiftRoom(35, 'G');

		for (int i = 36; i <= 48; i++) {
			gameMap[i] = new BuildingLotFourFive(i, '0');
		}

		gameMap[49] = new Prison(49, 'P');

		for (int i = 50; i <= 62; i++) {
			gameMap[i] = new BuildingLotFourFive(i, '0');
		}

		gameMap[63] = new MagicRoom(63, 'M');

		gameMap[64] = new Mine(64, 20, '$');
		gameMap[65] = new Mine(65, 80, '$');
		gameMap[66] = new Mine(66, 100, '$');
		gameMap[67] = new Mine(67, 40, '$');
		gameMap[68] = new Mine(68, 80, '$');
		gameMap[69] = new Mine(69, 60, '$');
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
		boolean notBreak = true;
		while (currentPlayerNum != 1 && notBreak) {
			if (players[currentPlayer] != null) {
				if (players[currentPlayer].getHospitalDays() > 0) {
					players[currentPlayer].setHospitalDays(players[currentPlayer].getHospitalDays() - 1);
					System.out.println(players[currentPlayer].getName() + ":stay in hospital,hospital left time:" + players[currentPlayer].getHospitalDays());
					currentPlayer = (currentPlayer + 1) % totalPlayerNum;
					continue;
				}
				if (players[currentPlayer].getPrisonDays() > 0) {
					players[currentPlayer].setPrisonDays(players[currentPlayer].getPrisonDays() - 1);
					currentPlayer = (currentPlayer + 1) % totalPlayerNum;
					continue;
				}

				if ((null != players[currentPlayer].getGod()) && (players[currentPlayer].getGod().getLeftTime() > 0)) {
					players[currentPlayer].getGod().timeCountDown();
					System.out.println(players[currentPlayer].getName() + ":" + players[currentPlayer].getGod().getLeftTime());
				}
				String inputStr = "";
				while (!inputStr.equals("roll") && !inputStr.equals("roll one")) {
					inputStr = input.getInput();
					String order = interpreter.interpret(inputStr, gameMap, players[currentPlayer]);
					System.out.println(players[currentPlayer].getName() + ":" + order);
					if (order.equals("quit")) {
						notBreak = false;
						break;
					}
					if (order.equals("drawMap")) {
						drawMap();
						break;
					}
					if (currentPlayerPosition(currentPlayer) instanceof BuildingLotOneTwo) {
						if (isLandBlank(currentPlayer)) {
							buyLand(input, currentPlayer);
						} else if (isLandBelongToPlayer(currentPlayer)) {
							levelUpLand(input, currentPlayer);
						} else {
							payLand(currentPlayer);
						}
					} else if (currentPlayerPosition(currentPlayer) instanceof BuildingLotThree) {
						if (isLandBlank(currentPlayer)) {
							buyLand(input, currentPlayer);
						} else if (isLandBelongToPlayer(currentPlayer)) {
							levelUpLand(input, currentPlayer);
						} else {
							payLand(currentPlayer);
						}
					} else if (currentPlayerPosition(currentPlayer) instanceof BuildingLotFourFive) {
						if (isLandBlank(currentPlayer)) {
							buyLand(input, currentPlayer);
						} else if (isLandBelongToPlayer(currentPlayer)) {
							levelUpLand(input, currentPlayer);
						} else {
							payLand(currentPlayer);
						}
					} else if (currentPlayerPosition(currentPlayer) instanceof Mine) {
						getMine(currentPlayer);
					} else if (currentPlayerPosition(currentPlayer) instanceof PropRoom) {
						getProp(input, currentPlayer);
					} else if (currentPlayerPosition(currentPlayer) instanceof GiftRoom) {
						getGift(input, currentPlayer);
					} else if (currentPlayerPosition(currentPlayer) instanceof Prison) {
						releasePrisoner(currentPlayer);
					} else if (currentPlayerPosition(currentPlayer) instanceof Hospital) {

					} else if (currentPlayerPosition(currentPlayer) instanceof MagicRoom) {

					}
				}
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			} else {
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			}
		}
	}

	private void getProp(InputSystem input, int currentPlayer) {
		System.out.println("Welcome to Prop Room");
		System.out.println("block 1 50");
		System.out.println("robot 2 30");
		System.out.println("bomb  3 50");
		if (players[currentPlayer].getGamePoint() < 30) {
			System.out.println("your game point is not enough");
		}
		while (players[currentPlayer].getGamePoint() >= 30) {
			System.out.println("you have " + players[currentPlayer].getGamePoint() + " game point left");
			if (players[currentPlayer].getProps().size() < 10) {
				String num = input.getInput();
				if (num.equals("1")) {
					((PropRoom) gameMap[players[currentPlayer].getCurrentMapPosition()]).sell(players[currentPlayer], new Block());
					System.out.println("you bought a block");
				} else if (num.equals("2")) {
					((PropRoom) gameMap[players[currentPlayer].getCurrentMapPosition()]).sell(players[currentPlayer], new Robot());
					System.out.println("you bought a robot");
				} else if (num.equals("3")) {
					((PropRoom) gameMap[players[currentPlayer].getCurrentMapPosition()]).sell(players[currentPlayer], new Bomb());
					System.out.println("you bought a bomb");
				} else if (num.equals("F")) {
					System.out.println("hope to see you again");
					break;
				}
			} else {
				System.out.println("your prop column is full");
				String str = input.getInput();
				if (str.equals("F")) {
					break;
				}
			}
		}
	}

	private void releasePrisoner(int currentPlayer) {
		((Prison) gameMap[players[currentPlayer].getCurrentMapPosition()]).release();
		System.out.println(players[currentPlayer].getName() + ":release all prisoners");
	}

	private void getGift(InputSystem input, int currentPlayer) {
		System.out.println("Welcome to Gift Room");
		System.out.println("Bond 1");
		System.out.println("Game Point 2");
		System.out.println("God 3");
		String str = input.getInput();
		if (str.equals("1")) {
			Gift bondGift = new MoneyGift();
			((GiftRoom) gameMap[players[currentPlayer].getCurrentMapPosition()]).getGift(players[currentPlayer], bondGift);
			System.out.println("add money:2000");
		} else if (str.equals("2")) {
			Gift gamePointGift = new GamePointGift();
			((GiftRoom) gameMap[players[currentPlayer].getCurrentMapPosition()]).getGift(players[currentPlayer], gamePointGift);
			System.out.println("add game point:200");
		} else if (str.equals("3")) {
			Gift godGift = new GodGift();
			((GiftRoom) gameMap[players[currentPlayer].getCurrentMapPosition()]).getGift(players[currentPlayer], godGift);
			System.out.println("possessed with god");
		}
	}

	private void getMine(int currentPlayer) {
		((Mine) gameMap[players[currentPlayer].getCurrentMapPosition()]).addGamePoint(players[currentPlayer]);
		System.out.println(players[currentPlayer].getName() + ":you get " + ((Mine) gameMap[players[currentPlayer].getCurrentMapPosition()]).getGamePoint());
	}

	private void payLand(int currentPlayer) {
		if ((null != players[currentPlayer].getGod()) && (players[currentPlayer].getGod().getLeftTime() > 0)) {
			System.out.println(players[currentPlayer].getName() + ":wealthGod bless , exempt from payment");
			return;
		}
		if (players[currentPlayer].payRent((Land) gameMap[players[currentPlayer].getCurrentMapPosition()], players[((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getBelongTo() - 1])) {
			System.out.println(players[currentPlayer].getName() + ":pay " + ((Land) gameMap[players[currentPlayer].getCurrentMapPosition()]).getCost());
		} else {
			System.out.println(players[currentPlayer].getName() + " broke");
			players[currentPlayer] = null;
		}
	}

	private boolean isLandBelongToPlayer(int currentPlayer) {
		return ((Land) currentPlayerPosition(currentPlayer)).getBelongTo() == players[currentPlayer].getId();
	}

	private boolean isLandBlank(int currentPlayer) {
		return ((Land) currentPlayerPosition(currentPlayer)).getBelongTo() == 0;
	}

	private Map currentPlayerPosition(int currentPlayer) {
		return gameMap[players[currentPlayer].getCurrentMapPosition()];
	}

	private void levelUpLand(InputSystem input, int currentPlayer) {
		System.out.println(players[currentPlayer].getName() + ":Do you want to level up this land," + ((Land) currentPlayerPosition(currentPlayer)).getPrice() + "(Y/N)?");
		String str = input.getInput();
		if (str.equals("Y") || str.equals("y")) {
			if (players[currentPlayer].upgradeLand((Land) currentPlayerPosition(currentPlayer))) {
				System.out.println(players[currentPlayer].getName() + ":level up land success");
			} else {
				System.out.println(players[currentPlayer].getName() + ":level up land fail");
			}
		} else {
			System.out.println(players[currentPlayer].getName() + ":give up level up");
		}
	}

	private void buyLand(InputSystem input, int currentPlayer) {
		System.out.println(players[currentPlayer].getName() + ":Do you want to buy this land," + ((Land) currentPlayerPosition(currentPlayer)).getPrice() + "(Y/N)?");
		String str = input.getInput();
		if (str.equals("Y") || str.equals("y")) {
			if (players[currentPlayer].buyLand((Land) currentPlayerPosition(currentPlayer))) {
				System.out.println(players[currentPlayer].getName() + ":buy land success");
			} else {
				System.out.println(players[currentPlayer].getName() + ":buy land fail");
			}
		} else {
			System.out.println(players[currentPlayer].getName() + ":give up buy");
		}
	}

	@Override
	public void drawMap() {
		for (int i = 0; i < 29; i++) {
			System.out.print(gameMap[i].getSymbol());
		}
		System.out.println();
		///////////////////////////////////
		System.out.print(gameMap[69].getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		System.out.print(gameMap[29].getSymbol());
		System.out.println();
		///////////////////////////////////
		System.out.print(gameMap[68].getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		System.out.print(gameMap[30].getSymbol());
		System.out.println();
		//////////////////////////////////
		System.out.print(gameMap[67].getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		System.out.print(gameMap[31].getSymbol());
		System.out.println();
		//////////////////////////////////
		System.out.print(gameMap[66].getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		System.out.print(gameMap[32].getSymbol());
		System.out.println();
		////////////////////////////////
		System.out.print(gameMap[65].getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		System.out.print(gameMap[33].getSymbol());
		System.out.println();
		////////////////////////////////
		System.out.print(gameMap[64].getSymbol());
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		System.out.print(gameMap[34].getSymbol());
		System.out.println();
		for (int i = 63; i >= 35; i--) {
			System.out.print(gameMap[i].getSymbol());
		}

	}
}
