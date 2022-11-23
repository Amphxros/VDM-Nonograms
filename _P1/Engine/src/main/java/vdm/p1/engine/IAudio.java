package vdm.p1.engine;

public interface IAudio {

	/**
	 * Creates an instance of a ISound
	 *
	 * @param filename The filename to load the sound from.
	 * @return An {@link ISound} instance.
	 */
	ISound createSound(String filename);

	/**
	 * Plays a sound.
	 *
	 * @param s The {@link ISound} to start playing.
	 */
	void playSound(ISound s);

	/**
	 * Stops playing a sound.
	 *
	 * @param s The {@link ISound} to stop playing.
	 */
	void stopSound(ISound s);

}
