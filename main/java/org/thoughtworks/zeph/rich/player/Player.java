package org.thoughtworks.zeph.rich.player;

import org.thoughtworks.zeph.rich.god.God;
import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.props.Prop;

import java.util.*;

public class Player {
	private int id;
	private String name;
	private int colorNum;
	private int money;
	private God god = null;
	private Prop prop = null;
	private int gamePoint = 0;
	private int currentMapPosition = 0;
	private int hospitalDays = 0;
	private int prisonDays = 0;
	private Map<Integer, Integer> props = new HashMap<Integer, Integer>();
	private Map<Integer, Land> lands = new HashMap<Integer, Land>();

	public Player(String name, int id, int colorNum, int money) {
		this.id = id;
		this.name = name;
		this.colorNum = colorNum;
		this.money = money;
	}

	public Map<Integer, Land> getLands() {
		return lands;
	}

	public int getColorNum() {
		return colorNum;
	}

	public String getName() {
		return name;
	}

	public Map<Integer, Integer> getProps() {
		return props;
	}

	public God getGod() {
		return god;
	}

	public int getGamePoint() {
		return gamePoint;
	}

	public int getMoney() {
		return money;
	}

	public void setGod(God god) {
		this.god = god;
	}

	public void setHospitalDays(int hospitalDays) {
		this.hospitalDays = hospitalDays;
	}

	public void countDownHospitalDays() {
		hospitalDays = hospitalDays - 1;
	}

	public int getHospitalDays() {
		return hospitalDays;
	}

	public void setPrisonDays(int prisonDays) {
		this.prisonDays = prisonDays;
	}

	public int getCurrentMapPosition() {
		return currentMapPosition;
	}

	public void setCurrentMapPosition(int currentMapPosition) {
		this.currentMapPosition = currentMapPosition;
	}

	public Prop getProp() {
		return prop;
	}

	public void setProp(Prop prop) {
		this.prop = prop;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void countDownPrisonDays() {
		prisonDays = prisonDays - 1;
	}

	public int getPrisonDays() {
		return prisonDays;
	}

	public void addGamePoint(int gamePoint) {
		this.gamePoint = this.gamePoint + gamePoint;
	}

	public void addMoney(int money) {
		this.money = this.money + money;
	}

	public boolean isGodExist() {
		if (null != god) {
			if (god.getLeftTime() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean buyLand(Land land) {
		if (money >= land.getPrice()) {
			lands.put(land.getId(), land);
			money = money - land.getPrice();
			land.setBelongTo(this);
			return true;
		} else {
			return false;
		}
	}

	public boolean upgradeLand(Land land) {
		if (money >= land.getPrice()) {
			land.levelUp();
			money = money - land.getPrice();
			lands.remove(land.getId());
			lands.put(land.getId(), land);
			return true;
		} else {
			return false;
		}
	}

	public boolean sellLand(Land land) {
		if (lands.containsKey(land.getId())) {
			money = money + land.getPrice() * (land.getLevel() + 1);
			lands.remove(land.getId());
			land.setBelongTo(null);
			return true;
		} else {
			return false;
		}
	}

	public boolean payRent(Land land, Player player) {
		if (money >= land.getCost()) {
			money = money - land.getCost();
			player.addMoney(land.getCost());
			return true;
		} else {
			Set<Integer> set = lands.keySet();
			Iterator<Integer> it = set.iterator();
			while (!lands.isEmpty() && money < land.getCost()) {
				Land myLand = lands.get(it.next());
				sellLand(myLand);
			}
			if (money >= land.getCost()) {
				money = money - land.getCost();
				player.addMoney(land.getCost());
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean buyProp(Prop prop) {
		if (gamePoint >= prop.getPrice()) {
			if (props.containsKey(prop.getId())) {
				int n = props.get(prop.getId()) + 1;
				props.remove(prop.getId());
				props.put(prop.getId(), n);
			} else {
				props.put(prop.getId(), 1);
			}
			gamePoint = gamePoint - prop.getPrice();
			return true;
		} else {
			return false;
		}
	}

	public boolean useProp(Prop prop) {
		if (props.containsKey(prop.getId())) {
			int n = props.get(prop.getId()) - 1;
			if (n == 0) {
				props.remove(prop.getId());
			} else {
				props.remove(prop.getId());
				props.put(prop.getId(), n);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean sellProp(Prop prop) {
		if (props.containsKey(prop.getId())) {
			int n = props.get(prop.getId()) - 1;
			if (n == 0) {
				props.remove(prop.getId());
			} else {
				props.remove(prop.getId());
				props.put(prop.getId(), n);
			}
			gamePoint = gamePoint + prop.getPrice();
			return true;
		} else {
			return false;
		}
	}

	public String query() {
		String message = "\nMoney:" + money + "\n";
		message += "Game Point:" + gamePoint + "\n";
		int blankLand = 0, room = 0, house = 0, skyscraper = 0;
		Set<Integer> set = lands.keySet();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			Land land = lands.get(it.next());
			switch (land.getLevel()) {
				case 0:
					blankLand++;
					break;
				case 1:
					room++;
					break;
				case 2:
					house++;
					break;
				case 3:
					skyscraper++;
					break;
			}
		}
		message += "Land:" + "BlankLand" + blankLand + ";" + "Room" + room + ";" + "House" + house + ";" + "skyscraper" + skyscraper + "\n";
		message += "Props:" + "Block" + props.get(1) + ";Robot" + props.get(2) + ";Bomb" + props.get(3);
		return message;
	}


	public int dice() {
		return new Random().nextInt(6) + 1;
	}
}

