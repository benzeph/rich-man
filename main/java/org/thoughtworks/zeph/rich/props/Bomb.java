package org.thoughtworks.zeph.rich.props;

public class Bomb extends Prop {
	private int leftTime;


	public Bomb() {
		this.id = 3;
		this.price = 50;
		this.icon = '@';
		this.leftTime = 1;
	}

	public void timeCountDown(int i) {
		leftTime = leftTime - i;
	}

	public int getLeftTime() {
		return leftTime;
	}
}
