package org.thoughtworks.zeph.rich.output;

public class SystemOut {
	private static final int WHITE = 7;

	public static void pleaseChoosePlayers() {
		colorPrintLn("请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):", WHITE);
	}
	public static void pleaseSetInitialMoney(){
		colorPrintLn("设置玩家初始资金，范围1000～50000（默认10000）",WHITE);
	}
	public static void waitForInstruction(String name) {
		colorPrintLn(name + ">待输入命令", WHITE);
		colorPrintLn("命令：", WHITE);
	}

	public static void doYouWantToBuyThisLand(int price) {
		colorPrintLn("是否购买该处空地，" + price + "元（Y/N）?", WHITE);
	}

	public static void doYouWantToLevelUpThisLand(int price) {
		colorPrintLn("是否升级该处地产，" + price + "元（Y/N）?", WHITE);
	}

	public static void welcomeToToolRoom() {
		colorPrintLn("欢迎光临道具屋，请选择您所需要的道具：", WHITE);
		colorPrintLn("道具\t\t编号\t价值（点数）\t显示方式", WHITE);
		colorPrintLn("路障\t\t1\t50\t\t\t#", WHITE);
		colorPrintLn("机器娃娃\t2\t30\t", WHITE);
		colorPrintLn("炸弹\t\t3\t50\t\t\t@", WHITE);
	}

	public static void welcomeToGiftRoom() {
		colorPrintLn("欢迎光临礼品屋，请选择一件您喜欢的礼品：", WHITE);
		colorPrintLn("礼品\t\t编号", WHITE);
		colorPrintLn("奖金\t\t1", WHITE);
		colorPrintLn("点数卡\t2", WHITE);
		colorPrintLn("福神\t\t3", WHITE);
	}

	public static void youHaveExitToolRoom() {
		colorPrintLn("你的点数不足或者道具栏已满，自动离开道具屋", WHITE);
	}

	public static void gameOver() {
		colorPrintLn("游戏结束", WHITE);
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
