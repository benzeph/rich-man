package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.gift.Gift;
import org.thoughtworks.zeph.rich.god.God;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Scanner;

public class GiftRoom extends Grid {

	private Scanner scanner = new Scanner(System.in);
	private String instruction;

	@Override
	public void doesWhatItNeedToDo(Player player) {
		instruction = scanner.nextLine();
		if (instruction.equals("1")) {
			player.addMoney(2000);
		} else if (instruction.equals("2")) {
			player.addGamePoint(200);
		} else if (instruction.equals("3")) {
			player.setGod(new God());
		}
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


	public GiftRoom(int mapId, char symbol) {
		super(mapId, symbol);
	}

	public void getGift(Player player, Gift gift) {
		gift.openGift(player);
	}
}
