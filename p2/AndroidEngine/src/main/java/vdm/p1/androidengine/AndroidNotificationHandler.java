package vdm.p1.androidengine;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;

import vdm.p1.engine.INotificationHandler;
import vdm.p1.engine.Notification;

public final class AndroidNotificationHandler implements INotificationHandler {
	private static final String CHANNEL_ID = "My Notification";
	private final Context context;
	private final ArrayList<Notification> notifications = new ArrayList<>();

	public AndroidNotificationHandler(Context context) {
		this.context = context;
		createNotificationChannel();
	}

	/**
	 * @return The channel ID.
	 */
	@Override
	public String getChannelID() {
		return CHANNEL_ID;
	}

	/**
	 * Create a {@link NotificationChannel}, supported only on <b>API 26+ (Oreo)</b>.
	 */
	public void createNotificationChannel() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "notifications", NotificationManager.IMPORTANCE_DEFAULT);
			NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			manager.createNotificationChannel(channel);
		} else {
			Toast.makeText(context, "Can't create notification channel", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * @return The pending notifications to launch.
	 */
	@Override
	public ArrayList<Notification> getPendingEntries() {
		return notifications;
	}

	/**
	 * Adds a new {@link Notification} to the queue.
	 *
	 * @param notification {@link Notification} to the queue.
	 */
	@Override
	public void add(Notification notification) {
		notifications.add(notification);
	}
}
