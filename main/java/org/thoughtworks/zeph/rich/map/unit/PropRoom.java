package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.tools.Block;
import org.thoughtworks.zeph.rich.tools.Bomb;
import org.thoughtworks.zeph.rich.tools.Robot;

import java.util.Scanner;
import java.util.Set;

public class PropRoom extends Grid {

	public static final int BOTTOM_PRICE = 30;
	private Scanner scanner = new Scanner(System.in);
	private String instruction;

	public PropRoom(int mapId, char symbol) {
		super(mapId, symbol);
	}
	public PropRoom(int mapId, char symbol,String instructions) {
		super(mapId, symbol);
		scanner = new Scanner(instructions);
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		//say welcome
		while (player.getGamePoint() >= BOTTOM_PRICE) {
			instruction = scanner.nextLine();
			if (instruction.equals("1")) {
				player.subtractGamePoint(new Block().getPrice());
				player.addProp(1);
			} else if (instruction.equals("2")) {
				player.subtractGamePoint(new Robot().getPrice());
				player.addProp(2);
			} else if (instruction.equals("3")) {
				player.subtractGamePoint(new Bomb().getPrice());
				player.addProp(3);
			}
		}
		//say you game point is not enough ,exit
	}


	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void setOwner(Player player) {

	}

	@Override
	public void addPlayer(Player player) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Set<Player> getPlayers() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

}
