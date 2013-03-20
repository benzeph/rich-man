package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.gift.GamePointGift;
import org.thoughtworks.zeph.rich.gift.Gift;
import org.thoughtworks.zeph.rich.gift.GodGift;
import org.thoughtworks.zeph.rich.gift.MoneyGift;
import org.thoughtworks.zeph.rich.input.InputSystem;
import org.thoughtworks.zeph.rich.interpreter.Interpreter;
import org.thoughtworks.zeph.rich.map.*;
import org.thoughtworks.zeph.rich.output.ColorSystemOut;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Robot;

public class Rich {

	private int currentPlayerNum;
	private int totalPlayerNum;
	private Player[] players;
	private Interpreter interpreter = new Interpreter();
	private Grid[] map;

	public Rich(Player[] players, Grid[] map) {
		this.players = players;
		totalPlayerNum = players.length;
		currentPlayerNum = players.length;
		this.map = map;
	}

	public void run() {
		InputSystem input = new InputSystem(System.in);
		totalPlayerNum = players.length;
		currentPlayerNum = players.length;
		int currentPlayer = 0;
		boolean notBreak = true;
		while (currentPlayerNum != 1 && notBreak) {
			if (players[currentPlayer] != null) {
				if (players[currentPlayer].getHospitalDays() > 0) {
					currentPlayer = stayInHospital(currentPlayer);
					continue;
				}
				if (players[currentPlayer].getPrisonDays() > 0) {
					currentPlayer = stayInPrison(currentPlayer);
					continue;
				}
				if (hasYouGetAGod(currentPlayer)) {
					players[currentPlayer].getGod().timeCountDown();
				}
				printWaitingForInputString(currentPlayer);
				String inputStr = "";
				while (!inputStr.equals("roll") && !inputStr.equals("roll one")) {
					inputStr = input.getInput();
					String order = interpreter.interpret(inputStr, map, players, currentPlayer);
					System.out.println(order);
					if (order.equals("quit")) {
						notBreak = false;
						break;
					}
					if (order.equals("drawMap")) {
						drawMap();
						continue;
					}
					if (order.equals("help")) {
						help();
						continue;
					}
					if (order.equals("query")) {
						System.out.println(players[currentPlayer].query());
						continue;
					}
					doesWhatItNeedTodoAfterStop(input, currentPlayer, order);
				}
				drawMap();
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			} else {
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			}
		}
		if (currentPlayerNum != 1) {
			System.out.println("game over");
		} else {
			for (Player player : players) {
				if (null != player) {
					System.out.println(player.getName() + " win");
				}
			}
		}
	}

	private boolean isStopAtBuildingLotFourFive(int currentPlayer, String order) {
		return (getCurrentPlayerPosition(currentPlayer) instanceof BuildingLandFourFive) && !order.equals("illegal instruction");
	}

	private boolean isStopAtBuildingLotOneTwo(int currentPlayer, String order) {
		return (getCurrentPlayerPosition(currentPlayer) instanceof BuildingLandOneTwo) && !order.equals("illegal instruction");
	}

	private void printWaitingForInputString(int currentPlayer) {
		ColorSystemOut.println(players[currentPlayer].getName() + ">waiting for input", players[currentPlayer].getColorNum());
		System.out.print("command:");
		ColorSystemOut.print("", 7);
	}

	private int stayInPrison(int currentPlayer) {
		players[currentPlayer].setPrisonDays(players[currentPlayer].getPrisonDays() - 1);
		currentPlayer = (currentPlayer + 1) % totalPlayerNum;
		return currentPlayer;
	}

	private int stayInHospital(int currentPlayer) {
		players[currentPlayer].setHospitalDays(players[currentPlayer].getHospitalDays() - 1);
		System.out.println(players[currentPlayer].getName() + ":stay in hospital,hospital left time:" + players[currentPlayer].getHospitalDays());
		currentPlayer = (currentPlayer + 1) % totalPlayerNum;
		return currentPlayer;
	}

