package vdm.p1.engine;

public abstract class Engine implements IEngine {
	private IGraphics graphics;
	private IAudio audio;
	private Input input;
	private ILogic logic;
	private IFileManager fileManager;
	private IShareIntent shareIntent;
	private ISensors sensors;
	private INotificationHandler notificationHandler;
	private IAdSystem adSystem;

	/**
	 * @return An {@link ILogic} instance.
	 */
	@Override
	public ILogic getLogic() {
		return logic;
	}

	@Override
	public void setLogic(ILogic logic) {
		this.logic = logic;
	}

	/**
	 * @return An {@link IGraphics} instance.
	 */
	@Override
	public IGraphics getGraphics() {
		return graphics;
	}

	@Override
	public void setGraphics(IGraphics graphics) {
		this.graphics = graphics;
	}

	/**
	 * @return An {@link Input} instance.
	 */
	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public void setInput(Input input) {
		this.input = input;
	}

	/**
	 * @return An {@link IAudio} instance.
	 */
	@Override
	public IAudio getAudio() {
		return audio;
	}

	@Override
	public void setAudio(IAudio audio) {
		this.audio = audio;
	}

	/**
	 * @return An {@link IFileManager} instance.
	 */
	@Override
	public IFileManager getFileManager() {
		return fileManager;
	}

	@Override
	public void setFileManager(IFileManager manager) {
		this.fileManager = manager;
	}

	/**
	 * @return An {@link ISensors} instance
	 */
	@Override
	public ISensors getSensors() {
		return sensors;
	}

	/**
	 * Sets the {@link ISensors} system.
	 *
	 * @param sensors An {@link ISensors} instance.
	 */
	@Override
	public void setSensors(ISensors sensors) {
		this.sensors = sensors;
	}

	/**
	 * @return An {@link IShareIntent} instance.
	 */
	@Override
	public IShareIntent getShareIntent() {
		return shareIntent;
	}

	/**
	 * Sets the {@link IShareIntent} system.
	 *
	 * @param shareIntent An {@link IShareIntent} instance.
	 */
	@Override
	public void setShareIntent(IShareIntent shareIntent) {
		this.shareIntent = shareIntent;
	}

	/**
	 * @return An {@link INotificationHandler} instance
	 */
	@Override
	public INotificationHandler getNotificationHandler() {
		return notificationHandler;
	}

	/**
	 * Sets the {@link INotificationHandler} instance.
	 *
	 * @param notificationHandler An {@link INotificationHandler} instance.
	 */
	@Override
	public void setNotificationHandler(INotificationHandler notificationHandler) {
		this.notificationHandler = notificationHandler;
	}

	/**
	 * @return
	 */
	@Override
	public IAdSystem getAdSystem() {
		return adSystem;
	}

	@Override
	public void setAdSystem(IAdSystem adSystem) {
		this.adSystem = adSystem;
	}
}
