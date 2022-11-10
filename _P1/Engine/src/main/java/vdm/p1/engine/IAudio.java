package vdm.p1.engine;

public interface IAudio {

	/**
	 * creates an instance of a ISound
	 * @param filename route
	 * @return the instance of the ISound
	 */
	ISound createSound(String filename);

	/**
	 * plays a sound s
	 */
	void playSound(ISound s);

	/**
	 * stop the sound s
	 */
	void stopSound(ISound s);

}
