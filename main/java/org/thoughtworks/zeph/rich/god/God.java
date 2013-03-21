package org.thoughtworks.zeph.rich.god;


public class God {

	private int leftTime;

	public God(int leftTime) {
		this.leftTime = leftTime;
	}

	public void timeCountDown() {
		leftTime = leftTime - 1;
	}

	public int getLeftTime() {
		return leftTime;
	}
}
