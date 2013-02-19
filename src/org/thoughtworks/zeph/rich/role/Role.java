package org.thoughtworks.zeph.rich.role;

import org.thoughtworks.zeph.rich.god.God;
import org.thoughtworks.zeph.rich.map.Land;
import org.thoughtworks.zeph.rich.props.Prop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Role {
	private int id;
	private String name;
	private int money;
	private int gamePoint;
	private Map<Integer, Land> lands;
	private Map<Integer, Integer> props;
	private God god;
	private Prop prop;
	private int hospitalDays;
	private int prisonDays;

	public Role(String name, int id) {
		this.id = id;
		this.name = name;
		money = 10000;
		lands = new HashMap<Integer, Land>();
		props = new HashMap<Integer, Integer>();
	}


	public Map<Integer, Land> getLands() {
		return lands;
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

	public void addGamePoint(int gamePoint) {
		this.gamePoint = this.gamePoint + gamePoint;
	}

	public void addMoney(int money) {
		this.money = this.money + money;
	}

	public boolean buyLand(Land land) {
		if (money >= land.getPrice()) {
			lands.put(land.getMapId(), land);
			money = money - land.getPrice();
			return true;
		} else {
			return false;
		}
	}

	public boolean upgradeLand(Land land) {
		if (money >= land.getPrice()) {
			land.levelUp();
			money = money - land.getPrice();
			lands.remove(land.getMapId());
			lands.put(land.getMapId(), land);
			return true;
		} else {
			return false;
		}
	}

	public boolean sellLand(int mapId) {
		if (lands.containsKey(mapId)) {
			Land land = lands.get(mapId);
			money = money + land.getPrice() * (land.getLevel() + 1);
			lands.remove(mapId);
			return true;
		} else {
			return false;
		}
	}

	public boolean payRent(Land land) {
		if (money >= land.getCost()) {
			money = money - land.getCost();
			return true;
		} else {
			Set<Integer> set = lands.keySet();
			Iterator<Integer> it = set.iterator();
			while (money < land.getCost() && !lands.isEmpty()) {
				Land myLand = lands.get(it.next());
				money = money + myLand.getPrice() * (myLand.getLevel() + 1);
				lands.remove(myLand.getMapId());
			}
			if (money >= land.getCost()) {
				money = money - land.getCost();
				return true;
			} else {
				return false;
			}
		}
	}

	public void collectRent(int mapId) {
		Land land = lands.get(mapId);
		money = money + land.getCost();
	}

	public boolean buyProps(Prop prop) {
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

	public void query() {
		String message = "Money:" + money + "\n";
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
		message += "Land:" + "Blank Land" + blankLand + ";" + "Room" + room + ";" + "House" + house + ";" + "skyscraper" + skyscraper + "\n";
		message += "Props:" + "Block" + props.get(1) + ";Robot" + props.get(2) + ";Bomb" + props.get(3);
		System.out.println(message);
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

	public void countDownPrisonDays() {
		prisonDays = prisonDays - 1;
	}

	public int getPrisonDays() {
		return prisonDays;
	}
}

