package vdm.p1.logic.scenes;

import java.util.ArrayList;
import java.util.List;

import vdm.p1.engine.HorizontalAlignment;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;
import vdm.p1.engine.IInput;
import vdm.p1.engine.IScene;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public abstract class Scene implements IScene {
	private final IEngine engine;
	private final ArrayList<GameObject> objects = new ArrayList<>();

	public Scene(IEngine engine) {
		this.engine = engine;
	}

	/**
	 * @return The {@link IEngine} that instantiated this scene.
	 */
	@Override
	public IEngine getEngine() {
		return engine;
	}

	public void addGameObject(GameObject object) {
		objects.add(object);
	}

	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	/**
	 * Signal the scene's {@link GameObject}s to render given a {@link IGraphics} engine.
	 *
	 * @param graphics The assigned platform-specific {@link IGraphics} engine.
	 */
	@Override
	public void render(IGraphics graphics) {
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
		List<TouchEvent> events = input.getTouchEvents();
		if (events.isEmpty()) return;

		IGraphics graphics = getEngine().getGraphics();
		for (TouchEvent event : events) {
			event.defineLogicCoordinates(graphics);
			if (!event.isValid()) continue;

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

	protected void addButton(GameObject button, IFont font, String text, int x, int y) {
		final int width = 100;
		final int textOffsetX = 50;
		final int textOffsetY = 25;

		GameObject textComponent = new Text(this, text, font).setPosition(x + textOffsetX, y + textOffsetY);
		addGameObject(button.addChild(textComponent).setPosition(x, y).setSize(width, 40));
	}

	protected void addButton(GameObject button, IImage image, IFont font, String text, int x, int y) {
		final int width = 120;
		final int textOffsetX = 30;
		final int textOffsetY = 26;

		GameObject imageComponent = new Image(this, image).setPosition(x + 5, y + 10).setSize(20, 20);
		GameObject textComponent = new Text(this, text, font, HorizontalAlignment.LEFT).setPosition(x + textOffsetX, y + textOffsetY);
		addGameObject(button.addChild(textComponent).addChild(imageComponent).setPosition(x, y).setSize(width, 40));
	}
}
