package org.thoughtworks.zeph.rich.props;

public class Bomb extends Prop {
	private int leftTime;

	public Bomb() {
		super(3,50,'@');
		this.leftTime = 3;
	}

	public void timeCountDown() {
		leftTime = leftTime - 1;
	}

	public int getLeftTime() {
		return leftTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Bomb bomb = (Bomb) o;
		if (leftTime != bomb.leftTime) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + leftTime;
		return result;
	}
}
