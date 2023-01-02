package vdm.p1.logic;

public final class Vector2D {
	private final int x;
	private final int y;

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get this instance's X-axis coordinate.
	 *
	 * @return The X-axis coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get this instance's Y-axis coordinate.
	 *
	 * @return The Y-axis coordinate.
	 */
	public int getY() {
		return y;
	}
}
