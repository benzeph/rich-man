package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

import java.util.Scanner;

public class BuildingLandOneTwo extends Grid {

	public static final int TOP_LEVEL = 3;
	private int level;
	private int price;
	private Player belongTo;//refactoring the name of this member field,owner
	private Scanner scanner = new Scanner(System.in);
	private String instruction;

	public BuildingLandOneTwo(int mapId, char symbol) {
		super(mapId, symbol);
		this.price = 200;
	}

	@Override
	public void doesWhatItNeedToDo(Player player) {
		if (belongTo == null) {
			instruction = scanner.next();
			while (instruction != "Y" && instruction != "N") {
				instruction = scanner.next();
			}
			if (instruction.equals("Y")) {
				if (player.getMoney() > price) {
					belongTo = player;
					player.subtractMoney(price);
					player.addBuilding((Grid) this);
				} else {
					//money not enough
					return;
				}
			} else {
				return;
			}
		} else if (belongTo.equals(player)) {
			if (level < TOP_LEVEL) {
				instruction = scanner.next();
				while (instruction != "Y" && instruction != "N") {
					instruction = scanner.next();
				}
				if (instruction.equals("Y")) {
					if (player.getMoney() > price) {
						level++;
						player.subtractMoney(price);
					} else {
						//money not enough
						return;
					}
				} else {
					return;
				}
			}
		} else {
			if (player.getMoney() > price * (level + 1) / 2) {
				player.subtractMoney(price * (level + 1) / 2);
				belongTo.addMoney(price * (level + 1) / 2);
			} else {
				//Player has to sell some land to pay for the rent
				/*Set<Integer> set = lands.keySet();
				Iterator<Integer> it = set.iterator();
				while (!lands.isEmpty() && money < land.getCost()) {
					Grid myLand = lands.get(it.next());
					sellLand(myLand);
				}*/
			}
		}
	}


	public int getPrice() {
		return price;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void setOwner(Player player) {
		belongTo = player;
	}
}