	public void runForTest(String instruction) {
		InputSystem input = new InputSystem(instruction);
		int currentPlayer = 0;
		boolean notBreak = true;
		while (currentPlayerNum != 1 && notBreak) {
			if (players[currentPlayer] != null) {
				if (players[currentPlayer].getHospitalDays() > 0) {
					currentPlayer = stayInHospital(currentPlayer);
					continue;
				}
				if (players[currentPlayer].getPrisonDays() > 0) {
					currentPlayer = stayInPrison(currentPlayer);
					continue;
				}
				if (hasYouGetAGod(currentPlayer)) {
					players[currentPlayer].getGod().timeCountDown();
				}
				String inputStr = "";
				while (!inputStr.equals("roll") && !inputStr.equals("roll one")) {
					System.out.println(players[currentPlayer].getName() + ">waiting for input");
					System.out.print("command:");
					inputStr = input.getInput();
					String order = interpreter.interpret(inputStr, map, players, currentPlayer);
					System.out.println(order);
					if (order.equals("quit")) {
						notBreak = false;
						break;
					}
					if (order.equals("drawMap")) {
						drawMap();
						continue;
					}
					if (order.equals("help")) {
						help();
						continue;
					}
					if (order.equals("query")) {
						System.out.println(players[currentPlayer].query());
						continue;
					}
					doesWhatItNeedTodoAfterStop(input, currentPlayer, order);
				}
				drawMap();
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			} else {
				currentPlayer = (currentPlayer + 1) % totalPlayerNum;
			}
		}
		if (currentPlayerNum != 1) {
			System.out.println("game over");
		} else {
			for (Player player : players) {
				if (null != player) {
					System.out.println(player.getName() + " win");
				}
			}
		}
	}

	private boolean hasYouGetAGod(int currentPlayer) {
		return players[currentPlayer].isGodExist();
	}

	private void doesWhatItNeedTodoAfterStop(InputSystem input, int currentPlayer, String order) {
		if (isStopAtBuildingLotOneTwo(currentPlayer, order)) {
			if (isLandBlank(currentPlayer)) {
				buyLand(input, currentPlayer);
			} else if (isLandBelongToPlayer(currentPlayer)) {
				levelUpLand(input, currentPlayer);
			} else {
				payLand(currentPlayer);
			}
		} else if (isStopAtBuildingLotThree(currentPlayer, order)) {
			if (isLandBlank(currentPlayer)) {
				buyLand(input, currentPlayer);
			} else if (isLandBelongToPlayer(currentPlayer)) {
				levelUpLand(input, currentPlayer);
			} else {
				payLand(currentPlayer);
			}
		} else if (isStopAtBuildingLotFourFive(currentPlayer, order)) {
			if (isLandBlank(currentPlayer)) {
				buyLand(input, currentPlayer);
			} else if (isLandBelongToPlayer(currentPlayer)) {
				levelUpLand(input, currentPlayer);
			} else {
				payLand(currentPlayer);
			}
		} else if (getCurrentPlayerPosition(currentPlayer) instanceof Mine) {
			getMine(currentPlayer);
		} else if (getCurrentPlayerPosition(currentPlayer) instanceof PropRoom) {
			getProp(input, currentPlayer);
		} else if (getCurrentPlayerPosition(currentPlayer) instanceof GiftRoom) {
			getGift(input, currentPlayer);
		} else if (getCurrentPlayerPosition(currentPlayer) instanceof Prison) {
			releasePrisoner(currentPlayer);
		} else if (getCurrentPlayerPosition(currentPlayer) instanceof Hospital) {

		} else if (getCurrentPlayerPosition(currentPlayer) instanceof MagicRoom) {

		}
	}

	private boolean isStopAtBuildingLotThree(int currentPlayer, String order) {
		return (getCurrentPlayerPosition(currentPlayer) instanceof BuildingLandThree) && !order.equals("illegal instruction");
	}

	private void help() {
		System.out.println("roll - random step 1~6\n" + "block n(-10~10) - stop you from walking ahead\n" + "bomb n(-10~10) - set a bomb\n" + "robot - clear road\n" + "sell x(game map id) - sell your land\n" + "sellTool x(tool id)\n" + "query - get detail of you\n" + "help\n" + "quit - exit game\n");
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
					getPropRoomByMapPosition(currentPlayer).sell(players[currentPlayer], new Block());
					System.out.println("you bought a block");
				} else if (num.equals("2")) {
					getPropRoomByMapPosition(currentPlayer).sell(players[currentPlayer], new Robot());
					System.out.println("you bought a robot");
				} else if (num.equals("3")) {
					getPropRoomByMapPosition(currentPlayer).sell(players[currentPlayer], new Bomb());
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

	private PropRoom getPropRoomByMapPosition(int currentPlayer) {
		return ((PropRoom) map[players[currentPlayer].getCurrentMapPosition()]);
	}

	private void releasePrisoner(int currentPlayer) {
		((Prison) map[players[currentPlayer].getCurrentMapPosition()]).release();
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
			getGiftRoomByMapPosition(currentPlayer).getGift(players[currentPlayer], bondGift);
			System.out.println("add money:2000");
		} else if (str.equals("2")) {
			Gift gamePointGift = new GamePointGift();
			getGiftRoomByMapPosition(currentPlayer).getGift(players[currentPlayer], gamePointGift);
			System.out.println("add game point:200");
		} else if (str.equals("3")) {
			Gift godGift = new GodGift();
			getGiftRoomByMapPosition(currentPlayer).getGift(players[currentPlayer], godGift);
			System.out.println("possessed with god");
		}
	}

