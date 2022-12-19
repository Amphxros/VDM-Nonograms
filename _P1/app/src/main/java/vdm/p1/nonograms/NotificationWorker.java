package vdm.p1.nonograms;



//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * This class runs on the background
 */
public class NotificationWorker extends Worker{

	public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
		super(context, workerParams);
	}

	@NonNull
	@Override
	public Result doWork() {
		final String title = getInputData().getString("tittle");
		final String contentText = getInputData().getString("contentText");
		final String biggerText = getInputData().getString("biggerText");
		final String CHANNEL_ID = getInputData().getString("CHANNEL_ID");
		final boolean autoCancel  = getInputData().getBoolean("autocancel", true);

		createAndSendNotification(title, contentText, biggerText, CHANNEL_ID, autoCancel);
		return Result.success();

	}

	public void createAndSendNotification(String title, String contentText, String biggerText, String CHANNEL_ID, boolean autoCancel){

		NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
				.setSmallIcon(1)
				.setContentTitle(title)
				.setContentText(contentText)
				.setStyle(new NotificationCompat.BigTextStyle()
						.bigText(biggerText))
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.setAutoCancel(autoCancel);

		Intent intent= new Intent(getApplicationContext(), MainActivity.class);
		intent.putExtra("notification", true);
		PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(pendingIntent);

		NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
		managerCompat.notify(999, builder.build());
		System.out.println("Enviado");
	}
}
