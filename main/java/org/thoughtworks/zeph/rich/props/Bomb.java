package org.thoughtworks.zeph.rich.props;

public class Bomb extends Prop {
	private int leftTime;


	public Bomb() {
		this.id = 3;
		this.price = 50;
		this.icon = '@';
		this.leftTime = 20;
	}

	@Override
	public int timeCountDown(int i) {
		return leftTime - i;
	}
}
