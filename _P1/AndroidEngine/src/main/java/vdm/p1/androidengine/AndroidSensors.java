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
	private Sensor gyro;

	private boolean isShaking=false;
	private float time;
	private static final int SHAKE_THRESHOLD = 20;

	public AndroidSensors(Context context){
		super();

		this.sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.unregisterListener(this);
		if(sensorManager==null){
			System.out.println("Sensor service not detected");
			Toast.makeText(context, "Sensor service not detected", Toast.LENGTH_SHORT).show();
		}
		else{
			register();
		}


	}

	@Override
	public void update(float t) {
		this.time+=t;
	}

	@Override
	public boolean isShaking() {
		return isShaking;
	}

	@Override
	public void shaked() {
		isShaking=false;
	}

	/**
	 * registers the accelerometer sensor
	 */
	@Override
	public void register() {

		accel= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		if(accel!=null && sensorManager!=null){
			try{
				sensorManager.registerListener(this,accel,SensorManager.SENSOR_DELAY_NORMAL);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * unregister the accelerometer sensor
	 */
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
			long curTime = System.currentTimeMillis();
			float diff= curTime - time;
				time=curTime;

				float x= sensorEvent.values[0];
				float y= sensorEvent.values[1];
				float z= sensorEvent.values[2];
				float speed= Math.abs(x+y+z-getX() -getY()-getZ())/1;
				System.out.println("speed: " + speed);
				if(speed>SHAKE_THRESHOLD){
						isShaking=true;
						setX(x);
						setY(y);
						setZ(z);
					}

			}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int i) {

	}
}
