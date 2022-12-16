package vdm.p1.logic;

import java.util.Vector;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public abstract class GameObject {
	private final Vector<GameObject> children = new Vector<>();
	private Vector2D position;
	private Vector2D size;
	private boolean enabled = true;
	private Color strokeColor = null;
	private GameObject parent = null;

	public GameObject() {
		this(0, 0, 0, 0);
	}

	public GameObject(int x, int y, int width, int height) {
		this.position = new Vector2D(x, y);
		this.size = new Vector2D(width, height);
	}

	public Vector2D getPosition() {
		return position;
	}

	public GameObject setPosition(Vector2D position) {
		this.position = position;
		return this;
	}

	public GameObject setPosition(int x, int y) {
		setPosition(new Vector2D(x, y));
		return this;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public Vector2D getSize() {
		return size;
	}

	public GameObject setSize(Vector2D size) {
		this.size = size;
		return this;
	}

	public GameObject setSize(int width, int height) {
		return setSize(new Vector2D(width, height));
	}

	public int getWidth() {
		return getSize().getX();
	}

	public int getHeight() {
		return getSize().getY();
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public GameObject getParent() {
		return parent;
	}

	public Vector<GameObject> getChildren() {
		return children;
	}

	public GameObject setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
		return this;
	}

	/**
	 * Adds a child to the GameObject.
	 *
	 * @param gameObject The object to add as a child.
	 * @return The updated {@link GameObject} instance.
	 * @throws RuntimeException When the given object already has a parent.
	 */
	public GameObject addChild(GameObject gameObject) {
		if (gameObject.parent != null) {
			throw new RuntimeException("The given GameObject already has a parent");
		}

		gameObject.parent = this;
		children.add(gameObject);
		return this;
	}

	/**
	 * An event method that's called once on initialization.
	 */
	public void init() {
		for (GameObject child : getChildren()) {
			child.init();
		}
	}

	public void render(IGraphics graphics) {
		if (strokeColor != null) {
			graphics.setColor(strokeColor);
			graphics.drawRectangle(getX(), getY(), getWidth(), getHeight());
		}

		for (GameObject child : getChildren()) {
			if (child.isEnabled()) {
				child.render(graphics);
			}
		}
	}

	/**
	 * An event method that's called on each frame.
	 *
	 * @param delta The number of seconds since the last frame.
	 */
	public void update(double delta) {
		for (GameObject child : getChildren()) {
			if (child.isEnabled()) {
				child.update(delta);
			}
		}
	}

	/**
	 * An event method that's called when the device receives an event.
	 *
	 * @param event The received event from the device.
	 * @return Whether or not the input has been processed.
	 */
	public boolean handleInput(TouchEvent event) {
		for (GameObject child : getChildren()) {
			if (child.isEnabled() && child.handleInput(event)) {
				return true;
			}
		}

		return false;
	}
}
