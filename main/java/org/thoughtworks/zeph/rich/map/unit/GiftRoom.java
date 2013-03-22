package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.god.WealthGod;
import org.thoughtworks.zeph.rich.output.SystemOut;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Scanner;
import java.util.Set;

public class GiftRoom extends Grid {

	private Scanner scanner = new Scanner(System.in);
	private String instruction;

	public GiftRoom(int mapId, char symbol) {
		super(mapId, symbol);
	}

	public GiftRoom(int mapId, char symbol, String instructions) {
		super(mapId, symbol);
		scanner = new Scanner(instructions);
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		SystemOut.welcomeToGiftRoom();
		instruction = scanner.nextLine();
		if (instruction.equals("1")) {
			player.addMoney(2000);
			SystemOut.youGetGift("2000元");
		} else if (instruction.equals("2")) {
			player.addGamePoint(200);
			SystemOut.youGetGift("200游戏点");
		} else if (instruction.equals("3")) {
			player.setGod(new WealthGod());
			SystemOut.youGetGift("财神爷");
		}
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
