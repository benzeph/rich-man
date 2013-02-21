package org.thoughtworks.zeph.rich.interpreter;

import org.thoughtworks.zeph.rich.map.Map;
import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Bomb;

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
						gameMap[bombPlace].setProp(new Bomb());
						player.useProp(new Bomb());
					}
				}
			}
		}
	}
}
