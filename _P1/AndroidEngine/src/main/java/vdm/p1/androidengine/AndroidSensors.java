package vdm.p1.androidengine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import java.util.ArrayList;

import vdm.p1.engine.ISensors;
import vdm.p1.engine.ShakeListener;

public final class AndroidSensors implements ISensors, SensorEventListener {
	/**
	 * The acceleration in m/s² for an acceleration to be considered a shake.
	 */
	private static final float SHAKE_THRESHOLD = 10.25f;
	/**
	 * The minimum amount of time between shakes, in milliseconds.
	 */
	private static final long SHAKE_PERIOD = 1000;

	private final SensorManager sensorManager;
	private Sensor sensor;
	private long lastShakeTime = System.currentTimeMillis();
	private final ArrayList<ShakeListener> shakeListeners = new ArrayList<>();

	public AndroidSensors(Context context) {
		super();

		this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager == null) {
			System.out.println("Sensor service not detected");
			Toast.makeText(context, "Sensor service not detected", Toast.LENGTH_SHORT).show();
		} else {
			register();
		}
	}

	/**
	 * Registers the sensor.
	 *
	 * @return Whether or not the operation was successful.
	 */
	@Override
	public boolean register() {
		if (sensorManager == null) {
			System.err.println("Could not detect a sensor service to register into");
			return false;
		}

		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if (sensor == null) {
			System.err.println("Could not get the accelerometer sensor");
			return false;
		}

		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		return true;
	}

	/**
	 * Unregisters the sensor.
	 *
	 * @return Whether or not the operation was successful.
	 */
	@Override
	public boolean unregister() {
		sensorManager.unregisterListener(this, sensor);
		return true;
	}

	/**
	 * Registers a shake listener.
	 *
	 * @param listener A {@link ShakeListener} that will be called when the sensors detect a shake.
	 */
	@Override
	public void registerListener(ShakeListener listener) {
		shakeListeners.add(listener);
	}

	/**
	 * Unregisters a shake listener.
	 *
	 * @param listener A {@link ShakeListener} that will not longer be called when the sensors detect a shake.
	 */
	@Override
	public void unregisterListener(ShakeListener listener) {
		shakeListeners.remove(listener);
	}

	/**
	 * @param event event happened in any of our sensors
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
			return;
		}

		long currentTime = System.currentTimeMillis();
		if (currentTime - lastShakeTime < SHAKE_PERIOD) return;

		//casts the event in the Accelerometer one
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];

		double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
		if (acceleration > SHAKE_THRESHOLD) {
			for (ShakeListener shakeListener: shakeListeners) {
				shakeListener.onShake(acceleration);
			}
			lastShakeTime = currentTime;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {
	}
}
