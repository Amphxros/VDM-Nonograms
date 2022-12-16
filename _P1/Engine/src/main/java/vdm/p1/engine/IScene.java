package vdm.p1.engine;

public interface IScene {
	/**
	 * An event method called once the scene has been constructed. By default, this will call the
	 * init method on GameObjects.
	 */
	void init();

	/**
	 * Signal the scene's objects to render given a {@link IGraphics} engine.
	 *
	 * @param graphics The assigned platform-specific {@link IGraphics} engine.
	 */
	void render(IGraphics graphics);

	/**
	 * Signal the scene's objects to handle a frame update.
	 *
	 * @param delta The amount of time elapsed since the previous frame.
	 */
	void update(double delta);

	/**
	 * Signal the scene's objects to handle {@link Input}'s events.
	 *
	 * @param input The assigned platform-specific {@link Input} engine.
	 */
	void handleInput(IInput input);

	/**
	 * Called when the scene is destroyed.
	 */
	void dispose();

	/**
	 * Handles when a notification has been closed.
	 */
	void handleClosingNotifications();

	/**
	 * Handles when a notification has been opened.
	 */
	void handleOpeningNotifications();
}
