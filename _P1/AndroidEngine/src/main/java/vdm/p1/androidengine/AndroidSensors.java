package vdm.p1.androidengine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import vdm.p1.engine.Sensors;

public class AndroidSensors extends Sensors implements SensorEventListener {
	private final SensorManager sensorManager;

	private Sensor accel;
	private float acceleration;
	private float currentAcceleration;
	private float lastAcceleration;

	private boolean isShaking=false;

	private static final int SHAKE_THRESHOLD = 800;

	public AndroidSensors(Context context){
		super();
		this.sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		if(sensorManager==null){
			Toast.makeText(context, "Sensor service not detected", Toast.LENGTH_SHORT).show();
		}
		else{
			register();
		}
		this.acceleration=0f;
		this.currentAcceleration=0f;
		this.lastAcceleration=0f;
	}

	@Override
	public boolean isShaking() {
		return isShaking;
	}

	@Override
	public void register() {
		accel= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if(accel!=null)
			sensorManager.registerListener(this,accel,SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void unregister() {
		sensorManager.unregisterListener(this,accel);
	}

	/**
	 *
	 * @param sensorEvent event happened in any of our sensors
	 */
	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		//casts the event in the Accelerometer one
		if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){


		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {

	}
}
