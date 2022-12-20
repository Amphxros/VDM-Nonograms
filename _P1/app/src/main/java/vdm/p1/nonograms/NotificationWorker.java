package vdm.p1.nonograms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * This class runs in second plane, on the background
 */
public final class NotificationWorker extends Worker {
	public static final String INPUT_TITLE = "title";
	public static final String INPUT_CONTENT = "content";
	public static final String INPUT_BIGGER_TEXT = "bigger_text";
	public static final String INPUT_CHANNEL_ID = "channel_id";
	public static final String INPUT_AUTO_CANCEL = "auto_cancel";

	public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
		super(context, workerParams);
	}

	@NonNull
	@Override
	public Result doWork() {
		final String title = getInputData().getString(INPUT_TITLE);
		final String contentText = getInputData().getString(INPUT_CONTENT);
		final String biggerText = getInputData().getString(INPUT_BIGGER_TEXT);
		final String channelId = getInputData().getString(INPUT_CHANNEL_ID);
		final boolean autoCancel = getInputData().getBoolean(INPUT_AUTO_CANCEL, true);
		sendNotification(title, contentText, biggerText, channelId, autoCancel);

		return Result.success();
	}

	public void sendNotification(String title, String contentText, String biggerText, String channelId, boolean autoCancel) {
		// Creates a notification object with the specified data:
		NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId)
				.setSmallIcon(R.drawable.mail)
				.setContentTitle(title)
				.setContentText(contentText)
				.setStyle(new NotificationCompat.BigTextStyle().bigText(biggerText))
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.setAutoCancel(autoCancel);

		// Creates an intent to mark this event as a notification:
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.putExtra("notification", true);

		// Creates a notification intent:
		PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
		notification.setContentIntent(pendingIntent);

		// Launches the notification:
		NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
		manager.notify(1, notification.build());
	}
}
