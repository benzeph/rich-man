package org.thoughtworks.zeph.rich.map.unit;

import org.thoughtworks.zeph.rich.player.Player;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BuildingLandOneTwo extends Grid {

	public static final int TOP_LEVEL = 3;
	private int level;
	private int price;
	private Player owner;//refactoring the name of this member field,owner
	private Scanner scanner = new Scanner(System.in);
	private String instruction;

	public BuildingLandOneTwo(int mapId, char symbol) {
		super(mapId, symbol);
		this.price = 200;
	}
	public BuildingLandOneTwo(int mapId, char symbol,String instructions) {
		super(mapId, symbol);
		this.price = 200;
		scanner = new Scanner(instructions);
	}
	@Override
	public void doesWhatItNeedToDo(Player player) {
		if (owner == null) {
			instruction = scanner.nextLine();
			while (instruction.equals("Y") && instruction.equals("N")) {
				instruction = scanner.nextLine();
			}
			if (instruction.equals("Y")) {
				if (player.getMoney() > price) {
					owner = player;
					player.subtractMoney(price);
					player.addBuilding((Grid) this);
				} else {
					return;
				}
			} else {
				return;
			}
		} else if (owner.equals(player)) {
			if (level < TOP_LEVEL) {
				instruction = scanner.nextLine();
				while (instruction != "Y" && instruction != "N") {
					instruction = scanner.nextLine();
				}
				if (instruction.equals("Y")) {
					if (player.getMoney() > price) {
						level++;
						player.subtractMoney(price);
					} else {
						return;
					}
				} else {
					return;
				}
			}
		} else {
			if (player.isGodExist()) {
				return;
			}
			if (player.getMoney() > price * (level + 1) / 2) {
				player.subtractMoney(price * (level + 1) / 2);
				owner.addMoney(price * (level + 1) / 2);
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


	public int getPrice() {
		return price;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void setOwner(Player player) {
		owner = player;
	}
}
