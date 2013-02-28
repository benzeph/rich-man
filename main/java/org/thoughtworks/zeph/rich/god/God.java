package org.thoughtworks.zeph.rich.god;


public class God {

	private int leftTime;

	public God() {
		leftTime = 5;
	}

	public void timeCountDown() {
		leftTime = leftTime - 1;
	}

	public int getLeftTime() {
		return leftTime;
	}
}
