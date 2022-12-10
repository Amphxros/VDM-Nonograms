package vdm.p1.engine;

public abstract class Engine implements IEngine {
	private IGraphics graphics;
	private IAudio audio;
	private Input input;
	private ILogic logic;
	private IFileManager fileManager;

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

}
