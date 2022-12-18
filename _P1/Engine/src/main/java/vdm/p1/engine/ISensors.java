package vdm.p1.engine;

public interface ISensors {

	/**
	 * updates the time to delay certain sensors events
	 * example: detecting shaking movement using accelerometers
	 */
	void update(float t);
	/**
	 * @return the rotation on x axis
	 */
	public float getX();
	public void setX(float x);

	/**
	 * @return the rotation on y axis
	 */
	public float getY();
	public void setY(float y);
	/**
	 * @return the rotation on z axis
	 */
	public float getZ();
	public void setZ(float z);

	public boolean isShaking();
	void register();
	void unregister();
}
