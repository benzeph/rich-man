package org.thoughtworks.zeph.rich.player;

import org.thoughtworks.zeph.rich.god.God;
import org.thoughtworks.zeph.rich.map.unit.Grid;
import org.thoughtworks.zeph.rich.tools.Bomb;
import org.thoughtworks.zeph.rich.tools.Tool;

import java.util.*;

public class Player {
	private int id;
	private String name;
	private int colorNum;
	private int money;
	private char symbol;
	private God god = null;
	private Tool tool = null;
	private int gamePoint = 0;
	private int currentMapPosition = 0;
	private int hospitalDays = 0;
	private int prisonDays = 0;
	private Map<Integer, Integer> tools = new HashMap<Integer, Integer>();
	private Map<Integer, Grid> lands = new HashMap<Integer, Grid>();

	public Player(String name, int id, int colorNum, int money) {
		this.id = id;
		this.name = name;
		this.colorNum = colorNum;
		this.money = money;
		symbol = name.charAt(0);
	}

	public boolean isCarriedWithABomb() {
		if (null == tool) {
			return false;
		} else {
			return tool instanceof Bomb;
		}
	}

	public boolean isInHospital() {
		return hospitalDays > 0;
	}

	public boolean isInPrison() {
		return prisonDays > 0;
	}

	public boolean isBombExplode() {
		Bomb bomb = (Bomb) tool;
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
		return tools.containsKey(1);
	}

	public boolean isPlayerHasARobot() {
		return tools.containsKey(2);
	}

	public boolean isPlayerHasABomb() {
		return tools.containsKey(3);
	}

	public boolean isPlayerHasBuilding(int id) {
		return lands.containsKey(id);
	}

	public boolean isYourToolBagFull() {
		int num = 0;
		Set<Integer> set = tools.keySet();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			num = num + tools.get(it.next());
		}
		return num >= 10;
	}

	public int getPrisonDays() {
		return prisonDays;
	}

	public int getHospitalDays() {
		return hospitalDays;
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


	public void setPrisonDays(int prisonDays) {
		this.prisonDays = prisonDays;
	}

	public int getCurrentMapPosition() {
		return currentMapPosition;
	}

	public void setCurrentMapPosition(int currentMapPosition) {
		this.currentMapPosition = currentMapPosition;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void countDownPrisonDays() {
		prisonDays = prisonDays - 1;
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

	public void useTool(Tool tool) {
		int n = tools.get(tool.getId()) - 1;
		if (n == 0) {
			tools.remove(tool.getId());
		} else {
			tools.remove(tool.getId());
			tools.put(tool.getId(), n);
		}
	}

	public void sellTool(Tool tool) {
		if (tools.containsKey(tool.getId())) {
			int n = tools.get(tool.getId()) - 1;
			if (n == 0) {
				tools.remove(tool.getId());
			} else {
				tools.remove(tool.getId());
				tools.put(tool.getId(), n);
			}
			gamePoint = gamePoint + tool.getPrice();
		}
	}


	public String query() {
		String message = "\n资金:" + money + "\n";
		message += "点数:" + gamePoint + "\n";
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
		message += "地产:" + "空地" + blankLand + "处;" + "茅房" + room + "处;" + "洋房" + house + "处;" + "摩天楼" + skyscraper + "处\n";
		message += "道具:" + "障碍" + tools.get(1) + "个;炸弹" + tools.get(3) + ";机器娃娃" + tools.get(2) + "\n";
		return message;
	}

	public int dice() {
		return new Random().nextInt(6) + 1;
	}

	public void bombTimeCountDown() {
		Bomb bomb = (Bomb) tool;
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
		if (tools.containsKey(i)) {
			int n = tools.get(i) + 1;
			tools.remove(i);
			tools.put(i, n);
		} else {
			tools.put(i, 1);
		}
	}
}

