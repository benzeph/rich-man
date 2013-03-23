package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.output.Printer;
import org.thoughtworks.zeph.rich.player.Player;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BuildingLandFourFive extends Grid {
	public static final int TOP_LEVEL = 3;
	private int level;
	private int price;
	private Player owner;
	private Scanner scanner = new Scanner(System.in);
	private String instruction;

	public BuildingLandFourFive(int mapId, char symbol) {
		super(mapId, symbol);
		this.price = 300;
	}

	public BuildingLandFourFive(int mapId, char symbol, String instructions) {
		super(mapId, symbol);
		this.price = 300;
		scanner = new Scanner(instructions);
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		if (owner == null) {
			Printer.doYouWantToBuyThisLand(price);
			instruction = scanner.nextLine();
			while (!instruction.equals("Y") && !instruction.equals("N")) {
				Printer.doYouWantToBuyThisLand(price);
				instruction = scanner.nextLine();
			}
			if (instruction.equals("Y")) {
				if (player.getMoney() > price) {
					owner = player;
					player.subtractMoney(price);
					player.addBuilding(this);
				} else {
					Printer.yourMoneyIsNotEnough();
					return;
				}
			} else {
				return;
			}
		} else if (owner.equals(player)) {
			if (level < TOP_LEVEL) {
				Printer.doYouWantToLevelUpThisLand(price);
				instruction = scanner.nextLine();
				while (!instruction.equals("Y") && !instruction.equals("N")) {
					instruction = scanner.nextLine();
				}
				if (instruction.equals("Y")) {
					if (player.getMoney() > price) {
						level++;
						player.subtractMoney(price);
						setSymbol((char) (level + 48));
					} else {
						Printer.yourMoneyIsNotEnough();
						return;
					}
				} else {
					return;
				}
			}
		} else {
			if (player.isGodExist()) {
				Printer.godBlessYou();
				return;
			}
			if (owner.isInHospital()) {
				Printer.ownerIsInTheHosptial(owner.getName());
				return;
			}
			if (owner.isInPrison()) {
				Printer.ownerIsInThePrison(owner.getName());
				return;
			}
			if (player.getMoney() > price * (level + 1) / 2) {
				player.subtractMoney(price * (level + 1) / 2);
				owner.addMoney(price * (level + 1) / 2);
				Printer.paySomeoneMoney(owner.getName(), price * (level + 1) / 2);
			} else {
				Map<Integer, Grid> lands = player.getLands();
				Set<Integer> set = lands.keySet();
				Iterator<Integer> it = set.iterator();
				while (it.hasNext()) {
					Grid land = lands.get(it.next());
					player.sellLand(land);
					if (player.getMoney() > price * (level + 1) / 2) {
						player.subtractMoney(price * (level + 1) / 2);
						owner.addMoney(price * (level + 1) / 2);
						break;
					}
				}
			}
		}
	}

	public Player getOwner() {
		return owner;
	}

	public int getPrice() {
		return price;
	}

	public boolean isOwnerExist() {
		return owner != null;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setOwner(Player player) {
		owner = player;
	}

	@Override
	public void addPlayer(Player player) {

	}

	@Override
	public Set<Player> getPlayers() {
		return null;
	}
}
