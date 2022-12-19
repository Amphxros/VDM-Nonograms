package vdm.p1.androidengine;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import java.util.ArrayList;

import vdm.p1.engine.Notification;
import vdm.p1.engine.INotificationHandler;

public class AndroidNotificationHandler implements INotificationHandler {
	Context context;
	String CHANNEL_ID= "My Notification";
	ArrayList<Notification> notifications;

	public AndroidNotificationHandler(Context context){
		this.context=context;
		notifications= new ArrayList<>();
		createNotificationChannel();
	}
	@Override
	public String getChannelID() {
		return CHANNEL_ID;
	}

	@Override
	public void createNotificationChannel() {
		// Create the NotificationChannel, but only on API 26+ because
		// the NotificationChannel class is new and not in the support library
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "My Notification";
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
			channel.setDescription("My channel desc");
			// Register the channel with the system; you can't change the importance
			// or other notification behaviors after this
			NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}
		else{
			System.out.println("Cant create");
		}
	}

	@Override
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	@Override
	public void addNotification(Notification notification) {
		notifications.add(notification);
	}
}
