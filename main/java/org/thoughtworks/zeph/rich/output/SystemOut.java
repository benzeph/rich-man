package org.thoughtworks.zeph.rich.output;

public class SystemOut {
	private static final int WHITE = 7;

	public static void waitForInstruction(String name) {
		colorPrint(name + ">待输入命令", WHITE);
		colorPrint("命令：", WHITE);
	}

	public static void doYouWantToBuyThisLand(int price) {
		colorPrint("是否购买该处空地，" + price + "元（Y/N）?", WHITE);
	}

	public static void doYouWantToLevelUpThisLand(int price) {
		colorPrint("是否升级该处地产，" + price + "元（Y/N）?", WHITE);
	}

	public static void welcomeToToolRoom() {
		colorPrint("欢迎光临道具屋，请选择您所需要的道具：", WHITE);
		colorPrint("道具\t\t编号\t价值（点数）\t显示方式", WHITE);
		colorPrint("路障\t\t1\t50\t\t\t#", WHITE);
		colorPrint("机器娃娃\t2\t30\t", WHITE);
		colorPrint("炸弹\t\t3\t50\t\t\t@", WHITE);
	}

	public static void welcomeToGiftRoom() {
		colorPrint("欢迎光临礼品屋，请选择一件您喜欢的礼品：", WHITE);
		colorPrint("礼品\t\t编号", WHITE);
		colorPrint("奖金\t\t1", WHITE);
		colorPrint("点数卡\t2", WHITE);
		colorPrint("福神\t\t3", WHITE);
	}

	public static void youHaveExitToolRoom() {
		colorPrint("你的点数不足或者道具栏已满，自动离开道具屋", WHITE);
	}

	public static void gameOver() {
		colorPrint("游戏结束", WHITE);
	}

	public static void colorPrint(String str, int color) {
		ColorSystemOut.print(str, color);
	}

	public static void colorPrint(char ch, int color) {
		ColorSystemOut.print(ch, color);
	}

	public static void colorPrintLn(String str, int color) {
		ColorSystemOut.println(str, color);
	}
}
