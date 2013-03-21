package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;
import org.thoughtworks.zeph.rich.props.Block;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Robot;

import java.util.Scanner;

public class PropRoom extends Grid {

	private Scanner scanner = new Scanner(System.in);
	private String instruction;

	public PropRoom(int mapId, char symbol) {
		super(mapId, symbol);
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		//say welcome
		while (player.getGamePoint() > 30) {
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
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getLevel() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void setOwner(Player player) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

}
