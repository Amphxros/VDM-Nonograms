package vdm.p1.engine;

public abstract class Engine implements IEngine {
	private IGraphics graphics;
	private IAudio audio;
	private Input input;
	private ILogic logic;

	private IFileManager fileManager;
	private INotificationManager notificationManager;
	private IShareIntent shareIntent;

	private ISensors sensors;

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
	 * @return an instance of {@link IFileManager} instance
	 */
	@Override
	public IFileManager getFileManager() {
		return fileManager;
	}

	@Override
	public void setFileManager(IFileManager manager) {
		this.fileManager = manager;
	}

	@Override
	public ISensors getSensors() {
		return sensors;
	}

	@Override
	public void setSensors(ISensors sensors) {
		this.sensors=sensors;
	}

	/**
	 * @return An {@link IShareIntent} instance
	 */
	@Override
	public IShareIntent getShareIntent() {
		return shareIntent;
	}

	@Override
	public void setShareIntent(IShareIntent shareIntent) {
		this.shareIntent = shareIntent;
	}

	@Override
	public INotificationManager getNotificationManager() {
		return notificationManager;
	}

	@Override
	public void setNotificationManager(INotificationManager notificationManager) {
		this.notificationManager = notificationManager;
	}
}
