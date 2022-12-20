package vdm.p1.engine;

import java.util.ArrayList;

public interface IEngine {
	/**
	 * @return An {@link IGraphics} instance.
	 */
	IGraphics getGraphics();

	/**
	 * Sets the {@link IGraphics} engine.
	 *
	 * @param graphics An {@link IGraphics} instance.
	 */
	void setGraphics(IGraphics graphics);

	/**
	 * @return An {@link Input} instance.
	 */
	Input getInput();

	/**
	 * Sets the {@link Input} engine.
	 *
	 * @param input An {@link Input} instance.
	 */
	void setInput(Input input);

	/**
	 * @return An {@link ILogic} instance.
	 */
	ILogic getLogic();

	/**
	 * Sets the {@link ILogic} engine.
	 *
	 * @param logic An {@link ILogic} instance.
	 */
	void setLogic(ILogic logic);

	/**
	 * @return An {@link IAudio} instance.
	 */
	IAudio getAudio();

	/**
	 * Sets the {@link IAudio} engine.
	 *
	 * @param audio An {@link IAudio} instance.
	 */
	void setAudio(IAudio audio);

	/**
	 * @return An {@link IShareIntent} instance.
	 */
	IShareIntent getShareIntent();

	/**
	 * Sets the {@link IShareIntent} system.
	 *
	 * @param shareIntent An {@link IShareIntent} instance.
	 */
	void setShareIntent(IShareIntent shareIntent);

	/**
	 * @return An {@link IFileManager} instance.
	 */
	IFileManager getFileManager();

	/**
	 * Sets the {@link IFileManager} engine.
	 *
	 * @param manager An {@link IFileManager} instance.
	 */
	void setFileManager(IFileManager manager);

	/**
	 * @return An {@link ISensors} instance
	 */
	ISensors getSensors();

	/**
	 * Sets the {@link ISensors} system.
	 *
	 * @param sensors An {@link ISensors} instance.
	 */
	void setSensors(ISensors sensors);

	/**
	 * @return An {@link INotificationHandler} instance
	 */
	INotificationHandler getNotificationHandler();

	/**
	 * Sets the {@link INotificationHandler} instance.
	 *
	 * @param notificationHandler An {@link INotificationHandler} instance.
	 */
	void setNotificationHandler(INotificationHandler notificationHandler);

	/**
	 *
	 * @return the {@link IAdSystem} instance
	 */
	IAdSystem getAdSystem();

	/**
	 * Sets the {@link IAdSystem} instance
	 * @param adSystem instance to add
	 */
	void setAdSystem(IAdSystem adSystem);

	/**
	 * @return The application's window width.
	 */
	int getWidth();

	/**
	 * @return The application's window height.
	 */
	int getHeight();
}
