package org.thoughtworks.zeph.rich.player;

import org.thoughtworks.zeph.rich.god.God;
import org.thoughtworks.zeph.rich.map.unit.Grid;
import org.thoughtworks.zeph.rich.props.Bomb;
import org.thoughtworks.zeph.rich.props.Prop;

import java.util.*;

public class Player {
	private int id;
	private String name;
	private int colorNum;
	private int money;
	private char symbol;
	private God god = null;
	private Prop prop = null;
	private int gamePoint = 0;
	private int currentMapPosition = 0;
	private int hospitalDays = 0;
	private int prisonDays = 0;
	private Map<Integer, Integer> props = new HashMap<Integer, Integer>();
	private Map<Integer, Grid> lands = new HashMap<Integer, Grid>();

	public Player(String name, int id, int colorNum, int money) {
		this.id = id;
		this.name = name;
		this.colorNum = colorNum;
		this.money = money;
		symbol = name.charAt(0);
	}

	public boolean isCarriedWithABomb() {
		if (null == prop) {
			return false;
		} else {
			return prop instanceof Bomb;
		}
	}

	public boolean isInHospital() {
		return hospitalDays > 0;
	}

	public boolean isInPrison() {
		return prisonDays > 0;
	}

	public boolean isBombExplode() {
		Bomb bomb = (Bomb) prop;
		return bomb.getLeftTime() == 0;
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

	public boolean isPlayerHasABlock() {
		return props.containsKey(1);
	}

	public boolean isPlayerHasARobot() {
		return props.containsKey(2);
	}

	public boolean isPlayerHasABomb() {
		return props.containsKey(3);
	}

	public boolean isPlayerHasBuilding(int id) {
		return lands.containsKey(id);
	}

	public char getSymbol() {
		return symbol;
	}

	public Map<Integer, Grid> getLands() {
		return lands;
	}

	public int getColorNum() {
		return colorNum;
	}

	public String getName() {
		return name;
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

	public void setProp(Prop prop) {
		this.prop = prop;
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


	public boolean sellLand(Grid land) {
		if (lands.containsKey(land.getId())) {
			money = money + land.getPrice() * (land.getLevel() + 1);
			lands.remove(land.getId());
			land.setOwner(null);
			return true;
		} else {
			return false;
		}
	}

	public void useProp(Prop prop) {
		int n = props.get(prop.getId()) - 1;
		if (n == 0) {
			props.remove(prop.getId());
		} else {
			props.remove(prop.getId());
			props.put(prop.getId(), n);
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
			Grid land = lands.get(it.next());
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

	public void bombTimeCountDown() {
		Bomb bomb = (Bomb) prop;
		bomb.timeCountDown();
	}

	public void subtractMoney(int price) {
		money = money - price;
	}

	public void addBuilding(Grid grid) {
		lands.put(grid.getId(), grid);
	}

	public void subtractGamePoint(int price) {
		gamePoint = gamePoint - price;
	}

	public void addProp(int i) {
		if (props.containsKey(i)) {
			int n = props.get(i) + 1;
			props.remove(i);
			props.put(i, n);
		} else {
			props.put(i, 1);
		}
	}
}

