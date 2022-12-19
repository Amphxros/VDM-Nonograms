package vdm.p1.engine;

import java.util.ArrayList;

public interface INotificationHandler {
	/**
	 * @return
	 */
	String getChannelID();
	/**
	 * Creates the notification channel
	 */
	void createNotificationChannel();

	/**
	 * @return the pending notifications to launch
	 */
	ArrayList<Notification> getNotifications();

	/**
	 * Adds a new {@link Notification} to launch
	 * @param notification {@link Notification} to launch
	 */
	void addNotification(Notification notification);


}
