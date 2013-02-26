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

	public String interpret(String instruction, Map[] gameMap, Player[] players, int currentPlayer) {
		Player player = players[currentPlayer];
		Pattern pattern;
		Matcher matcher;
		if (instruction.contains("bomb")) {
			pattern = Pattern.compile("bomb (-)?\\d*");
			matcher = pattern.matcher(instruction);
			if (matcher.matches()) {
				int n = Integer.valueOf(instruction.replace("bomb ", ""));
				if (-10 <= n && n <= 10) {
					int bombPlace = (gameMap.length + player.getCurrentMapPosition() + n) % gameMap.length;
					boolean isBeenOccupied = false;
					for (int i = 0; i < players.length; i++) {
						if (null != players[i]) {
							if (players[i].getCurrentMapPosition() == bombPlace) {
								isBeenOccupied = true;
							}
						}
					}
					if (null != gameMap[bombPlace].getProp()) {
						return "place has been occupied";
					}
					if (isBeenOccupied) {
						return "place has been occupied";
					}
					if (gameMap[bombPlace].getProp() == null) {
						if (player.useProp(new Bomb())) {
							gameMap[bombPlace].setProp(new Bomb());
							return "bomb set at " + bombPlace;
						} else {
							return "you don't have a bomb";
						}
					}
				}
			}
			return "bomb n(-10<=n<=10)";
		} else if (instruction.equals("roll")) {
			int currentMapPosition = player.getCurrentMapPosition();
			int step = player.dice();
			for (int i = 1; i <= step; i++) {
				currentMapPosition = (currentMapPosition + 1) % gameMap.length;
				player.setCurrentMapPosition(currentMapPosition);
				if (player.getProp() instanceof Bomb) {
					((Bomb) player.getProp()).timeCountDown();
					if (((Bomb) player.getProp()).getLeftTime() == 0) {
						player.setCurrentMapPosition(14);
						player.setHospitalDays(3);
						player.setProp(null);
						System.out.println(player.getName() + ":bomb explode , you are in hospital");
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
		} else if (instruction.contains("block")) {
			pattern = Pattern.compile("block (-)?\\d*");
			matcher = pattern.matcher(instruction);
			if (matcher.matches()) {
				int n = Integer.valueOf(instruction.replace("block ", ""));
				if (-10 <= n && n <= 10) {
					int blockPlace = (gameMap.length + player.getCurrentMapPosition() + n) % gameMap.length;
					boolean isBeenOccupied = false;
					for (int i = 0; i < players.length; i++) {
						if (null != players[i]) {
							if (players[i].getCurrentMapPosition() == blockPlace) {
								isBeenOccupied = true;
							}
						}
					}
					if (null != gameMap[blockPlace].getProp()) {
						return "place has been occupied";
					}
					if (isBeenOccupied) {
						return "place has been occupied";
					}
					if (gameMap[blockPlace].getProp() == null) {
						if (player.useProp(new Block())) {
							gameMap[blockPlace].setProp(new Block());
							return "block at " + blockPlace;
						}
					}
				}
			}
			return "block n(-10=<n<=10)";
		} else if (instruction.equals("robot")) {
			int currentMapPosition = player.getCurrentMapPosition();
			for (int i = 1; i <= 10; i++) {
				currentMapPosition = currentMapPosition + 1;
				gameMap[currentMapPosition].setProp(null);
			}
			return "robot out";
		} else if (instruction.contains("sell") && !instruction.contains("sellTool")) {
			pattern = Pattern.compile("sell \\d*");
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
		} else if (instruction.contains("sellTool")) {
			pattern = Pattern.compile("sellTool \\d");
			matcher = pattern.matcher(instruction);
			if (matcher.matches()) {
				int n = Integer.valueOf(instruction.replace("sellTool ", ""));
				switch (n) {
					case 1:
						if (player.sellProp(new Block())) {
							return "sell block,GP:" + 50;
						} else {
							return "you don't have a block";
						}
					case 2:
						if (player.sellProp(new Robot())) {
							return "sell robot,GP:" + 30;
						} else {
							return "you don't have a robot";
						}
					case 3:
						if (player.sellProp(new Bomb())) {
							return "sell bomb,GP:" + 50;
						} else {
							return "you don't have a bomb";
						}
				}
			}
			return "sellTool n(n={1,2,3})";
		} else if (instruction.equals("query")) {
			return player.query();
		} else if (instruction.equals("quit")) {
			return "quit";
		} else if (instruction.equals("roll one")) {
			int currentMapPosition = player.getCurrentMapPosition();
			int step = 1;
			for (int i = 1; i <= step; i++) {
				currentMapPosition = (currentMapPosition + 1) % gameMap.length;
				player.setCurrentMapPosition(currentMapPosition);
				if (player.getProp() instanceof Bomb) {
					((Bomb) player.getProp()).timeCountDown();
					if (((Bomb) player.getProp()).getLeftTime() == 0) {
						player.setCurrentMapPosition(14);
						player.setHospitalDays(3);
						player.setProp(null);
						System.out.println(player.getName() + ":bomb explode , you are in hospital");
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
		} else if (instruction.equals("drawMap")) {
			return "drawMap";
		} else if (instruction.equals("help")) {
			return "help";
		}
		return "illegal instruction";
	}
}
