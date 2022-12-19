package vdm.p1.androidengine;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

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
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
			NotificationChannel channel= new NotificationChannel(CHANNEL_ID,"notifications",NotificationManager.IMPORTANCE_DEFAULT);
			NotificationManager mngr= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			mngr.createNotificationChannel(channel);
		}
		else{
			Toast.makeText(context, "Can't create notification channel", Toast.LENGTH_SHORT).show();
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
