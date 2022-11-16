package vdm.p2.logic;

public class TouchEvent {
	/**
	 * The coordinates the touch event happened at.
	 */
	private final int x, y;
	private final EventType type; //type of event

	public TouchEvent(int x, int y, EventType event) {
		this.x = x;
		this.y = y;
		this.type = event;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public EventType getType() {
		return type;
	}
}
