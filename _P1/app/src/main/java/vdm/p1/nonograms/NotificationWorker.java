package vdm.p1.nonograms;

import android.app.NotificationManager;
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
public class NotificationWorker extends Worker{


	public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
		super(context, workerParams);
	}

	@NonNull
	@Override
	public Result doWork() {
		final String title = getInputData().getString("title");
		final String contentText = getInputData().getString("content");
		final String biggerText = getInputData().getString("biggerText");
		final String CHANNEL_ID = getInputData().getString("CHANNEL_ID");
		final boolean autoCancel  = getInputData().getBoolean("autocancel", true);
		sendNotification(title,contentText,biggerText,CHANNEL_ID,autoCancel);
		return Result.success();
	}

	public void sendNotification(String title, String contentText, String biggerText, String CHANNEL_ID, boolean autoCancel){

		//creates the notification object with their data
		NotificationCompat.Builder notification= new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
				.setSmallIcon(R.drawable.mail)
				.setContentTitle(title)
				.setContentText(contentText)
				.setStyle(new NotificationCompat.BigTextStyle().bigText(biggerText))
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.setAutoCancel(autoCancel);


		Intent intent= new Intent(getApplicationContext(),MainActivity.class);
		intent.putExtra("notification",true);

		//
		PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setContentIntent(pendingIntent);

		//launch the notification
		NotificationManagerCompat mngr= NotificationManagerCompat.from(getApplicationContext());
		mngr.notify(1, notification.build());
	}
}
