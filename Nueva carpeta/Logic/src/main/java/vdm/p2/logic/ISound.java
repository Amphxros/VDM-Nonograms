package vdm.p2.logic;

public interface ISound {
	/**
	 * Plays the sound.
	 */
	void play();

	/**
	 * Stops playing the sound.
	 */
	void stop();

	/**
	 * Sets the sound in a loop.
	 */
	void setLoop();
}
