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
			}
		}
	}

	public BuildingLandOneTwo(int mapId, char symbol) {
		super(mapId, symbol);
		setPrice(200);
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Player getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(Player belongTo) {
		this.belongTo = belongTo;
	}

	public int getCost() {
		return price * (level + 1) / 2;
	}

	public void levelUp() {
		level = level + 1;
	}

	@Override
	public int getLevel() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
