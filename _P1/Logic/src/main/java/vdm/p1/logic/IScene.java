package vdm.p1.logic;

import java.util.ArrayList;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.Input;
import vdm.p1.logic.layout.Body;

public interface IScene {
	/**
	 * Retrieves the {@link Body} object which all of a scene's {@link GameObject} are child of.
	 *
	 * @return The {@link Body} object.
	 */
	Body getBody();

	/**
	 * Signal the scene's {@link GameObject}s to render given a {@link IGraphics} engine.
	 *
	 * @param graphics The assigned platform-specific {@link IGraphics} engine.
	 */
	void render(IGraphics graphics);

	/**
	 * Signal the scene's {@link GameObject}s to handle a frame update.
	 *
	 * @param delta The amount of time elapsed since the previous frame.
	 */
	void update(double delta);

	/**
	 * Signal the scene's {@link GameObject}s to handle {@link Input}'s events.
	 *
	 * @param input The assigned platform-specific {@link Input} engine.
	 */
	void handleInput(Input input);

	/**
	 * Called when the scene is destroyed.
	 */
	void dispose();

	/**
	 *
	 */
	void handleClosingNotifications();

	/**
	 *
	 */
	void handleOpeningNotifications();
}
