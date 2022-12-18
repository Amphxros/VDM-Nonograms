package vdm.p1.engine;

public interface ISensors {
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
	public void resetShaking();
	void register();
	void unregister();
}
