package vdm.p1.engine;

public interface IEngine {
	/**
	 * @return the instance of graphics
	 */
	IGraphics getGraphics();

	/**
	 * sets the instance of graphics
	 */
	void setGraphics(IGraphics g);

	/**
	 * @return the instance of input
	 */
	Input getInput();

	/**
	 * set the instance of input
	 */
	void setInput(Input input);

	/**
	 * @return the instance of logic
	 */
	ILogic getLogic();

	/**
	 * set the instance of logic
	 */
	void setLogic(ILogic logic);

	/**
	 * @return the instance of audio
	 */
	IAudio getAudio();

	/**
	 * set the instance of audio
	 */
	void setAudio(IAudio audio);
	/**
	 * @return the width of the window
	 */
	int getWidth();
	/**
	 * @return the height of the window
	 */
	int getHeight();
}