	private GiftRoom getGiftRoomByMapPosition(int currentPlayer) {
		return ((GiftRoom) map[players[currentPlayer].getCurrentMapPosition()]);
	}

	private void getMine(int currentPlayer) {
		getMineByMapPosition(currentPlayer).addGamePoint(players[currentPlayer]);
		System.out.println(players[currentPlayer].getName() + ":you get " + getMineByMapPosition(currentPlayer).getGamePoint());
	}

	private Mine getMineByMapPosition(int currentPlayer) {
		return ((Mine) map[players[currentPlayer].getCurrentMapPosition()]);
	}

	private void payLand(int currentPlayer) {
		if (hasYouGetAGod(currentPlayer)) {
			System.out.println("wealth god bless , exempt from payment");
			return;
		}
		if (players[currentPlayer].payRent(getLandByMapPosition(currentPlayer), (getLandByMapPosition(currentPlayer)).getBelongTo())) {
			System.out.println("pay " + (getLandByMapPosition(currentPlayer)).getCost());
		} else {
			System.out.println(players[currentPlayer].getName() + " broke");
			players[currentPlayer] = null;
			currentPlayerNum--;
		}
	}

	private Land getLandByMapPosition(int currentPlayer) {
		return (Land) map[players[currentPlayer].getCurrentMapPosition()];
	}

	private boolean isLandBelongToPlayer(int currentPlayer) {
		return ((Land) getCurrentPlayerPosition(currentPlayer)).getBelongTo().equals(players[currentPlayer]);
	}

	private boolean isLandBlank(int currentPlayer) {
		return ((Land) getCurrentPlayerPosition(currentPlayer)).getBelongTo() == null;
	}

	private Grid getCurrentPlayerPosition(int currentPlayer) {
		return map[players[currentPlayer].getCurrentMapPosition()];
	}

	private void levelUpLand(InputSystem input, int currentPlayer) {
		System.out.println("Do you want to level up this land," + ((Land) getCurrentPlayerPosition(currentPlayer)).getPrice() + "(Y/N)?");
		String str = input.getInput();
		if (str.equals("Y") || str.equals("y")) {
			if (players[currentPlayer].upgradeLand((Land) getCurrentPlayerPosition(currentPlayer))) {
				System.out.println("level up land success");
			} else {
				System.out.println("level up land fail");
			}
		} else {
			System.out.println("give up level up");
		}
	}

	private void buyLand(InputSystem input, int currentPlayer) {
		System.out.println("Do you want to buy this land," + ((Land) getCurrentPlayerPosition(currentPlayer)).getPrice() + "(Y/N)?");
		String str = input.getInput();
		if (str.equals("Y") || str.equals("y")) {
			if (players[currentPlayer].buyLand((Land) getCurrentPlayerPosition(currentPlayer))) {
				System.out.println("buy land success");
			} else {
				System.out.println("buy land fail");
			}
		} else {
			System.out.println("give up buy");
		}
	}

	public void drawMap() {
		for (int i = 0; i < 29; i++) {
			map[i].getSymbol();
		}
		System.out.println();
		map[69].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		map[29].getSymbol();
		System.out.println();
		map[68].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		map[30].getSymbol();
		System.out.println();
		map[67].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		map[31].getSymbol();
		System.out.println();
		map[66].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		map[32].getSymbol();
		System.out.println();
		map[65].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		map[33].getSymbol();
		System.out.println();
		map[64].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		map[34].getSymbol();
		System.out.println();
		for (int i = 63; i >= 35; i--) {
			map[i].getSymbol();
		}
		System.out.println();
	}

	public Grid[] getMap() {
		return map;
	}
}
