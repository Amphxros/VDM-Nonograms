package vdm.p1.engine;

public abstract class Engine implements IEngine {
	private IGraphics graphics;
	private IAudio audio;
	private Input input;
	private ILogic logic;

	/**
	 * @return instance of the logic
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
	 * @return instance of graphics
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
	 * @return instance of inputhandler
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
	 * @return instance of audiomanager
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
