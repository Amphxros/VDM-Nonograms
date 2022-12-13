package vdm.p1.androidengine;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import vdm.p1.engine.INotificationManager;

public class AndroidNotificationManager implements INotificationManager {
	private final Context context;

	public AndroidNotificationManager(Context context) {
		this.context = context;
		configureNotificationManager();
	}

	@Override
	public void configureNotificationManager() {
		CharSequence channelName = "Notification";
		String channelTitle = "My Notification";
		int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
		NotificationChannel channel = new NotificationChannel(channelTitle, channelName, channelImportance);
		channel.setDescription("Description");

		NotificationManager manager = context.getSystemService(NotificationManager.class);
		manager.createNotificationChannel(channel);
	}
}
