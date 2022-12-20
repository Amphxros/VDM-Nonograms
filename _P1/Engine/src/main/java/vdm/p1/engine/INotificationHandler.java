package vdm.p1.engine;

import java.util.ArrayList;

public interface INotificationHandler {
	/**
	 * @return The channel ID.
	 */
	String getChannelID();

	/**
	 * @return The pending notifications to launch.
	 */
	ArrayList<Notification> getPendingEntries();

	/**
	 * Adds a new {@link Notification} to the queue.
	 *
	 * @param notification {@link Notification} to the queue.
	 */
	void add(Notification notification);
}
