package org.thoughtworks.zeph.rich.output;

public class Printer {

	public static void pleaseChoosePlayers() {
		colorPrintLn("请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):", Color.WHITE);
	}

	public static void pleaseSetInitialMoney() {
		colorPrintLn("设置玩家初始资金，范围1000～50000（默认10000）", Color.WHITE);
	}

	public static void waitForInstruction(String name) {
		colorPrintLn(name + ">待输入命令", Color.WHITE);
		colorPrint("命令：", Color.WHITE);
	}

	public static void doYouWantToBuyThisLand(int price) {
		colorPrintLn("是否购买该处空地，" + price + "元（Y/N）?", Color.WHITE);
	}

	public static void doYouWantToLevelUpThisLand(int price) {
		colorPrintLn("是否升级该处地产，" + price + "元（Y/N）?", Color.WHITE);
	}

	public static void welcomeToToolRoom() {
		colorPrintLn("欢迎光临道具屋，请选择您所需要的道具：", Color.WHITE);
		colorPrintLn("道具\t\t编号\t价值（点数）\t显示方式", Color.WHITE);
		colorPrintLn("路障\t\t1\t50\t\t\t#", Color.WHITE);
		colorPrintLn("机器娃娃\t2\t30\t", Color.WHITE);
		colorPrintLn("炸弹\t\t3\t50\t\t\t@", Color.WHITE);
	}

	public static void welcomeToGiftRoom() {
		colorPrintLn("欢迎光临礼品屋，请选择一件您喜欢的礼品：", Color.WHITE);
		colorPrintLn("礼品\t\t编号", Color.WHITE);
		colorPrintLn("奖金\t\t1", Color.WHITE);
		colorPrintLn("点数卡\t2", Color.WHITE);
		colorPrintLn("福神\t\t3", Color.WHITE);
	}

	public static void youHaveExitToolRoom() {
		colorPrintLn("你的点数不足或者道具栏已满，自动离开道具屋", Color.WHITE);
	}

	public static void blockFailed() {
		colorPrintLn("放置障碍失败，位置被占据", Color.WHITE);
	}

	public static void bombFailed() {
		colorPrintLn("放置炸弹失败，位置被占据", Color.WHITE);
	}

	public static void youDoNotHaveARobot() {
		colorPrintLn("你没有机器娃娃可用", Color.WHITE);
	}

	public static void youDoNotHaveABlock() {
		colorPrintLn("你没有障碍可用", Color.WHITE);
	}

	public static void youDoNotHaveABomb() {
		colorPrintLn("你没有炸弹可用", Color.WHITE);
	}

	public static void bombExplode() {
		colorPrintLn("你身上的炸弹爆炸了", Color.WHITE);
	}

	public static void youMeetABlock() {
		colorPrintLn("你遇到了一个障碍", Color.WHITE);
	}

	public static void youMeetABomb() {
		colorPrintLn("你遇到了一个炸弹", Color.WHITE);
	}

	public static void sellFail() {
		colorPrintLn("你没有这块地", Color.WHITE);
	}

	public static void sellToolFailed(String name) {
		colorPrintLn("你没有" + name + "可以卖", Color.WHITE);
	}


	public static void gameOver() {
		colorPrintLn("游戏结束", Color.WHITE);
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

	public static void illegalInstruction() {
		colorPrintLn("你输入的命令不存在，请重新输入（输入help-查看帮助）", Color.WHITE);
	}

	public static void paySomeoneMoney(String ownerName, int money) {
		colorPrintLn("支付给玩家" + ownerName + money + "元过路费", Color.WHITE);
	}

	public static void printGetGamePoint(int gamePoint) {
		colorPrintLn("得到游戏点" + gamePoint, Color.WHITE);
	}

	public static void youReleaseAllPrisoner() {
		colorPrintLn("你释放了所有的囚犯", Color.WHITE);
	}

	public static void youReleaseAllPatient() {
		colorPrintLn("你释放了所有的病人", Color.WHITE);
	}

	public static void hospitalLeftTime(String name, int hospitalDays) {
		colorPrintLn(name + "在医院休息还剩下" + hospitalDays + "天", Color.WHITE);
	}

	public static void prisonLeftTime(String name, int prisonDays) {
		colorPrintLn(name + "在监狱里关着还剩下" + prisonDays + "天", Color.WHITE);
	}

	public static void youGetGift(String str) {
		colorPrintLn("你得到了礼物：" + str, Color.WHITE);
	}

	public static void godBlessYou() {
		colorPrintLn("财神保佑你，免交过路费", Color.WHITE);
	}

	public static void ownerIsInTheHosptial(String name) {
		colorPrintLn(name + "在医院，免交过路费", Color.WHITE);
	}

	public static void ownerIsInThePrison(String name) {
		colorPrintLn(name + "在监狱，免交过路费", Color.WHITE);
	}

	public static void yourMoneyIsNotEnough() {
		colorPrintLn("你的现金不够", Color.WHITE);
	}

	public static void dice(int step) {
		colorPrintLn("走" + step + "步", Color.WHITE);
	}
}
