package vdm.p1.logic.scenes;

import java.util.ArrayList;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IInput;
import vdm.p1.engine.IScene;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.Vector2D;

public abstract class Scene implements IScene {
	private final IEngine engine;
	private final ArrayList<GameObject> objects = new ArrayList<>();
	private Vector2D pointer = null;

	public Scene(IEngine engine) {
		this.engine = engine;
	}

	public IEngine getEngine() {
		return engine;
	}

	public void addGameObject(GameObject object) {
		objects.add(object);
	}

	/**
	 * Signal the scene's {@link GameObject}s to render given a {@link IGraphics} engine.
	 *
	 * @param graphics The assigned platform-specific {@link IGraphics} engine.
	 */
	@Override
	public void render(IGraphics graphics) {
		if (pointer != null) {
			int x = pointer.getX();
			int y = pointer.getY();

			graphics.setColor(new Color(0, 255, 0));
			graphics.drawLine(0, y, graphics.getWidth(), y);
			graphics.drawLine(x, 0, x, graphics.getHeight());

			x = graphics.getScenePointX(x);
			y = graphics.getScenePointY(y);
			if (x != -1 && y != -1) {
				graphics.setColor(new Color(255, 0, 0));
				graphics.drawLine(0, y, graphics.getWidth(), y);
				graphics.drawLine(x, 0, x, graphics.getHeight());
			}
		}

		for (GameObject object : objects) {
			if (object.isEnabled()) object.render(graphics);
		}
	}

	/**
	 * Signal the scene's {@link GameObject}s to handle a frame update.
	 *
	 * @param delta The amount of time elapsed since the previous frame.
	 */
	@Override
	public void update(double delta) {
		for (GameObject object : objects) {
			if (object.isEnabled()) object.update(delta);
		}
	}

	/**
	 * Signal the scene's {@link GameObject}s to handle {@link Input}'s events.
	 *
	 * @param input The assigned platform-specific {@link Input} engine.
	 */
	@Override
	public void handleInput(IInput input) {
		for (TouchEvent event : input.getTouchEvents()) {
			pointer = new Vector2D(event.getX(), event.getY());

			for (GameObject object : objects) {
				if (object.isEnabled()) object.handleInput(event);
			}
		}
	}

	/**
	 * An event method called once the scene has been constructed. By default, this will call the
	 * awake method on GameObjects.
	 */
	@Override
	public void init() {
		for (GameObject object : objects) {
			object.init();
		}
	}

	/**
	 * Called when the scene is destroyed.
	 */
	@Override
	public void dispose() {
	}

	/**
	 * Handles when a notification has been closed.
	 */
	@Override
	public void handleClosingNotifications() {
	}

	/**
	 * Handles when a notification has been opened.
	 */
	@Override
	public void handleOpeningNotifications() {
	}
}
