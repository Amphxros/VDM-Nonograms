package vdm.p1.engine;

public interface ISensors {
	/**
	 * Registers the sensor.
	 *
	 * @return Whether or not the operation was successful.
	 */
	boolean register();

	/**
	 * Unregisters the sensor.
	 *
	 * @return Whether or not the operation was successful.
	 */
	boolean unregister();

	/**
	 * Registers a shake listener.
	 *
	 * @param listener A {@link ShakeListener} that will be called when the sensors detect a shake.
	 */
	void registerListener(ShakeListener listener);

	/**
	 * Unregisters a shake listener.
	 *
	 * @param listener A {@link ShakeListener} that will not longer be called when the sensors detect a shake.
	 */
	void unregisterListener(ShakeListener listener);
}
