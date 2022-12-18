package vdm.p1.androidengine;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import vdm.p1.engine.Sensors;

public class AndroidSensors extends Sensors implements SensorEventListener {

	public AndroidSensors(){
		super();

	}

	@Override
	public boolean isAgitated() {
		return false;
	}

	@Override
	public void register() {

	}

	@Override
	public void unregister() {

	}


	/**public void onSensorChanged(int sensor, float[] values) {
		System.out.println(sensor);
		if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
			long curTime = System.currentTimeMillis();
			// only allow one update every 100ms.
			if ((curTime - lastUpdate) > 100) {
				long diffTime = (curTime - lastUpdate);
				lastUpdate = curTime;

				setX(values[SensorManager.AXIS_X]);
				setY(values[SensorManager.AXIS_Y]);
				setZ(values[SensorManager.AXIS_Z]);

				float speed = Math.abs(getX()+getY()+getZ() - last_x - last_y - last_z) / diffTime * 10000;

				if (speed > SHAKE_THRESHOLD) {
					Log.d("sensor", "shake detected w/ speed: " + speed);
					Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
				}
				last_x = x;
				last_y = y;
				last_z = z;
	 		}
		 */


	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {

	}
}
