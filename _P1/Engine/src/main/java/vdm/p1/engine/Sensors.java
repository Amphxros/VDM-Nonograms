package vdm.p1.engine;

public abstract class Sensors implements ISensors {
	private float x,y,z;

	public Sensors(){
		this.x=0;
		this.y=0;
		this.z=0;
	}
	@Override
	public float getX() {
		return x;
	}

	@Override
	public void setX(float x) {
		this.x=x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setY(float y) {
		this.y=y;
	}

	@Override
	public float getZ() {
		return z;
	}

	@Override
	public void setZ(float z) {
		this.z=z;
	}

}
