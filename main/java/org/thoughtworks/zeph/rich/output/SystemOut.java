package org.thoughtworks.zeph.rich.output;

public class SystemOut {
	public static void waitForInstruction(String name) {
		System.out.println(name + ">待输入命令");
		System.out.print("命令：");
	}

	public static void doYouWantToBuyThisLand(int price) {
		System.out.println("是否购买该处空地，" + price + "元（Y/N）?");
	}

	public static void doYouWantToLevelUpThisLand(int price) {
		System.out.println("是否升级该处地产，" + price + "元（Y/N）?");
	}

	public static void welcomeToToolRoom() {
		System.out.println("欢迎光临道具屋，请选择您所需要的道具：");
		System.out.println("道具\t\t编号\t价值（点数）\t显示方式");
		System.out.println("路障\t\t1\t50\t\t\t#");
		System.out.println("机器娃娃\t2\t30\t");
		System.out.println("炸弹\t\t3\t50\t\t\t@");
	}

	public static void welcomeToGiftRoom() {
		System.out.println("欢迎光临礼品屋，请选择一件您喜欢的礼品：");
		System.out.println("礼品\t\t编号");
		System.out.println("奖金\t\t1");
		System.out.println("点数卡\t2");
		System.out.println("福神\t\t3");
	}

	public static void youHaveExitToolRoom(){
		System.out.println("你的点数不足或者道具栏已满，自动离开道具屋");
	}
}
