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
		mapInitialize();
	}

	private void mapInitialize() {
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

	@Override
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
				if (isYouGetAGod(currentPlayer)) {
					players[currentPlayer].getGod().timeCountDown();
				}
				printWaitingForInputString(currentPlayer);
				String inputStr = "";
				while (!inputStr.equals("roll") && !inputStr.equals("roll one")) {
					inputStr = input.getInput();
					String order = interpreter.interpret(inputStr, gameMap, players, currentPlayer);
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
					doWhatItNeedTodoAfterStop(input, currentPlayer, order);
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
		return (currentPlayerPosition(currentPlayer) instanceof BuildingLotFourFive) && !order.equals("illegal instruction");
	}

	private boolean isStopAtBuildingLotOneTwo(int currentPlayer, String order) {
		return (currentPlayerPosition(currentPlayer) instanceof BuildingLotOneTwo) && !order.equals("illegal instruction");
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

	@Override
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
				if (isYouGetAGod(currentPlayer)) {
					players[currentPlayer].getGod().timeCountDown();
				}
				String inputStr = "";
				while (!inputStr.equals("roll") && !inputStr.equals("roll one")) {
					System.out.println(players[currentPlayer].getName() + ">waiting for input");
					System.out.print("command:");
					inputStr = input.getInput();
					String order = interpreter.interpret(inputStr, gameMap, players, currentPlayer);
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
					doWhatItNeedTodoAfterStop(input, currentPlayer, order);
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

	private boolean isYouGetAGod(int currentPlayer) {
		return (null != players[currentPlayer].getGod()) && (players[currentPlayer].getGod().getLeftTime() > 0);
	}

	private void doWhatItNeedTodoAfterStop(InputSystem input, int currentPlayer, String order) {
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

	private boolean isStopAtBuildingLotThree(int currentPlayer, String order) {
		return (currentPlayerPosition(currentPlayer) instanceof BuildingLotThree) && !order.equals("illegal instruction");
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
		return ((PropRoom) gameMap[players[currentPlayer].getCurrentMapPosition()]);
	}

	private void releasePrisoner(int currentPlayer) {
		((Prison)gameMap[players[currentPlayer].getCurrentMapPosition()]).release();
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
		return ((GiftRoom) gameMap[players[currentPlayer].getCurrentMapPosition()]);
	}

	private void getMine(int currentPlayer) {
		getMineByMapPosition(currentPlayer).addGamePoint(players[currentPlayer]);
		System.out.println(players[currentPlayer].getName() + ":you get " + getMineByMapPosition(currentPlayer).getGamePoint());
	}

	private Mine getMineByMapPosition(int currentPlayer) {
		return ((Mine) gameMap[players[currentPlayer].getCurrentMapPosition()]);
	}

	private void payLand(int currentPlayer) {
		if (isYouGetAGod(currentPlayer)) {
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
		return (Land) gameMap[players[currentPlayer].getCurrentMapPosition()];
	}

	private boolean isLandBelongToPlayer(int currentPlayer) {
		return ((Land) currentPlayerPosition(currentPlayer)).getBelongTo().equals(players[currentPlayer]);
	}

	private boolean isLandBlank(int currentPlayer) {
		return ((Land) currentPlayerPosition(currentPlayer)).getBelongTo() == null;
	}

	private Map currentPlayerPosition(int currentPlayer) {
		return gameMap[players[currentPlayer].getCurrentMapPosition()];
	}

	private void levelUpLand(InputSystem input, int currentPlayer) {
		System.out.println("Do you want to level up this land," + ((Land) currentPlayerPosition(currentPlayer)).getPrice() + "(Y/N)?");
		String str = input.getInput();
		if (str.equals("Y") || str.equals("y")) {
			if (players[currentPlayer].upgradeLand((Land) currentPlayerPosition(currentPlayer))) {
				System.out.println("level up land success");
			} else {
				System.out.println("level up land fail");
			}
		} else {
			System.out.println("give up level up");
		}
	}

	private void buyLand(InputSystem input, int currentPlayer) {
		System.out.println("Do you want to buy this land," + ((Land) currentPlayerPosition(currentPlayer)).getPrice() + "(Y/N)?");
		String str = input.getInput();
		if (str.equals("Y") || str.equals("y")) {
			if (players[currentPlayer].buyLand((Land) currentPlayerPosition(currentPlayer))) {
				System.out.println("buy land success");
			} else {
				System.out.println("buy land fail");
			}
		} else {
			System.out.println("give up buy");
		}
	}

	@Override
	public void drawMap() {
		for (int i = 0; i < 29; i++) {
			gameMap[i].getSymbol();
		}
		System.out.println();
		///////////////////////////////////
		gameMap[69].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		gameMap[29].getSymbol();
		System.out.println();
		///////////////////////////////////
		gameMap[68].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		gameMap[30].getSymbol();
		System.out.println();
		//////////////////////////////////
		gameMap[67].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		gameMap[31].getSymbol();
		System.out.println();
		//////////////////////////////////
		gameMap[66].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		gameMap[32].getSymbol();
		System.out.println();
		////////////////////////////////
		gameMap[65].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		gameMap[33].getSymbol();
		System.out.println();
		////////////////////////////////
		gameMap[64].getSymbol();
		for (int i = 1; i < 28; i++) {
			System.out.print(" ");
		}
		gameMap[34].getSymbol();
		System.out.println();
		for (int i = 63; i >= 35; i--) {
			gameMap[i].getSymbol();
		}
		System.out.println();
	}
}
