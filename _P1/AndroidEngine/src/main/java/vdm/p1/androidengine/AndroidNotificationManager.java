package vdm.p1.androidengine;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.INotificationManager;
import vdm.p1.engine.Notification;

public class AndroidNotificationManager implements INotificationManager {
	private Context context;
	public AndroidNotificationManager(Context context){
		this.context=context;
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
			CharSequence name= "Notification";
			String channel_tittle="My Notification";
			int importance= NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel= new NotificationChannel(channel_tittle,name,importance);
			String desc= "desc";
			channel.setDescription(desc);

			NotificationManager notMngr= context.getSystemService(NotificationManager.class);
			notMngr.createNotificationChannel(channel);
		}

	}

	@Override
	public IEngine getEngine() {
		return null;
	}

	@Override
	public void setEngine(IEngine engine) {

	}
}
