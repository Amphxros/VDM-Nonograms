package vdm.p1.engine;

public final class TouchEvent {
	/**
	 * The coordinates the touch event happened at.
	 */
	private final int windowX, windowY;
	private final EventType type; //type of event
	private int x = -1, y = -1;

	public TouchEvent(int windowX, int windowY, EventType event) {
		this.windowX = windowX;
		this.windowY = windowY;
		this.type = event;
	}

	/**
	 * The X-axis position in the logic, will be -1 if invalid or unset. See {@link #isValid()}.
	 *
	 * @return The logic X-axis position the event happened at.
	 */
	public int getX() {
		return x;
	}

	/**
	 * The Y-axis position in the logic, will be -1 if invalid or unset. See {@link #isValid()}.
	 *
	 * @return The logic Y-axis position the event happened at.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Checks whether or not the event happened at a valid set of coordinates.
	 *
	 * @return Whether or not the event happened at a valid set of coordinates.
	 */
	public boolean isValid() {
		return x != -1 && y != -1;
	}

	/**
	 * Returns the absolute position in the X-axis where the event happened at, it will always be
	 * defined.
	 *
	 * @return The absolute position in the X-axis where the event happened at.
	 */
	public int getWindowX() {
		return windowX;
	}

	/**
	 * Returns the absolute position in the Y-axis where the event happened at, it will always be
	 * defined.
	 *
	 * @return The absolute position in the Y-axis where the event happened at.
	 */
	public int getWindowY() {
		return windowY;
	}

	/**
	 * Sets the logic coordinates, sets the values that {@link #getX()} and {@link #getY()} return.
	 * Ideally, this should be called before consuming the events in the game's objects. The new
	 * values will be the result of calling {@link IGraphics#getLogicPointX(int)} with
	 * {@link #getWindowX()}, and {@link IGraphics#getLogicPointY(int)} with {@link #getWindowY()},
	 * respectively.
	 *
	 * @param graphics The graphics engine to read the values from.
	 */
	public void defineLogicCoordinates(IGraphics graphics) {
		this.x = graphics.getLogicPointX(getWindowX());
		this.y = graphics.getLogicPointY(getWindowY());
	}

	public EventType getType() {
		return type;
	}
}
