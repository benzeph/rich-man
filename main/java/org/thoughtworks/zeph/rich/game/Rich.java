package org.thoughtworks.zeph.rich.game;

import org.thoughtworks.zeph.rich.gift.GamePointGift;
import org.thoughtworks.zeph.rich.gift.Gift;
import org.thoughtworks.zeph.rich.gift.GodGift;
import org.thoughtworks.zeph.rich.gift.MoneyGift;
import org.thoughtworks.zeph.rich.input.InputSystem;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.map.unit.*;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Robot;
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

	private void doesWhatItNeedTodoAfterStop(InputSystem input, int currentPlayer) {
		if (isStopAtBuildingLotOneTwo(currentPlayer)) {
			if (isLandBlank(currentPlayer)) {
				buyLand(input, currentPlayer);
			} else if (isLandBelongToPlayer(currentPlayer)) {
				levelUpLand(input, currentPlayer);
			} else {
				payLand(currentPlayer);
			}
		} else if (isStopAtBuildingLotThree(currentPlayer)) {
			if (isLandBlank(currentPlayer)) {
				buyLand(input, currentPlayer);
			} else if (isLandBelongToPlayer(currentPlayer)) {
				levelUpLand(input, currentPlayer);
			} else {
				payLand(currentPlayer);
			}
		} else if (isStopAtBuildingLotFourFive(currentPlayer)) {
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

	private boolean isStopAtBuildingLotFourFive(int currentPlayer) {
		return map.isBuildingLandFour(players[currentPlayer].getCurrentMapPosition());
	}

	private boolean isStopAtBuildingLotOneTwo(int currentPlayer) {
		return map.isBuildingLandOneTwo(players[currentPlayer].getCurrentMapPosition());
	}

	private boolean isStopAtBuildingLotThree(int currentPlayer) {
		return map.isBuildingLandThree(players[currentPlayer].getCurrentMapPosition());
	}

	private boolean hasYouGetAGod(int currentPlayer) {
		return players[currentPlayer].isGodExist();
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
		return ((PropRoom) map.getGrid(players[currentPlayer].getCurrentMapPosition()));
	}

	private void releasePrisoner(int currentPlayer) {
		((Prison) map.getGrid(players[currentPlayer].getCurrentMapPosition())).release();
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
		return ((GiftRoom) map.getGrid(players[currentPlayer].getCurrentMapPosition()));
	}

	private void getMine(int currentPlayer) {
		getMineByMapPosition(currentPlayer).addGamePoint(players[currentPlayer]);
		System.out.println(players[currentPlayer].getName() + ":you get " + getMineByMapPosition(currentPlayer).getGamePoint());
	}

	private Mine getMineByMapPosition(int currentPlayer) {
		return ((Mine) map.getGrid(players[currentPlayer].getCurrentMapPosition()));
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

	private Grid getLandByMapPosition(int currentPlayer) {
		return map.getGrid(players[currentPlayer].getCurrentMapPosition());
	}

	private boolean isLandBelongToPlayer(int currentPlayer) {
		return (getCurrentPlayerPosition(currentPlayer)).getBelongTo().equals(players[currentPlayer]);
	}

	private boolean isLandBlank(int currentPlayer) {
		return (getCurrentPlayerPosition(currentPlayer)).getBelongTo() == null;
	}

	private Grid getCurrentPlayerPosition(int currentPlayer) {
		return map.getGrid(players[currentPlayer].getCurrentMapPosition());
	}

	private void levelUpLand(InputSystem input, int currentPlayer) {
		String str = input.getInput();
		if (str.equals("Y") || str.equals("y")) {
			if (players[currentPlayer].upgradeLand(getCurrentPlayerPosition(currentPlayer))) {
				System.out.println("level up land success");
			} else {
				System.out.println("level up land fail");
			}
		} else {
			System.out.println("give up level up");
		}
	}

	private void buyLand(InputSystem input, int currentPlayer) {
		System.out.println("Do you want to buy this land," + ((Grid) getCurrentPlayerPosition(currentPlayer)).getPrice() + "(Y/N)?");
		String str = input.getInput();
		if (str.equals("Y") || str.equals("y")) {
			if (players[currentPlayer].buyLand((Grid) getCurrentPlayerPosition(currentPlayer))) {
				System.out.println("buy land success");
			} else {
				System.out.println("buy land fail");
			}
		} else {
			System.out.println("give up buy");
		}
	}
}
