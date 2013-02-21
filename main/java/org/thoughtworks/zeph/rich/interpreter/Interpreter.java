package org.thoughtworks.zeph.rich.interpreter;

import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;
import org.thoughtworks.zeph.rich.props.RoadBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {

	public void interpret(String instruction, Map[] gameMap, Player player) {
		Pattern pattern;
		Matcher matcher;
		if (instruction.contains("bomb")) {
			pattern = Pattern.compile("bomb (-)?\\d*");
			matcher = pattern.matcher(instruction);
			if (matcher.matches()) {
				int n = Integer.valueOf(instruction.replace("bomb ", ""));
				if (-10 <= n && n <= 10) {
					int bombPlace = player.getCurrentMapPosition() + n;
					if (gameMap[bombPlace].getProp() == null) {
						if (player.useProp(new Bomb())) {
							gameMap[bombPlace].setProp(new Bomb());
						}
					}
				}
			}
		} else if (instruction.equals("roll")) {
			int currentMapPosition = player.getCurrentMapPosition();
			int step = player.dice();
			for (int i = 1; i <= step; i++) {
				currentMapPosition = currentMapPosition + 1;
				Prop block = new RoadBlock();
				player.setCurrentMapPosition(currentMapPosition);
				if (gameMap[currentMapPosition].getProp() == block) {
					gameMap[currentMapPosition].setProp(null);
					break;
				}
			}
			Prop bomb = new Bomb();
			if (gameMap[currentMapPosition].getProp() == bomb) {
				player.setProp(new Bomb());
				gameMap[currentMapPosition].setProp(null);
			}
		} else if (instruction.contains("block")) {
			pattern = Pattern.compile("block (-)?\\d*");
			matcher = pattern.matcher(instruction);
			if (matcher.matches()) {
				int n = Integer.valueOf(instruction.replace("block ", ""));
				if (-10 <= n && n <= 10) {
					int blockPlace = player.getCurrentMapPosition() + n;
					if (gameMap[blockPlace].getProp() == null) {
						if (player.useProp(new RoadBlock())) {
							gameMap[blockPlace].setProp(new RoadBlock());
						}
					}
				}
			}
		}
	}
}
