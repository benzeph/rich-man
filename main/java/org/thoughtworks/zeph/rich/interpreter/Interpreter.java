package org.thoughtworks.zeph.rich.interpreter;

import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {

	public static final String BOMB_PATTEN = "bomb (-)?\\d*";
	public static final String BLOCK_PATTERN = "block (-)?\\d*";
	public static final String SELL_PATTERN = "sell \\d*";
	public static final String SELL_TOOL_PATTERN = "sellTool \\d";
	private Pattern pattern;
	private Matcher matcher;

	public String interpret(String instruction, Map[] gameMap, Player[] players, int currentPlayer) {
		Player player = players[currentPlayer];
		if (instruction.contains("bomb")) {
			return bombAction(instruction, gameMap, players, player);
		} else if (instruction.equals("roll")) {
			return rollAction(gameMap, player);
		} else if (instruction.contains("block")) {
			return blockAction(instruction, gameMap, players, player);
		} else if (instruction.equals("robot")) {
			return robotAction(gameMap, player);
		} else if (instruction.contains("sell") && !instruction.contains("sellTool")) {
			return sellAction(instruction, gameMap, player);
		} else if (instruction.contains("sellTool")) {
			return sellToolAction(instruction, player);
		} else if (instruction.equals("query")) {
			return "query";
		} else if (instruction.equals("quit")) {
			return "quit";
		} else if (instruction.equals("roll one")) {
			return rollOneAction(gameMap, player);
		} else if (instruction.equals("drawMap")) {
			return "drawMap";
		} else if (instruction.equals("help")) {
			return "help";
		} else {
			return "illegal instruction";
		}
	}

	private String rollAction(Map[] map, Player player) {
		int currentMapPosition = player.getCurrentMapPosition();
		int step = player.dice();
		for (int i = 1; i <= step; i++) {
			currentMapPosition = (currentMapPosition + 1) % map.length;
			setMapSymbolEveryStep(map, player, currentMapPosition);
			player.setCurrentMapPosition(currentMapPosition);
			if (player.getProp() instanceof Bomb) {
				((Bomb) player.getProp()).timeCountDown();
				if (((Bomb) player.getProp()).getLeftTime() == 0) {
					bombExplode(player);
					break;
				}
			}
			if (map[currentMapPosition].getProp() instanceof Block) {
				map[currentMapPosition].setProp(null);
				return "roll , block at " + currentMapPosition;
			}
		}
		if (map[currentMapPosition].getProp() instanceof Bomb) {
			player.setProp(new Bomb());
			map[currentMapPosition].setProp(null);
			return "roll , stop at " + currentMapPosition + " , meet a bomb";
		}
		return "roll , stop at " + currentMapPosition;
	}

	private void setMapSymbolEveryStep(Map[] gameMap, Player player, int currentMapPosition) {
		if (gameMap[currentMapPosition].getPlayerSymbol() == ' ') {
			gameMap[currentMapPosition].setPlayerSymbol(player.getSymbol());
		}
		if (gameMap[(gameMap.length + currentMapPosition - 1) % gameMap.length].getPlayerSymbol() == player.getSymbol()) {
			gameMap[(gameMap.length + currentMapPosition - 1) % gameMap.length].setPlayerSymbol(' ');
		}
	}

	private String rollOneAction(Map[] gameMap, Player player) {
		int currentMapPosition = player.getCurrentMapPosition();
		int step = 1;
		for (int i = 1; i <= step; i++) {
			currentMapPosition = (currentMapPosition + 1) % gameMap.length;
			setMapSymbolEveryStep(gameMap, player, currentMapPosition);
			player.setCurrentMapPosition(currentMapPosition);
			if (player.getProp() instanceof Bomb) {
				((Bomb) player.getProp()).timeCountDown();
				if (((Bomb) player.getProp()).getLeftTime() == 0) {
					bombExplode(player);
					break;
				}
			}
			if (gameMap[currentMapPosition].getProp() instanceof Block) {
				gameMap[currentMapPosition].setProp(null);
				return "roll , block at " + currentMapPosition;
			}
		}
		if (gameMap[currentMapPosition].getProp() instanceof Bomb) {
			player.setProp(new Bomb());
			gameMap[currentMapPosition].setProp(null);
			return "roll , stop at " + currentMapPosition + " , meet a bomb";
		}
		return "roll , stop at " + currentMapPosition;
	}

	private void bombExplode(Player player) {
		player.setCurrentMapPosition(14);
		player.setHospitalDays(3);
		player.setProp(null);
		System.out.println(player.getName() + ":bomb explode , you are in hospital");
	}

	private String sellToolAction(String instruction, Player player) {
		pattern = Pattern.compile(SELL_TOOL_PATTERN);
		matcher = pattern.matcher(instruction);
		if (matcher.matches()) {
			int n = Integer.valueOf(instruction.replace("sellTool ", ""));
			switch (n) {
				case 1:
					return sellBlock(player);
				case 2:
					return sellRobot(player);
				case 3:
					return sellBomb(player);
			}
		}
		return "sellTool n(n={1,2,3})";
	}

	private String sellBomb(Player player) {
		if (player.sellProp(new Bomb())) {
			return "sell bomb,GP:" + 50;
		} else {
			return "you don't have a bomb";
		}
	}

	private String sellRobot(Player player) {
		if (player.sellProp(new Robot())) {
			return "sell robot,GP:" + 30;
		} else {
			return "you don't have a robot";
		}
	}

	private String sellBlock(Player player) {
		if (player.sellProp(new Block())) {
			return "sell block,GP:" + 50;
		} else {
			return "you don't have a block";
		}
	}

	private String sellAction(String instruction, Map[] gameMap, Player player) {
		pattern = Pattern.compile(SELL_PATTERN);
		matcher = pattern.matcher(instruction);
		if (matcher.matches()) {
			int n = Integer.valueOf(instruction.replace("sell ", ""));
			if (0 < n && n < gameMap.length) {
				if (player.sellLand((Land) gameMap[n])) {
					return "sell land " + n + ", money:" + ((Land) gameMap[n]).getPrice();
				} else {
					return "not your building";
				}
			}

		}
		return "sell n(0<n<" + gameMap.length + ")";
	}

	private String robotAction(Map[] gameMap, Player player) {
		int robotMapPosition = player.getCurrentMapPosition();
		for (int i = 1; i <= 10; i++) {
			robotMapPosition = robotMapPosition + 1;
			gameMap[robotMapPosition].setProp(null);
		}
		return "robot out";
	}

	private String blockAction(String instruction, Map[] map, Player[] players, Player player) {
		pattern = Pattern.compile(BLOCK_PATTERN);
		matcher = pattern.matcher(instruction);
		if (matcher.matches()) {
			int n = Integer.valueOf(instruction.replace("block ", ""));
			if (-10 <= n && n <= 10) {
				int blockPlace = (map.length + player.getCurrentMapPosition() + n) % map.length;
				boolean isBeenOccupied = false;
				for (Player everyonePlayer : players) {
					if (null != everyonePlayer) {
						if (everyonePlayer.getCurrentMapPosition() == blockPlace) {
							isBeenOccupied = true;
						}
					}
				}
				if (null != map[blockPlace].getProp()) {
					return "place has been occupied";
				}
				if (isBeenOccupied) {
					return "place has been occupied";
				}
				if (map[blockPlace].getProp() == null) {
					if (player.useProp(new Block())) {
						map[blockPlace].setProp(new Block());
						return "block at " + blockPlace;
					}
				}
			}
		}
		return "block n(-10=<n<=10)";
	}

	private String bombAction(String instruction, Map[] map, Player[] players, Player player) {
		pattern = Pattern.compile(BOMB_PATTEN);
		matcher = pattern.matcher(instruction);
		if (matcher.matches()) {
			int n = Integer.valueOf(instruction.replace("bomb ", ""));
			if (-10 <= n && n <= 10) {
				int bombPlace = (map.length + player.getCurrentMapPosition() + n) % map.length;
				boolean isBeenOccupied = false;
				for (Player everyPlayer : players) {
					if (null != everyPlayer) {
						if (everyPlayer.getCurrentMapPosition() == bombPlace) {
							isBeenOccupied = true;
						}
					}
				}
				if (null != map[bombPlace].getProp()) {
					return "place has been occupied";
				}
				if (isBeenOccupied) {
					return "place has been occupied";
				}
				if (map[bombPlace].getProp() == null) {
					if (player.useProp(new Bomb())) {
						map[bombPlace].setProp(new Bomb());
						return "bomb set at " + bombPlace;
					} else {
						return "you don't have a bomb";
					}
				}
			}
		}
		return "bomb n(-10<=n<=10)";
	}
}
