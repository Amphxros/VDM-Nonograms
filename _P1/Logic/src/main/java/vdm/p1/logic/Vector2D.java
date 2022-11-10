package vdm.p1.logic;

public final class Vector2D {
	private int x;
	private int y;

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
	 * Sets this instance's X-axis coordinate.
	 *
	 * @param x The new value for the X-axis coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get this instance's Y-axis coordinate.
	 *
	 * @return The Y-axis coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets this instance's Y-axis coordinate.
	 *
	 * @param y The new value for the Y-axis coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Sets this instance's coordinates.
	 *
	 * @param value The {@link Vector2D} to copy.
	 */
	public void set(Vector2D value) {
		set(value.getX(), value.getY());
	}

	/**
	 * Sets this instance's coordinates.
	 *
	 * @param x The new value for the X-axis coordinate.
	 * @param y The new value for the Y-axis coordinate.
	 */
	public void set(int x, int y) {
		setX(x);
		setY(y);
	}
}
